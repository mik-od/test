package ua.ncherkasov.testtask.service.impl;

import org.springframework.stereotype.Service;
import ua.ncherkasov.testtask.Circle;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;

import java.util.Map;

@Service
public class CircleService implements ShapeService<Circle> {
    @Override
    public boolean isSupported(String shapeType) {
        return Circle.class.getSimpleName().equalsIgnoreCase(shapeType);
    }

    @Override
    public Circle createShape(Map<String, String> attributes) throws AttributeNotFoundException, AttributeIncorrectFormatException {
        int[] parametersValue = ShapeService.getParametersValue(Circle.class.getDeclaredFields(), attributes);
        return new Circle(parametersValue[0]);
    }

    @Override
    public double getArea(Circle circle) {
        return Math.PI * circle.radius() * circle.radius();
    }

    @Override
    public double getPerimeter(Circle circle) {
        return 2 * Math.PI * circle.radius();
    }
}
