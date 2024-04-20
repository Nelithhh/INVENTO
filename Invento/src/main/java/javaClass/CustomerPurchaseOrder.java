package javaClass;

public class CustomerPurchaseOrder {
    private String cusContact;
    private String proCode;
    private String date;
    private String time;
    private int quantity;
    private float proPrice;
    private float orderPrice;
    private String cusName;
    private String proName;

    public CustomerPurchaseOrder(String cusContact, String proCode, String date, String time, int quantity, float proPrice, float orderPrice, String cusName, String proName) {
        this.cusContact = cusContact;
        this.proCode = proCode;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.proPrice = proPrice;
        this.orderPrice = orderPrice;
        this.cusName = cusName;
        this.proName = proName;
    }

    public String getCusContact() {
        return cusContact;
    }

    public void setCusContact(String cusContact) {
        this.cusContact = cusContact;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
