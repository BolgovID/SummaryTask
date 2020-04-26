package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.OperationCarBean;
import ua.nure.bolhov.SummaryTask4.web.service.CarService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class AddCarCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(AddCarCommand.class);
    private static final String MODEL = "model";
    private static final String COST = "cost";
    private static final String BRAND = "brand";
    private static final String category = "category";
    private static final String ERRORS = "errorsAdd";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String modelParameter = request.getParameter(MODEL);
        String costParameter = request.getParameter(COST);
        String brandParameter = request.getParameter(BRAND);
        String categoryParameter = request.getParameter(category);

        OperationCarBean operationCarBean = new OperationCarBean();
        operationCarBean.setId(Constants.NOT_EMPTY_NUMBER_FIELD);
        operationCarBean.setCost(costParameter);
        operationCarBean.setModel(modelParameter);
        operationCarBean.setIdBrand(brandParameter);
        operationCarBean.setIdCategory(categoryParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Map<String, String> errors = validator.validate(operationCarBean, locale);
        request.setAttribute(ERRORS, errors);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            CarService carService = (CarService) servletContext.getAttribute(Constants.CAR_SERVICE_MANAGER);
            carService.createCar(operationCarBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_ADMIN_PANEL;
    }
}
