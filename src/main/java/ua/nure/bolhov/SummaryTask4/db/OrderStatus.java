package ua.nure.bolhov.SummaryTask4.db;

public enum OrderStatus {

    CONSIDERING, ACCEPTED, REJECTED, PAID, RETURNING, CLOSED;

    public static OrderStatus getOrder(String decide) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.toString().equalsIgnoreCase(decide)) {
                return orderStatus;
            }
        }
        return CONSIDERING;
    }

    public long getNumber() {
        return this.ordinal()+1;
    }


}
