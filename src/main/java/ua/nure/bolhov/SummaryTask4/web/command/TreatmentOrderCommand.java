package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.OrderStatus;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;
import ua.nure.bolhov.SummaryTask4.web.service.BillService;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class TreatmentOrderCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(TreatmentOrderCommand.class);
    private static final String DECIDE = "decide";
    private static final String COMMENT = "comment";
    private static final String ORDER = "order";
    private static final String COST = "cost";
    private static final String ERRORS = "errors";
    private static final String RENT = "Rent";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOGGER.debug(LoggerUtils.COMMAND_STARTS);

        String statusParameter = request.getParameter(DECIDE);
        String commentParameter = request.getParameter(COMMENT);
        String orderIdParameter = request.getParameter(ORDER);
        String costParameter = request.getParameter(COST);

        OrderStatus status = OrderStatus.getOrder(statusParameter);

        TreatmentOrderBean treatmentOrderBean = new TreatmentOrderBean();
        treatmentOrderBean.setId(orderIdParameter);
        treatmentOrderBean.setCost(costParameter);
        treatmentOrderBean.setStatus(status);
        treatmentOrderBean.setComment(commentParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);
        Map<String, String> errors = validator.validate(treatmentOrderBean,locale);
        request.setAttribute(ERRORS, errors);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
            if (status.equals(OrderStatus.ACCEPTED)) {
                treatmentOrderBean.setComment(RENT);
                BillService billService = (BillService) servletContext.getAttribute(Constants.BILL_SERVICE_MANAGER);
                orderService.setAcceptedStatus(treatmentOrderBean);
                billService.createBill(treatmentOrderBean);
            } else {
                orderService.setRejectedStatus(treatmentOrderBean);
            }
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_MANAGER_LIST_ORDERS;
    }
}
