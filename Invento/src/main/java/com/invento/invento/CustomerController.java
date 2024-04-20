package com.invento.invento;

import javaClass.Customer;
import javaClass.DatabaseConnection;
import javaClass.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Customer, String> clmContact;

    @FXML
    private TableColumn<Customer, String> clmGmail;

    @FXML
    private TableColumn<Customer, String> clmName;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtGmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;


    private String userEmail;

    private String OldPhone;
    DatabaseConnection connectNow = new DatabaseConnection();

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        tblCustomer.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });

    }
    @FXML
    private void handleSearch(ActionEvent event) {
        String searchTerm = txtSearch.getText().trim();
        if (!searchTerm.isEmpty()) {
            displayEmployeeDetailsBySearch(searchTerm);
        }
    }

    public void displayEmployeeDetailsBySearch(String searchTerm) {
        Connection connectDB = connectNow.getConnection();
        String sql = "SELECT contact,name,email FROM customer WHERE userEmail=? AND (contact LIKE ?) LIMIT 1";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, searchTerm);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Customer resultCustomer = new Customer(
                        resultSet.getString("contact"),
                        resultSet.getString("name"),
                        resultSet.getString("email")

                );
                displayCustomerDetails(resultCustomer);
                OldPhone = txtContact.getText();
            } else {
                clearTextFields();
            }
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleTableClick() {
        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            displayCustomerDetails(selectedCustomer);
        }
    }

    private void displayCustomerDetails(Customer customer) {
        txtContact.setText(customer.getContact());
        txtName.setText(customer.getName());
        txtGmail.setText(customer.getEmail());
        OldPhone=txtContact.getText();
    }

    public void loadData(){
        loadTable();
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void loadTable(){
        Connection connectDB = connectNow.getConnection();
        ObservableList<Customer> customers= FXCollections.observableArrayList();
        String sql="select contact,name,email from customer where userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getString("contact"),
                        resultSet.getString("name"),
                        resultSet.getString("email")

                ));
            }

            // Set the items of your TableView to the ObservableList after the loop
            tblCustomer.setItems(customers);

            connectDB.close();
        }catch(Exception e){e.printStackTrace();};

    }
    @FXML private void Add(ActionEvent event){
        Connection connectDB = connectNow.getConnection();
        String sql="insert into customer(contact,name,email,userEmail) values(?,?,?,?)";
        try{
            PreparedStatement preparedStatement=connectDB.prepareStatement(sql);
            preparedStatement.setString(1,txtContact.getText());
            preparedStatement.setString(2,txtName.getText());
            preparedStatement.setString(3,txtGmail.getText());
            preparedStatement.setString(4,userEmail);
            preparedStatement.executeUpdate();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Registration");
            alert.setHeaderText("Customer Registration");
            alert.setContentText("New Customer Added Successfully!");

            alert.showAndWait();

            clearTextFields();

            loadData();
            connectDB.close();

        }catch (Exception e){e.printStackTrace();}


    }
    @FXML private void Edit(ActionEvent event){

        Connection connectDB = connectNow.getConnection();
        String sql="update customer set contact=?,name=?,email=? where contact=? AND userEmail=?";
        try{
            PreparedStatement preparedStatement=connectDB.prepareStatement(sql);
            preparedStatement.setString(1,txtContact.getText());
            preparedStatement.setString(2,txtName.getText());
            preparedStatement.setString(3,txtGmail.getText());
            preparedStatement.setString(4,OldPhone);
            preparedStatement.setString(5,userEmail);
            preparedStatement.executeUpdate();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Updation");
            alert.setHeaderText("Customer Updation");
            alert.setContentText("Customer Details updated successfully !");

            alert.showAndWait();

            clearTextFields();

            loadData();
            connectDB.close();

        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Updation");
            alert.setHeaderText("Employee Updation");
            alert.setContentText("To change the contact informat");
            e.printStackTrace();
        }


    }
    @FXML private void Delete(ActionEvent event) {

        Connection connectDB = connectNow.getConnection();
        String sql = "delete from customer where contact=? AND userEmail=?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, txtContact.getText());
            preparedStatement.setString(2, userEmail);
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Record Deleted");
            alert.setHeaderText("Customer record deleted");
            alert.setContentText("Customer Details deleted successfully !");

            alert.showAndWait();

            clearTextFields();

            loadData();
            connectDB.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Updation");
            alert.setHeaderText("Employee Updation");
            alert.setContentText("To change the contact informat");
            e.printStackTrace();
        }


    }
    private void clearTextFields(){
        txtContact.clear();
        txtGmail.clear();
        txtName.clear();
        txtSearch.clear();
    }
}
