package com.invento.invento;

import javaClass.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public OrderController() {
        // Default constructor implementation
    }

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmCusContact;

    @FXML
    private TableColumn<Integer, CustomerPurchaseOrder> clmQuantity;

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmCusName;

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmDate;

    @FXML
    private TableColumn<Float, CustomerPurchaseOrder> clmOrderPrice;

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmProName;

    @FXML
    private TableColumn<Float, CustomerPurchaseOrder> clmProPrice;

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmTime;

    @FXML
    private TableColumn<String, CustomerPurchaseOrder> clmProCode;

    @FXML
    private TableView<CustomerPurchaseOrder> tblCustomerOrder;

    //DailyOrderSummary
    @FXML
    private TableView<DailyOrderSummary> tblDailyOrderSummary;

    @FXML
    private TableColumn<String , DailyOrderSummary> clmDailyOrderEarnings;

    @FXML
    private TableColumn<Integer, DailyOrderSummary> clmDailyOrderNumber;

    @FXML
    private TableColumn<Float, DailyOrderSummary> clmDailyorderDate;

    //MonthlyOrderSummary

    @FXML
    private TableColumn<Float, MonthlySummaryDetails> clmMonthlyEarnings;

    @FXML
    private TableColumn<String, MonthlySummaryDetails> clmMonthlyMonth;

    @FXML
    private TableColumn<Integer, MonthlySummaryDetails> clmMonthlyOrders;

    @FXML
    private TableView<MonthlySummaryDetails> tblMonthlySummary;

    //YearlySummary

    @FXML
    private TableColumn<Float, YearlyOrderSummary> clmYearlyEarnings;

    @FXML
    private TableColumn<Integer, YearlyOrderSummary> clmYearlyOrders;

    @FXML
    private TableColumn<String, YearlyOrderSummary> clmYearlyYear;

    @FXML
    private TableView<YearlyOrderSummary> tblYearlySummary;

    private String userEmail;
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    DatabaseConnection connectNow = new DatabaseConnection();

    public void initialize(URL url, ResourceBundle rb) {
//        tblUser.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 1) {
//                handleTableClick();
//            }
//        });

    }

    public void loadData(){

        loadTable();


        clmCusContact.setCellValueFactory(new PropertyValueFactory<>("cusContact"));
        clmProCode.setCellValueFactory(new PropertyValueFactory<>("proCode"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        clmProPrice.setCellValueFactory(new PropertyValueFactory<>("proPrice"));
        clmOrderPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
        clmCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        clmProName.setCellValueFactory(new PropertyValueFactory<>("proName"));

    }

    @FXML
    private void loadTable(){
        Connection connectDB = connectNow.getConnection();
        ObservableList<CustomerPurchaseOrder> cusPurOrder= FXCollections.observableArrayList();
        String sql="select * from customerpurchasedetails where userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cusPurOrder.add(new CustomerPurchaseOrder(
                        resultSet.getString("CustomerContact"),
                        resultSet.getString("ProductCode"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("Quantity"),
                        resultSet.getFloat("productPrice"),
                        resultSet.getFloat("OrderPrice"),
                        resultSet.getString("CustomerName"),
                        resultSet.getString("ProductName")
                ));
            }

            // Set the items of your TableView to the ObservableList after the loop
            tblCustomerOrder.setItems(cusPurOrder);

            connectDB.close();
        }catch(Exception e){e.printStackTrace();};

    }

    //Daily Summary
    public void loadData02(){

        loadTable02();

        clmDailyorderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmDailyOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orders"));
        clmDailyOrderEarnings.setCellValueFactory(new PropertyValueFactory<>("earnings"));


    }

    @FXML
    private void loadTable02(){
        Connection connectDB = connectNow.getConnection();
        ObservableList<DailyOrderSummary> dailyOrder= FXCollections.observableArrayList();
        String sql="select * from dailyordersummary where userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dailyOrder.add(new DailyOrderSummary(

                        resultSet.getString("date"),
                        resultSet.getInt("TotalOrders"),
                        resultSet.getFloat("TotalEarnings")

                ));
            }

            // Set the items of your TableView to the ObservableList after the loop
            tblDailyOrderSummary.setItems(dailyOrder);

            connectDB.close();
        }catch(Exception e){e.printStackTrace();};

    }

    public void loadData03(){

        loadTable03();

        clmMonthlyMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        clmMonthlyOrders.setCellValueFactory(new PropertyValueFactory<>("orders"));
        clmMonthlyEarnings.setCellValueFactory(new PropertyValueFactory<>("earnings"));


    }

    @FXML
    private void loadTable03(){
        Connection connectDB = connectNow.getConnection();
        ObservableList<MonthlySummaryDetails> monthlyOrder= FXCollections.observableArrayList();
        String sql="select * from monthlyordersummary where userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                monthlyOrder.add(new MonthlySummaryDetails(

                        resultSet.getString("Month"),
                        resultSet.getInt("TotalOrders"),
                        resultSet.getFloat("TotalEarnings")

                ));
            }

            // Set the items of your TableView to the ObservableList after the loop
           tblMonthlySummary.setItems(monthlyOrder);

            connectDB.close();
        }catch(Exception e){e.printStackTrace();};

    }

    public void loadData04(){

        loadTable04();

        clmYearlyYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        clmYearlyOrders.setCellValueFactory(new PropertyValueFactory<>("orders"));
        clmYearlyEarnings.setCellValueFactory(new PropertyValueFactory<>("earnings"));


    }

    @FXML
    private void loadTable04(){
        Connection connectDB = connectNow.getConnection();
        ObservableList<YearlyOrderSummary> yearlyOrder= FXCollections.observableArrayList();
        String sql="select * from yearlyordersummary where userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                yearlyOrder.add(new YearlyOrderSummary(

                        resultSet.getString("year"),
                        resultSet.getInt("TotalOrders"),
                        resultSet.getFloat("TotalEarnings")

                ));
            }

            // Set the items of your TableView to the ObservableList after the loop
            tblYearlySummary.setItems(yearlyOrder);

            connectDB.close();
        }catch(Exception e){e.printStackTrace();};

    }


    public void displayDailyOrderSummaryInChart(BarChart<String, Number> barChart,
                                                TableColumn<String, DailyOrderSummary> dateColumn,
                                                TableColumn<Integer, DailyOrderSummary> orderNumberColumn) {
        ObservableList<XYChart.Data<String, Number>> chartData = FXCollections.observableArrayList();

        for (DailyOrderSummary summary : tblDailyOrderSummary.getItems()) {
            String date = summary.getDate(); // Retrieve date directly from DailyOrderSummary object
            int orderNumber = summary.getOrders(); // Retrieve order number directly from DailyOrderSummary object

            // Print out the retrieved data for debugging
            System.out.println("Date: " + date + ", Order Number: " + orderNumber);

            chartData.add(new XYChart.Data<>(date, orderNumber));
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setData(chartData);

        barChart.getData().clear();
        barChart.getData().add(series);
    }







}
