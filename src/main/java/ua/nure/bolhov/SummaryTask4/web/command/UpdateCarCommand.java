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

public class UpdateCarCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(UpdateCarCommand.class);
    private final static String ID_CAR = "id_car";
    private final static String MODEL = "model";
    private final static String COST = "cost";
    private final static String BRAND = "brand";
    private final static String CATEGORY = "category";
    private final static String ERRORS = "errorsUpdate";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String idParameter = request.getParameter(ID_CAR);
        String modelParameter = request.getParameter(MODEL);
        String costParameter = request.getParameter(COST);
        String brandParameter = request.getParameter(BRAND);
        String categoryParameter = request.getParameter(CATEGORY);

        OperationCarBean operationCarBean = new OperationCarBean();
        operationCarBean.setId(idParameter);
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
            carService.updateCar(operationCarBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        return Path.COMMAND_ADMIN_PANEL;
    }
}
