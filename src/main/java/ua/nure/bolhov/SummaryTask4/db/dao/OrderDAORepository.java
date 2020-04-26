package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.DBException;
import ua.nure.bolhov.SummaryTask4.util.DBConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class OrderDAORepository {
    private final DBManager DB_MANAGER;

    public OrderDAORepository(DBManager dbManager) {
        DB_MANAGER = dbManager;
    }

    public boolean updateOrder(Order order) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("ORdER: "+order);
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_ORDER);
            int i = 1;
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setString(i++, order.getReasonDeny());
            preparedStatement.setLong(i, order.getId());
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

    public boolean updateOrderStatus(Order order) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int i = 1;
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_ORDER_STATUS);
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setLong(i, order.getId());
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

    public List<OrderDTO> getOrderDTOByUser(User user) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_GET_ORDER_DTO);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDTO orderDTO = extractOrderDTO(resultSet);
                orderDTOList.add(orderDTO);
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    public List<OrderDTO> getOrdersDTOByStatus(Order order) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_MANAGER_GET_ORDER_DTO);
            preparedStatement.setLong(1, order.getIdStatus());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDTOList.add(extractOrderDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    public boolean insertOrder(Order order)
            throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_ORDER);
            int i = 1;
            preparedStatement.setBoolean(i++, order.isWithDriver());
            preparedStatement.setTimestamp(i++, order.getFromDate());
            preparedStatement.setTimestamp(i++, order.getToDate());
            preparedStatement.setLong(i++, order.getIdCar());
            preparedStatement.setLong(i, order.getIdUser());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    private OrderDTO extractOrderDTO(ResultSet resultSet) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(resultSet.getLong("id_order"));
        orderDTO.setFirstName(resultSet.getString("firstname"));
        orderDTO.setLastName(resultSet.getString("lastname"));
        orderDTO.setModel(resultSet.getString("model"));
        orderDTO.setDriver(resultSet.getBoolean("driver"));
        orderDTO.setFromDate(resultSet.getTimestamp("fromdate"));
        orderDTO.setToDate(resultSet.getTimestamp("todate"));
        orderDTO.setStatus(resultSet.getString("status"));
        orderDTO.setReasonDeny(resultSet.getString("reasondeny"));
        orderDTO.setCost(resultSet.getDouble("cost"));
        return orderDTO;
    }
}
