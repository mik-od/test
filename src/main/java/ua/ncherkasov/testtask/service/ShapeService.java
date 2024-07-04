package ua.ncherkasov.testtask.service;

import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.Shape;
import ua.ncherkasov.testtask.exception.OperationNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface ShapeService<T extends Shape> {

    static int[] getParametersValue(Field[] fields, Map<String, String> attributes) throws AttributeIncorrectFormatException, AttributeNotFoundException {
        List<String> parametersMissed = new ArrayList<>();
        int [] parametersValue = new int[fields.length];
        int counter = 0;
        for (Field field : fields) {
            String parameterValue = attributes.get(field.getName());
            if (parameterValue == null) {
                parametersMissed.add(field.getName());
            }
            else {
                try {
                    parametersValue[counter++] = Integer.parseInt(parameterValue);
                } catch (NumberFormatException ex) {
                    throw new AttributeIncorrectFormatException("Incorrect format for parameter -" + field.getName());
                }
            }
        }

        if (!parametersMissed.isEmpty())
            throw new AttributeNotFoundException("Parameters " + String.join(",", parametersMissed) + " not found");
        return parametersValue;
    }

    default double getOperationResult(String operationType, T shape) throws OperationNotFoundException, InvocationTargetException, IllegalAccessException {
        Method neededMethod = Arrays.stream(ShapeService.class.getDeclaredMethods())
                                    .filter(m -> m.getName().toLowerCase().contains(operationType.toLowerCase()))
                                    .findAny()
                                    .orElseThrow(() -> new OperationNotFoundException("Method '" + operationType + "' not found"));
        return (double) neededMethod.invoke(this, shape);
    }
    boolean isSupported(String shapeType);
    T createShape(Map<String, String> attributes) throws AttributeNotFoundException, AttributeIncorrectFormatException;
    double getArea(T shape);
    double getPerimeter(T shape);
}
