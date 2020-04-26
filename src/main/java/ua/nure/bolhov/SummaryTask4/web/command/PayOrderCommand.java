package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.service.BillService;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class PayOrderCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(PayOrderCommand.class);
    private static final String PAY = "pay";
    private static final String BILL = "bill";
    private static final String STATUS = "status";
    private static final String CLOSED_STATUS = "closed";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String payParameter = request.getParameter(PAY);
        String billParameter = request.getParameter(BILL);
        String statusParameter = request.getParameter(STATUS);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        PayOrderBean payOrderBean = new PayOrderBean();
        payOrderBean.setIdOrder(payParameter);
        payOrderBean.setBill(billParameter);
        payOrderBean.setStatus(statusParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Map<String, String> errors = validator.validate(payOrderBean, locale);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            BillService billService = (BillService) servletContext.getAttribute(Constants.BILL_SERVICE_MANAGER);
            billService.payForBill(payOrderBean);
            if (!Objects.equals(CLOSED_STATUS, statusParameter)) {
                OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
                orderService.setPaidStatus(payOrderBean);
            }
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_CLIENT_ORDER_LIST;
    }
}
