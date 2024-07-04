package ua.ncherkasov.testtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ncherkasov.testtask.Shape;
import ua.ncherkasov.testtask.exception.AttributeIncorrectFormatException;
import ua.ncherkasov.testtask.exception.AttributeNotFoundException;
import ua.ncherkasov.testtask.exception.OperationNotFoundException;
import ua.ncherkasov.testtask.exception.ServiceNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;
import ua.ncherkasov.testtask.service.ShapeServiceHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/testtask")
public class MainController {

    private final ShapeServiceHelper shapeServiceHelper;

    public MainController(ShapeServiceHelper shapeServiceHelper) {
        this.shapeServiceHelper = shapeServiceHelper;
    }

    @RequestMapping(value = "/operation/{operationType}/{shapeType}",method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public String performOperation(@PathVariable("operationType") String operationType,
                                   @PathVariable("shapeType") String shapeType,
                                   @RequestParam Map<String,String> requestParams) throws ServiceNotFoundException,
            AttributeNotFoundException, AttributeIncorrectFormatException,
            OperationNotFoundException, InvocationTargetException, IllegalAccessException {
        ShapeService<Shape> shapeService = (ShapeService<Shape>) shapeServiceHelper.findService(shapeType);
        Shape shape = shapeService.createShape(requestParams);
        return String.valueOf(shapeService.getOperationResult(operationType,shape));
    }

    @ExceptionHandler({ ServiceNotFoundException.class, ServiceNotFoundException.class,
            AttributeNotFoundException.class, OperationNotFoundException.class})
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST) ;
    }
}
