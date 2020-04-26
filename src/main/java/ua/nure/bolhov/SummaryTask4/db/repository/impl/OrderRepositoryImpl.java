package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.OrderDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.db.repository.OrderRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAORepository ORDER_DAO_REPOSITORY;
    private final DBManager DB_MANAGER;

    public OrderRepositoryImpl(OrderDAORepository orderDAORepository, DBManager dbManager) {
        ORDER_DAO_REPOSITORY = orderDAORepository;
        DB_MANAGER = dbManager;
    }

    @Override
    public void updateOrder(Order order) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return ORDER_DAO_REPOSITORY.updateOrder(order);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });

    }

    @Override
    public void updateOrderStatus(Order order) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return ORDER_DAO_REPOSITORY.updateOrderStatus(order);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public List<OrderDTO> getOrderDTOByUser(User user) {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return ORDER_DAO_REPOSITORY.getOrderDTOByUser(user);
            } catch (DBException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public List<OrderDTO> getOrdersDTOByStatus(Order order) {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return ORDER_DAO_REPOSITORY.getOrdersDTOByStatus(order);
            } catch (DBException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void createAnOrder(Order order) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return ORDER_DAO_REPOSITORY.insertOrder(order);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}
