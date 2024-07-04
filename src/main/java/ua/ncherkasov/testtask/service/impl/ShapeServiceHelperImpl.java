package ua.ncherkasov.testtask.service.impl;

import org.springframework.stereotype.Service;
import ua.ncherkasov.testtask.Shape;
import ua.ncherkasov.testtask.exception.ServiceNotFoundException;
import ua.ncherkasov.testtask.service.ShapeService;
import ua.ncherkasov.testtask.service.ShapeServiceHelper;

import java.util.List;
import java.util.function.BiPredicate;

@Service
public class ShapeServiceHelperImpl implements ShapeServiceHelper {

    private final List<ShapeService<? extends Shape>> shapeServices;

    public ShapeServiceHelperImpl(List<ShapeService<? extends Shape>> shapeServices) {
        this.shapeServices = shapeServices;
    }

    public ShapeService<? extends Shape> findService(String shapeType) throws ServiceNotFoundException {
        BiPredicate<ShapeService<? extends Shape>, String> isSupported = ShapeService::isSupported;

        return shapeServices
                .stream()
                .filter(s -> isSupported.test(s, shapeType))
                .findAny()
                .orElseThrow(() -> new ServiceNotFoundException("Service not found for shape type = " + shapeType));
    }
}
