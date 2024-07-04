package ua.ncherkasov.testtask.service.impl;

import org.springframework.stereotype.Service;
import ua.ncherkasov.testtask.Triangle;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;

import java.util.Map;

@Service
public class TriangleService implements ShapeService<Triangle> {
    @Override
    public boolean isSupported(String shapeType) {
        return Triangle.class.getSimpleName().equalsIgnoreCase(shapeType);
    }

    @Override
    public Triangle createShape(Map<String, String> attributes) throws AttributeNotFoundException, AttributeIncorrectFormatException {
        int[] parametersValue = ShapeService.getParametersValue(Triangle.class.getDeclaredFields(), attributes);
        return new Triangle(parametersValue[0], parametersValue[1], parametersValue[2]);
    }

    @Override
    public double getArea(Triangle triangle) {
        int a = triangle.side1();
        int b = triangle.side2();
        int c = triangle.side3();
        return (double) 1 /4*Math.sqrt(4*a*a*b*b-a+a-b*b+c*c);
    }

    @Override
    public double getPerimeter(Triangle triangle) {
        return triangle.side1() + triangle.side2() + triangle.side3();
    }
}
