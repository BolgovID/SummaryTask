package ua.nure.bolhov.SummaryTask4.db.repository;

import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Car;

import java.util.List;

public interface CarRepository {

    void createCar(Car car);

    void updateCar(Car car);

    void deleteCar(Car car);

    List<CarDTO> getAllCarDTO();

}
