package ua.nure.bolhov.SummaryTask4.web.service;

import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.MakeOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;

import java.util.List;

public interface OrderService {

    void setAcceptedStatus(TreatmentOrderBean treatmentOrderBean);

    void setRejectedStatus(TreatmentOrderBean treatmentOrderBean);

    void setPaidStatus(PayOrderBean payOrderBean);

    void setReturnedStatus(Order order);

    void setClosedStatus(AcceptCarBean acceptCarBean);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getReturningOrdersDTO();

    List<OrderDTO> getConsideringOrdersDTO();

    void createAnOrder(MakeOrderBean makeOrderBean);
}
