package ua.nure.bolhov.SummaryTask4.web.service.impl;

import ua.nure.bolhov.SummaryTask4.db.OrderStatus;
import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.db.repository.OrderRepository;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.MakeOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;
import ua.nure.bolhov.SummaryTask4.web.service.OrderService;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository ORDER_REPOSITORY;

    public OrderServiceImpl(OrderRepository ORDER_REPOSITORY) {
        this.ORDER_REPOSITORY = ORDER_REPOSITORY;
    }


    @Override
    public void setAcceptedStatus(TreatmentOrderBean treatmentOrderBean) {
        ORDER_REPOSITORY.updateOrder(getEntity(treatmentOrderBean));
    }

    @Override
    public void setReturnedStatus(Order order) {
       ORDER_REPOSITORY.updateOrderStatus(getEntity(order));
    }

    @Override
    public void setRejectedStatus(TreatmentOrderBean treatmentOrderBean) {
        ORDER_REPOSITORY.updateOrder(getEntity(treatmentOrderBean));
    }

    @Override
    public void setPaidStatus(PayOrderBean payOrderBean) {
        ORDER_REPOSITORY.updateOrderStatus(getEntity(payOrderBean));
    }

    @Override
    public void setClosedStatus(AcceptCarBean acceptCarBean) {
        ORDER_REPOSITORY.updateOrderStatus(getEntity(acceptCarBean));
    }

    @Override
    public List<OrderDTO> getOrderDTOByUser(User user) {
        return ORDER_REPOSITORY.getOrderDTOByUser(user);
    }

    @Override
    public List<OrderDTO> getReturningOrdersDTO() {
        Order order = new Order();
        order.setIdStatus(OrderStatus.RETURNING.getNumber());
        return ORDER_REPOSITORY.getOrdersDTOByStatus(order);
    }

    @Override
    public List<OrderDTO> getConsideringOrdersDTO() {
        Order order = new Order();
        order.setIdStatus(OrderStatus.CONSIDERING.getNumber());
        return ORDER_REPOSITORY.getOrdersDTOByStatus(order);
    }

    @Override
    public void createAnOrder(MakeOrderBean makeOrderBean) {
        ORDER_REPOSITORY.createAnOrder(getEntity(makeOrderBean));
    }

    private Order getEntity(MakeOrderBean makeOrderBean) {
        Order order = new Order();
        order.setIdCar(makeOrderBean.getIdCar());
        order.setWithDriver(makeOrderBean.isWithDriver());
        order.setFromDate(new Timestamp(makeOrderBean.getFrom()));
        order.setToDate(new Timestamp(makeOrderBean.getTo()));
        order.setIdUser(makeOrderBean.getIdUser());
        return order;
    }

    private Order getEntity(PayOrderBean payOrderBean) {
        Order order = new Order();
        order.setId(payOrderBean.getIdOrder());
        order.setIdStatus(OrderStatus.PAID.getNumber());
        return order;
    }

    private Order getEntity(TreatmentOrderBean treatmentOrderBean) {
        Order order = new Order();
        order.setId(treatmentOrderBean.getId());
        order.setIdStatus(treatmentOrderBean.getStatus().getNumber());
        order.setReasonDeny(treatmentOrderBean.getComment());
        return order;
    }

    private Order getEntity(Order order) {
        order.setIdStatus(OrderStatus.RETURNING.getNumber());
        return order;
    }

    private Order getEntity(AcceptCarBean acceptCarBean) {
        Order order = new Order();
        order.setId(acceptCarBean.getId());
        order.setIdStatus(OrderStatus.CLOSED.getNumber());
        return order;
    }
}
