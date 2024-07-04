package ua.ncherkasov.testtask.service.impl;

import org.springframework.stereotype.Service;
import ua.ncherkasov.testtask.Square;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;

import java.util.Map;

@Service
public class SquareService implements ShapeService<Square> {
    @Override
    public boolean isSupported(String shapeType) {
        return Square.class.getSimpleName().equalsIgnoreCase(shapeType);
    }

    @Override
    public Square createShape(Map<String, String> attributes) throws AttributeNotFoundException, AttributeIncorrectFormatException {
        int[] parametersValue = ShapeService.getParametersValue(Square.class.getDeclaredFields(), attributes);
        return new Square(parametersValue[0]);
    }

    @Override
    public double getArea(Square square) {
        return square.length()*square.length();
    }

    @Override
    public double getPerimeter(Square square) {
        return square.length()*4;
    }
}
