package ua.nure.bolhov.SummaryTask4.web.service;

import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.web.bean.OperationCarBean;

import java.util.List;

public interface CarService {

    void createCar(OperationCarBean operationCarBean);

    void updateCar(OperationCarBean operationCarBean);

    void deleteCar(OperationCarBean operationCarBean);

    List<CarDTO> getAllCarDTO();

    List<Category> getCategoryList();

    List<Brand> getBrandList();

}
