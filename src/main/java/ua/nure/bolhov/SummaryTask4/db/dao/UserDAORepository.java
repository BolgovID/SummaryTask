package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.util.DBConstant;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class UserDAORepository {
    private final DBManager DB_MANAGER;

    public UserDAORepository(DBManager dbManager) {
        DB_MANAGER = dbManager;
    }

    public boolean updateUserStatus(User user) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_USER_STATUS);
            int i = 1;
            preparedStatement.setLong(i++, user.getIdStatus());
            preparedStatement.setLong(i, user.getId());
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

    public boolean updateUser(User user) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_USER);
            int i = 1;
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setInt(i++, user.getAge());
            preparedStatement.setLong(i, user.getId());
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

    public boolean insertUser(User user) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_USER);
            int i = 1;
            preparedStatement.setString(i++, user.getLogin());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setInt(i++, user.getAge());
            preparedStatement.setLong(i, user.getIdRole());
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

    public List<UserDTO> getUserDTO() throws DBException {
        List<UserDTO> userDTOList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.SQL_GET_USER_DTO);
            while (resultSet.next()) {
                userDTOList.add(extractUserDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return userDTOList;
    }

    public User getUser(User user) throws DBException {
        Connection connection = DB_MANAGER.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User findUser = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_GET_USER_BY_LOGIN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                findUser = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return findUser;
    }

    private UserDTO extractUserDTO(ResultSet resultSet) throws SQLException {
        UserDTO user = new UserDTO();
        user.setId((resultSet.getLong("id_user")));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setRole(resultSet.getString("role"));
        user.setStatus(resultSet.getString("status"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }

    private static User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId((resultSet.getLong("id_user")));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setPassword(resultSet.getString("password"));
        user.setIdRole(resultSet.getLong("id_role"));
        user.setIdStatus(resultSet.getLong("id_status"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }

}
