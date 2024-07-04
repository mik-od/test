package ua.ncherkasov.testtask.service.impl;

import org.springframework.stereotype.Service;
import ua.ncherkasov.testtask.Rectangle;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;

import java.util.Map;

@Service
public class RectangleService implements ShapeService<Rectangle> {
    @Override
    public boolean isSupported(String shapeType) {
        return Rectangle.class.getSimpleName().equalsIgnoreCase(shapeType);
    }

    @Override
    public Rectangle createShape(Map<String, String> attributes) throws AttributeNotFoundException, AttributeIncorrectFormatException {
        int[] parametersValue = ShapeService.getParametersValue(Rectangle.class.getDeclaredFields(), attributes);
        return new Rectangle(parametersValue[0], parametersValue[1]);
    }

    @Override
    public double getArea(Rectangle rectangle) {
        return rectangle.length()*rectangle.width();
    }

    @Override
    public double getPerimeter(Rectangle rectangle) {
        return rectangle.length()*2+rectangle.width()*2;
    }
}
