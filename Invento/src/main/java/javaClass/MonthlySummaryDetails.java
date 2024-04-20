package javaClass;

public class MonthlySummaryDetails {

    private String month;
    private int orders;
    private float earnings;

    public MonthlySummaryDetails(String month, int orders, float earnings) {
        this.month = month;
        this.orders = orders;
        this.earnings = earnings;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public float getEarnings() {
        return earnings;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }
}
