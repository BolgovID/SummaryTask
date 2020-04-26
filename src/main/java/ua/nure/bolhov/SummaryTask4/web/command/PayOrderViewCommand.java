package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.service.BillService;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PayOrderViewCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(PayOrderViewCommand.class);
    private static final String BILLS = "billList";
    private static final String ORDERS = "orderDTOList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        User user = (User) request.getSession().getAttribute(Constants.USER);
        OrderService orderService = (OrderService) servletContext.getAttribute(Constants.ORDER_SERVICE_MANAGER);
        List<OrderDTO> orderDTOList = orderService.getOrderDTOByUser(user);
        List<Bill> bills = new ArrayList<>();
        BillService billService = (BillService) servletContext.getAttribute(Constants.BILL_SERVICE_MANAGER);
        for (OrderDTO order : orderDTOList) {
            bills.addAll(billService.getBillsForOneOrder(order));
        }
        request.setAttribute(BILLS, bills);
        request.setAttribute(ORDERS, orderDTOList);
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        return Path.PAGE_CLIENT_ORDER_LIST;
    }
}
