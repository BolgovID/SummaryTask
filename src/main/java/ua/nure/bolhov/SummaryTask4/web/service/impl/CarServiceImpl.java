package ua.nure.bolhov.SummaryTask4.web.service.impl;

import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.db.entity.Car;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.db.repository.BrandRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.CarRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.CategoryRepository;
import ua.nure.bolhov.SummaryTask4.web.bean.OperationCarBean;
import ua.nure.bolhov.SummaryTask4.web.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarRepository CAR_REPOSITORY;
    private final BrandRepository BRAND_REPOSITORY;
    private final CategoryRepository CATEGORY_REPOSITORY;

    public CarServiceImpl(CarRepository CAR_REPOSITORY, BrandRepository BRAND_REPOSITORY, CategoryRepository CATEGORY_REPOSITORY) {
        this.CAR_REPOSITORY = CAR_REPOSITORY;
        this.BRAND_REPOSITORY = BRAND_REPOSITORY;
        this.CATEGORY_REPOSITORY = CATEGORY_REPOSITORY;
    }

    @Override
    public void createCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.createCar(getEntity(operationCarBean));
    }

    @Override
    public void updateCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.updateCar(getEntity(operationCarBean));
    }

    @Override
    public void deleteCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.deleteCar(getEntity(operationCarBean));
    }

    @Override
    public List<CarDTO> getAllCarDTO() {
        return CAR_REPOSITORY.getAllCarDTO();
    }

    @Override
    public List<Category> getCategoryList() {
        return CATEGORY_REPOSITORY.getCategoryList();
    }

    @Override
    public List<Brand> getBrandList() {
        return BRAND_REPOSITORY.getBrandList();
    }

    private Car getEntity(OperationCarBean operationCarBean) {
        Car car = new Car();
        car.setId(operationCarBean.getId());
        car.setCost(operationCarBean.getCost());
        car.setModel(operationCarBean.getModel());
        car.setIdBrand(operationCarBean.getIdBrand());
        car.setIdCategory(operationCarBean.getIdCategory());
        return car;
    }
}
