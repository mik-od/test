package ua.ncherkasov.testtask.service;

import ua.ncherkasov.testtask.Shape;
import ua.ncherkasov.testtask.exception.ServiceNotFoundException;

public interface ShapeServiceHelper {

    ShapeService<? extends Shape> findService(String shapeType) throws ServiceNotFoundException;
}
