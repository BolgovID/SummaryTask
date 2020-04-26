package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.util.DBConstant;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Car;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class CarDAORepository {

    private final DBManager DB_MANAGER;

    public CarDAORepository(DBManager dbManager) {
        DB_MANAGER = dbManager;
    }

    public boolean insertCar(Car car) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_CAR);
            int i = 1;
            preparedStatement.setString(i++, car.getModel());
            preparedStatement.setDouble(i++, car.getCost());
            preparedStatement.setLong(i++, car.getIdBrand());
            preparedStatement.setLong(i, car.getIdCategory());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    public boolean updateCar(Car car) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_CAR);
            int i = 1;
            preparedStatement.setString(i++, car.getModel());
            preparedStatement.setDouble(i++, car.getCost());
            preparedStatement.setLong(i++, car.getIdBrand());
            preparedStatement.setLong(i++, car.getIdCategory());
            preparedStatement.setLong(i, car.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    public boolean deleteCar(Car car) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_DELETE_CAR);
            preparedStatement.setLong(1, car.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    public List<CarDTO> getAllCarDTO() throws DBException {
        List<CarDTO> carDTOList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.SQL_GET_ALL_CAR_DTO);
            while (resultSet.next()) {
                carDTOList.add(extractCarBean(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return carDTOList;
    }

    private CarDTO extractCarBean(ResultSet resultSet) throws SQLException {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(resultSet.getLong("id_car"));
        carDTO.setBrand(resultSet.getString("brand"));
        carDTO.setModel(resultSet.getString("model"));
        carDTO.setCost(resultSet.getDouble("cost"));
        carDTO.setCategory(resultSet.getString("category"));
        return carDTO;
    }
}
