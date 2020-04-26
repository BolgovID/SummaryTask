package ua.nure.bolhov.SummaryTask4.db.entity;

import java.sql.Timestamp;

public class Order extends Entity {

    private boolean withDriver;
    private Timestamp fromDate;
    private Timestamp toDate;
    String reasonDeny;
    long idCar;
    long idStatus;
    long idUser;

    public boolean isWithDriver() {
        return withDriver;
    }

    public void setWithDriver(boolean withDriver) {
        this.withDriver = withDriver;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public String getReasonDeny() {
        return reasonDeny;
    }

    public void setReasonDeny(String reasonDeny) {
        this.reasonDeny = reasonDeny;
    }

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


    @Override
    public String toString() {
        return "Order{" +
                "withDriver=" + withDriver +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", reasonDeny='" + reasonDeny + '\'' +
                ", idCar=" + idCar +
                ", idStatus=" + idStatus +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Order w = (Order) o;
        return getId().compareTo(w.getId());
    }
}
