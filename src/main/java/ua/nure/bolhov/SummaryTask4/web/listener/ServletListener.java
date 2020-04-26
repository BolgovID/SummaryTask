package ua.nure.bolhov.SummaryTask4.web.listener;

import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.BillDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dao.BrandDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dao.CarDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dao.CategoryDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dao.OrderDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dao.UserDAORepository;
import ua.nure.bolhov.SummaryTask4.db.repository.BillRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.BrandRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.CarRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.CategoryRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.OrderRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.UserRepository;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.BillRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.BrandRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.CarRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.CategoryRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.OrderRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.db.repository.impl.UserRepositoryImpl;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.command.Command;
import ua.nure.bolhov.SummaryTask4.web.service.BillService;
import ua.nure.bolhov.SummaryTask4.web.service.CarService;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;
import ua.nure.bolhov.SummaryTask4.web.service.impl.BillServiceImpl;
import ua.nure.bolhov.SummaryTask4.web.service.impl.CarServiceImpl;
import ua.nure.bolhov.SummaryTask4.web.service.impl.OrderServiceImpl;
import ua.nure.bolhov.SummaryTask4.web.service.impl.UserServiceImpl;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Command.setContext(servletContext);

        DBManager dbManager = DBManager.getInstance();
        UserService userService = getUserService(dbManager);
        BillService billService = getBillService(dbManager);
        CarService carService = getCarService(dbManager);
        OrderService orderService = getOrderService(dbManager);

        Util util = new Util();
        Validator validator = new Validator(util);


        servletContext.setAttribute(Constants.VALIDATOR, validator);
        servletContext.setAttribute(Constants.UTIL, util);
        servletContext.setAttribute(Constants.USER_SERVICE_MANAGER,userService);
        servletContext.setAttribute(Constants.BILL_SERVICE_MANAGER,billService);
        servletContext.setAttribute(Constants.CAR_SERVICE_MANAGER,carService);
        servletContext.setAttribute(Constants.ORDER_SERVICE_MANAGER, orderService);
    }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private UserService getUserService(DBManager dbManager) {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        UserRepository userRepository = new UserRepositoryImpl(userDAORepository, dbManager);
        return new UserServiceImpl(userRepository);
    }

    private CarService getCarService(DBManager dbManager) {
        CarDAORepository carDAORepository = new CarDAORepository(dbManager);
        BrandDAORepository brandDAORepository = new BrandDAORepository(dbManager);
        CategoryDAORepository categoryDAORepository = new CategoryDAORepository(dbManager);

        CarRepository carRepository = new CarRepositoryImpl(dbManager, carDAORepository);
        BrandRepository brandRepository = new BrandRepositoryImpl(dbManager, brandDAORepository);
        CategoryRepository categoryRepository = new CategoryRepositoryImpl(dbManager, categoryDAORepository);
        return new CarServiceImpl(carRepository, brandRepository, categoryRepository);
    }

    private OrderService getOrderService(DBManager dbManager) {
        OrderDAORepository orderDAORepository = new OrderDAORepository(dbManager);
        OrderRepository orderRepository = new OrderRepositoryImpl(orderDAORepository, dbManager);
        return new OrderServiceImpl(orderRepository);
    }

    private BillService getBillService(DBManager dbManager) {
        BillDAORepository billDAORepository = new BillDAORepository(dbManager);
        BillRepository billRepository = new BillRepositoryImpl(dbManager, billDAORepository);
        return new BillServiceImpl(billRepository);
    }
}
