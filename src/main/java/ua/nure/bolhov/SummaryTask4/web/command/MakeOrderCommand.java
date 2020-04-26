package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.MakeOrderBean;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;

public class MakeOrderCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(MakeOrderCommand.class);
    private static final String DRIVER = "driver";
    private static final String FROM = "fromDate";
    private static final String TO = "toDate";
    private static final String CHOICE = "chooseCar";
    private static final String ERRORS = "errors";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String driverParameter = request.getParameter(DRIVER);
        String fromDateParameter = request.getParameter(FROM);
        String toDateParameter = request.getParameter(TO);
        String chooseCarParameter = request.getParameter(CHOICE);

        Util util = (Util) servletContext.getAttribute(Constants.UTIL);
        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);

        Timestamp fromDate = util.convertStringToTimestamp(fromDateParameter);
        Timestamp toDate = util.convertStringToTimestamp(toDateParameter);

        MakeOrderBean makeOrderBean = new MakeOrderBean();
        makeOrderBean.setIdUser(user);
        makeOrderBean.setIdCar(chooseCarParameter);
        makeOrderBean.setWithDriver(driverParameter);
        makeOrderBean.setFrom(fromDate);
        makeOrderBean.setTo(toDate);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Map<String, String> errors = validator.validate(makeOrderBean,locale);
        request.setAttribute(ERRORS, errors);

        if (util.isNotReSubmitting(request) && errors.isEmpty()) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
            orderService.createAnOrder(makeOrderBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_CLIENT_CAR_LIST;
    }

}
