package javaClass;

public class YearlyOrderSummary {


    private String Year;
    private int orders;
    private float earnings;

    public YearlyOrderSummary(String year, int orders, float earnings) {
        Year = year;
        this.orders = orders;
        this.earnings = earnings;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
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
