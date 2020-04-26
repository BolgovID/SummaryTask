package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.util.DBConstant;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class CategoryDAORepository {

    private final DBManager DB_MANAGER;

    public CategoryDAORepository(DBManager dbManager) {
        this.DB_MANAGER = dbManager;
    }

    public List<Category> getCategoryList() throws DBException {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = DB_MANAGER.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.SQL_GET_ALL_CATEGORY);
            while (resultSet.next()) {
                categoryList.add(extractCategory(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(connection);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return categoryList;
    }

    private Category extractCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id_category"));
        category.setCategory(resultSet.getString("category"));
        return category;
    }
}
