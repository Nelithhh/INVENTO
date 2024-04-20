<?php
class DBConnection {
    private $host = "localhost";
    private $username = "root";
    private $password = "";
    private $database = "invento";
    private $conn;

    // Constructor to establish the database connection
    public function __construct() {
        $this->conn = new mysqli($this->host, $this->username, $this->password, $this->database);
        if ($this->conn->connect_error) {
            die("Connection failed: " . $this->conn->connect_error);
        }
    }

    // Method to get the database connection
    public function getConnection() {
        return $this->conn;
    }

    // Destructor to close the database connection
    public function __destruct() {
        $this->conn->close();
    }
}
?>
