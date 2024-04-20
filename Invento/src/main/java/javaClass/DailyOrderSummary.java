package javaClass;

public class DailyOrderSummary {
    private String date;
    private int orders;
    private float earnings;

    public DailyOrderSummary(String date, int orders, float earnings) {
        this.date = date;
        this.orders = orders;
        this.earnings = earnings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
