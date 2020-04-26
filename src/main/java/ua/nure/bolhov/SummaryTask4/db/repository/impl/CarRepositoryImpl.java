package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.CarDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Car;
import ua.nure.bolhov.SummaryTask4.db.repository.CarRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final DBManager DB_MANAGER;
    private final CarDAORepository CAR_DAO_REPOSITORY;

    public CarRepositoryImpl(DBManager dbManager, CarDAORepository carDAORepository) {
        DB_MANAGER = dbManager;
        CAR_DAO_REPOSITORY = carDAORepository;
    }


    @Override
    public void createCar(Car car) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return CAR_DAO_REPOSITORY.insertCar(car);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public void updateCar(Car car) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return CAR_DAO_REPOSITORY.updateCar(car);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public void deleteCar(Car car) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return CAR_DAO_REPOSITORY.deleteCar(car);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public List<CarDTO> getAllCarDTO() {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return CAR_DAO_REPOSITORY.getAllCarDTO();
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
