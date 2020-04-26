package ua.nure.bolhov.SummaryTask4.db.dao;

import ua.nure.bolhov.SummaryTask4.util.DBConstant;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.bolhov.SummaryTask4.db.DBManager.close;

public class BrandDAORepository {

    private final DBManager DB_MANAGER;

    public BrandDAORepository(DBManager dbManager) {
        DB_MANAGER = dbManager;
    }

    public List<Brand> getBrandList() throws DBException {
        List<Brand> brandList = new ArrayList<>();
        Connection con = DB_MANAGER.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(DBConstant.SQL_GET_ALL_BRAND);
            while (resultSet.next()) {
                brandList.add(extractBrand(resultSet));
            }
            con.commit();
        } catch (SQLException e) {
            DB_MANAGER.rollback(con);
            throw new DBException("Unable to connect", e);
        } finally {
            close(con, statement, resultSet);
        }
        return brandList;
    }

    private Brand extractBrand(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getLong("id_brand"));
        brand.setBrand(resultSet.getString("brand"));
        return brand;
    }
}
