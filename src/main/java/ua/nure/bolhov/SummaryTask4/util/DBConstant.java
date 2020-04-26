package ua.nure.bolhov.SummaryTask4.util;

public final class DBConstant {

    public static final String SQL_INSERT_ORDER = "INSERT INTO orders(driver, fromdate, todate, id_car, id_user) VALUES (?,?,?,?,?)";
    public static final String SQL_INSERT_BILL = "INSERT INTO bills(cost,reason,id_order) VALUES (?,?,?)";
    public static final String SQL_INSERT_USER = "INSERT INTO users(login, password, firstname, lastname, age, id_role) VALUES (?,?,?,?,?,?)";
    public static final String SQL_INSERT_CAR = "INSERT INTO  cars(model, cost, id_brand, id_category) VALUES(?,?,?,?) ";

    public static final String SQL_UPDATE_CAR = "UPDATE cars SET model=?, cost=?, id_brand=?, id_category=? WHERE id_car=?" ;
    public static final String SQL_UPDATE_ORDER = "UPDATE orders SET id_status=?, reasondeny=? WHERE id_order=?";
    public static final String SQL_UPDATE_BILL = "UPDATE bills SET paid = (?) WHERE id_bill=(?)";
    public static final String SQL_UPDATE_ORDER_STATUS = "UPDATE orders SET id_status=? WHERE id_order=?";
    public static final String SQL_UPDATE_USER = "UPDATE users SET firstname=?, lastname=?, age=? WHERE id_user=?";
    public static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET id_status=? WHERE id_user=?";


    public static final String SQL_DELETE_CAR = "DELETE FROM cars WHERE id_car=?";

    public static final String SQL_GET_BILLS_BY_ORDER = "SELECT * FROM bills WHERE id_order=(?) AND paid=false";
    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=(?)";

    public static final String SQL_GET_ALL_CATEGORY = "SELECT * FROM categories";
    public static final String SQL_GET_ALL_BRAND = "SELECT * FROM brands";

    public static final String SQL_GET_ALL_CAR_DTO =
            "SELECT cars.id_car, brands.brand, categories.category, cars.model,cars.cost FROM brands" +
                    " INNER JOIN cars ON brands.id_brand=cars.id_brand " +
                    "INNER JOIN categories ON cars.id_category=categories.id_category";
    public static final String SQL_GET_ORDER_DTO =
            "SELECT o.id_user,u.age, u.firstname,u.lastname, o.id_order, " +
                    "os.status,c.cost, c.model, o.driver, o.fromdate, o.todate," +
                    " o.reasondeny FROM order_statuses os" +
                    " INNER JOIN orders o ON os.id_status=o.id_status" +
                    " INNER JOIN cars c ON c.id_car=o.id_car" +
                    " INNER JOIN users u on o.id_user = u.id_user  WHERE o.id_user=(?)";

    public static final String SQL_MANAGER_GET_ORDER_DTO =
            "SELECT o.id_user,u.age, u.firstname,u.lastname," +
                    " o.id_order, os.status, c.model, o.driver, o.fromdate, o.todate," +
                    " o.reasondeny, c.cost FROM order_statuses os" +
                    " INNER JOIN orders o ON os.id_status=o.id_status" +
                    " INNER JOIN cars c ON c.id_car=o.id_car" +
                    " INNER JOIN users u on o.id_user = u.id_user  WHERE os.id_status=(?)";
    public static final String SQL_GET_USER_DTO =
            "SELECT u.id_user, u.login, u.firstname,u.lastname, u.age, s.status, r.role FROM users u" +
                    " INNER JOIN user_statuses s ON s.id_status=u.id_status " +
                    "INNER JOIN roles r ON r.id_role=u.id_role";


    private DBConstant() {
    }
}
