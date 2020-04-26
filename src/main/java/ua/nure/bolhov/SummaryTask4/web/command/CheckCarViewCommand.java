package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CheckCarViewCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(CheckCarViewCommand.class);
    private static final String ORDER_DTO_LIST = "orderDTOList";
    private static final String DECIDE = "decide";
    private static final String SELECTED_OPTION = "selectedOption";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
        List<OrderDTO> orderDTOList = orderService.getReturningOrdersDTO();
        request.setAttribute(ORDER_DTO_LIST, orderDTOList);
        String selectedOption = request.getParameter(DECIDE);
        if (Objects.nonNull(selectedOption) && !selectedOption.isEmpty()) {
            request.setAttribute(SELECTED_OPTION, selectedOption);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.PAGE_MANAGER_RETURN_CAR;
    }
}
