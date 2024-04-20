package com.invento.invento;

import javaClass.DailyOrderSummary;
import javaClass.DatabaseConnection;
import javaClass.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    public String  userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



    DatabaseConnection connectNow = new DatabaseConnection();

    LocalDate currentDate = LocalDate.now();
    String formattedDate=currentDate.toString();
    java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);


    private final OrderController orderController = new OrderController();


    // Inject the TableView from the other controller
    public void setTableView(TableView<DailyOrderSummary> tableView) {
        this.tblDailyOrderSummary = tableView;
    }


    @FXML
    private BarChart<String, Number> orderBarChart;

    @FXML
    private TableColumn<String, DailyOrderSummary> clmDailyorderDate;

    @FXML
    private TableColumn<Integer, DailyOrderSummary> clmDailyOrderNumber;

    // This TableView will be set from the other controller
    private TableView<DailyOrderSummary> tblDailyOrderSummary;

    @FXML
    private Button btnLoad;

    @FXML
    void loadChart(ActionEvent event) {
        if (tblDailyOrderSummary != null) {
            orderController.displayDailyOrderSummaryInChart(orderBarChart, clmDailyorderDate, clmDailyOrderNumber);
        }
    }

    @FXML
    public void initialize() {
        // Initialize method
    }


}
