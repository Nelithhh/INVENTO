<?php
session_start();


require_once 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
    if (!empty($_POST['email']) && !empty($_POST['pass'])) {
        
        $dbConnection = new DBConnection();
        $conn = $dbConnection->getConnection();

        
        $email = $conn->real_escape_string($_POST['email']);
        $password = $conn->real_escape_string($_POST['pass']);

        
        $sql = "SELECT * FROM user WHERE UserEmail='$email' AND password='$password'";
        $result = $conn->query($sql);

        if ($result->num_rows == 1) {
            
            $_SESSION['email'] = $email;
            header("Location: profile.php"); 
            exit();
        } else {
            
            $error_message = "Invalid email or password!";
        }

        
        $conn->close();
    } else {
        
        $error_message = "Please enter both email and password!";
    }
}
?>
