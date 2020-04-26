package ua.nure.bolhov.SummaryTask4.web.service.impl;

import ua.nure.bolhov.SummaryTask4.db.dto.OrderDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Bill;
import ua.nure.bolhov.SummaryTask4.db.entity.Order;
import ua.nure.bolhov.SummaryTask4.db.repository.BillRepository;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;
import ua.nure.bolhov.SummaryTask4.web.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillRepository BILL_REPOSITORY;

    public BillServiceImpl(BillRepository BILL_REPOSITORY) {
        this.BILL_REPOSITORY = BILL_REPOSITORY;
    }


    @Override
    public void createBill(AcceptCarBean acceptCarBean) {
        BILL_REPOSITORY.createBill(getEntity(acceptCarBean));
    }

    @Override
    public void createBill(TreatmentOrderBean treatmentOrderBean) {
        BILL_REPOSITORY.createBill(getEntity(treatmentOrderBean));
    }

    private Bill getEntity(TreatmentOrderBean treatmentOrderBean) {
        Bill bill = new Bill();
        bill.setOrderId(treatmentOrderBean.getId());
        bill.setCost(treatmentOrderBean.getCost());
        bill.setReason(treatmentOrderBean.getComment());
        return bill;
    }

    private Bill getEntity(AcceptCarBean acceptCarBean) {
        Bill bill = new Bill();
        bill.setOrderId(acceptCarBean.getId());
        bill.setCost(acceptCarBean.getCost());
        bill.setReason(acceptCarBean.getComment());
        return bill;
    }

    @Override
    public List<Bill> getBillsForOneOrder(OrderDTO orderDTO) {
        return BILL_REPOSITORY.getBills(getEntity(orderDTO));
    }



    @Override
    public void payForBill(PayOrderBean payOrderBean) {
        BILL_REPOSITORY.updateBill(getEntity(payOrderBean));
    }

    private Bill getEntity(PayOrderBean payOrderBean) {
        Bill bill = new Bill();
        bill.setId(payOrderBean.getIdBill());
        bill.setPaid(true);
        return bill;
    }

    private Order getEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        return order;
    }
}
