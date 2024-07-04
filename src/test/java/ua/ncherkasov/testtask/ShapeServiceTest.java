package ua.ncherkasov.testtask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.exception.ServiceNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;
import ua.ncherkasov.testtask.service.ShapeServiceHelper;
import ua.ncherkasov.testtask.service.impl.SquareService;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ShapeServiceTest {

    private final static Map<String, String> attributes = new HashMap<>();

    @Autowired
    private ShapeServiceHelper shapeServiceHelper;

    @BeforeAll
    public static void init() {
        attributes.put("length","2");
        attributes.put("width","4");
        attributes.put("side1","2");
        attributes.put("side2","3");
        attributes.put("side3","4");
        attributes.put("radius","5");
    }

    @Test
    public void testServiceCreation() throws ServiceNotFoundException {
        Assertions.assertThrows(ServiceNotFoundException.class,
                () ->shapeServiceHelper.findService(null));

        Assertions.assertThrows(ServiceNotFoundException.class,
                () ->shapeServiceHelper.findService("something"));

        Assertions.assertInstanceOf(SquareService.class, shapeServiceHelper.findService("square"));
    }

    @Test
    public void testShapeCreation() throws ServiceNotFoundException,
            AttributeNotFoundException, AttributeIncorrectFormatException {
        Map<String, String> attributesLocal = new HashMap<>();
        attributesLocal.put("length","--");

        ShapeService<? extends Shape> shapeService = shapeServiceHelper.findService("square");
        attributesLocal.put("length","--");
        Assertions.assertThrows(AttributeIncorrectFormatException.class, () ->shapeService.createShape(attributesLocal));

        attributesLocal.remove("length");
        Assertions.assertThrows(AttributeNotFoundException.class, () ->shapeService.createShape(attributesLocal));

        attributesLocal.putAll(attributes);
        Shape shape = shapeService.createShape(attributesLocal);
        Assertions.assertInstanceOf(Square.class, shape);
    }
    @Test
    @SuppressWarnings("unchecked")
    public void testArea() throws ServiceNotFoundException,
            AttributeNotFoundException, AttributeIncorrectFormatException {
        Map<String, String> attributesLocal = new HashMap<>(attributes);
        ShapeService<Shape> shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("square");
        Shape shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(4, shapeService.getArea(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("rectangle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(8, shapeService.getArea(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("triangle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(3.072051431861127, shapeService.getArea(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("circle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(78.53981633974483, shapeService.getArea(shape));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPerimeter() throws ServiceNotFoundException,
            AttributeNotFoundException, AttributeIncorrectFormatException {
        Map<String, String> attributesLocal = new HashMap<>(attributes);
        ShapeService<Shape> shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("square");
        Shape shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(8, shapeService.getPerimeter(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("rectangle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(12, shapeService.getPerimeter(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("triangle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(9, shapeService.getPerimeter(shape));

        shapeService = (ShapeService<Shape>) shapeServiceHelper.findService("circle");
        shape = shapeService.createShape(attributesLocal);
        Assertions.assertEquals(31.41592653589793, shapeService.getPerimeter(shape));
    }
}
