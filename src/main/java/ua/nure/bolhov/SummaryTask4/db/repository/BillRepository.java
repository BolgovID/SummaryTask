package ua.nure.bolhov.SummaryTask4.db.repository;

import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;

import java.util.List;

public interface BillRepository {

    void createBill(Bill bill);

    List<Bill> getBills(Order order);

    void updateBill(Bill bill);
}
