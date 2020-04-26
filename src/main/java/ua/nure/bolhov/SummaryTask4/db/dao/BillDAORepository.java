package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.util.DBConstant;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class BillDAORepository {

    private final DBManager DB_MANAGER;

    public BillDAORepository(DBManager dbManager) {
        DB_MANAGER = dbManager;
    }

    public boolean insertBill(Bill bill) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_BILL);
            int i = 1;
            preparedStatement.setDouble(i++, bill.getCost());
            preparedStatement.setString(i++, bill.getReason());
            preparedStatement.setLong(i, bill.getOrderId());
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

    public List<Bill> getBills(Order order) throws DBException {
        List<Bill> billList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_GET_BILLS_BY_ORDER);
            preparedStatement.setLong(1, order.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                billList.add(extractBill(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return billList;
    }


    public boolean updateBill(Bill bill) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_BILL);
            int i = 1;
            preparedStatement.setBoolean(i++, bill.isPaid());
            preparedStatement.setLong(i, bill.getId());
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

    private Bill extractBill(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getLong("id_bill"));
        bill.setCost(resultSet.getDouble("cost"));
        bill.setReason(resultSet.getString("reason"));
        bill.setOrderId(resultSet.getLong("id_order"));
        return bill;
    }
}
