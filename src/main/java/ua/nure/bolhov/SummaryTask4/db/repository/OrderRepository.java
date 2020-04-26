package ua.nure.bolhov.SummaryTask4.db.repository;

import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.entity.User;

import java.util.List;

public interface OrderRepository {

    void updateOrder(Order order) ;

    void updateOrderStatus(Order order);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getOrdersDTOByStatus(Order order);

    void createAnOrder(Order order);

}
