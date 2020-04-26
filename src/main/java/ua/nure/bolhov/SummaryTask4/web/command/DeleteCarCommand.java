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

public class DeleteCarCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteCarCommand.class);
    private final static String ID_CAR = "id_car";
    private final static String ERRORS = "errorsDelete";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String idParameter = request.getParameter(ID_CAR);

        OperationCarBean operationCarBean = new OperationCarBean();
        operationCarBean.setId(idParameter);
        operationCarBean.setCost(Constants.NOT_EMPTY_NUMBER_FIELD);
        operationCarBean.setModel(Constants.NOT_EMPTY_STRING_FIELD);
        operationCarBean.setIdBrand(Constants.NOT_EMPTY_NUMBER_FIELD);
        operationCarBean.setIdCategory(Constants.NOT_EMPTY_NUMBER_FIELD);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);
        Map<String, String> errors = validator.validate(operationCarBean, locale);
        request.setAttribute(ERRORS, errors);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            CarService carService = (CarService) servletContext.getAttribute(Constants.CAR_SERVICE_MANAGER);
            carService.deleteCar(operationCarBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_ADMIN_PANEL;
    }
}
