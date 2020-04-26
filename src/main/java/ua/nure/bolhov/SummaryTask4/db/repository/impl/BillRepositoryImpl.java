package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.BillDAORepository;
import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.repository.BillRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class BillRepositoryImpl implements BillRepository {

    private final DBManager DB_MANAGER;
    private final BillDAORepository BILL_DAO_REPOSITORY;

    public BillRepositoryImpl(DBManager dbManager, BillDAORepository billDAORepository) {
        DB_MANAGER = dbManager;
        BILL_DAO_REPOSITORY = billDAORepository;
    }

    @Override
    public void createBill(Bill bill) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return BILL_DAO_REPOSITORY.insertBill(bill);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public List<Bill> getBills(Order order) {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return BILL_DAO_REPOSITORY.getBills(order);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public void updateBill(Bill bill) {
         DB_MANAGER.doTransaction(() -> {
            try {
                return BILL_DAO_REPOSITORY.updateBill(bill);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}
