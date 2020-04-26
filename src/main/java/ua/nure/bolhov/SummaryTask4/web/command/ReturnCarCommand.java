package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ReturnCarCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(ReturnCarCommand.class);
    private static final String ORDER_ID = "orderId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String payParameter = request.getParameter(ORDER_ID);
        Order order = new Order();
        long orderId = (Objects.isNull(payParameter) || payParameter.isEmpty()) ? 0 : Long.parseLong(payParameter);
        order.setId(orderId);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);
        if (orderId != 0 && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
            orderService.setReturnedStatus(order);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_CLIENT_ORDER_LIST;
    }
}
