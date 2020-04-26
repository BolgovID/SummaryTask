package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
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

public class AcceptCarCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AcceptCarCommand.class);
    public static final String DECIDE = "decide";
    public static final String PENALTY_COMMENT = "penaltyComment";
    public static final String ORDER_ID = "orderId";
    public static final String COST = "cost";
    private static final String ERRORS = "errors";
    private static final String PENALTY = "penalty";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String decideParameter = request.getParameter(DECIDE);
        String commentParameter = request.getParameter(PENALTY_COMMENT);
        String idParameter = request.getParameter(ORDER_ID);
        String costParameter = request.getParameter(COST);

        AcceptCarBean acceptCarBean = new AcceptCarBean();
        acceptCarBean.setId(idParameter);
        acceptCarBean.setDecide(decideParameter);
        acceptCarBean.setComment(commentParameter);
        acceptCarBean.setCost(costParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);
        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Map<String, String> errors = validator.validate(acceptCarBean, locale);
        request.setAttribute(ERRORS, errors);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
            orderService.setClosedStatus(acceptCarBean);
            if (Objects.equals(PENALTY, decideParameter)) {
                BillService billService = (BillService) servletContext.getAttribute(Constants.BILL_SERVICE_MANAGER);
                billService.createBill(acceptCarBean);
            }
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_MANAGER_CHECK_CARS;
    }
}
