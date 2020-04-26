package ua.nure.bolhov.SummaryTask4.web.service;

import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;

import java.util.List;

public interface BillService {

    void createBill(AcceptCarBean acceptCarBean);

    void createBill(TreatmentOrderBean treatmentOrderBean);

    List<Bill> getBillsForOneOrder(OrderDTO order);

    void payForBill(PayOrderBean payOrderBean);

}
