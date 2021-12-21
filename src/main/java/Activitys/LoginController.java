package Activitys;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class LoginController {


    public Button registrationBtn;
    @FXML
        private TextField emailIdField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button submitButton;

        @FXML
        private Button loginBtn;

        //@FXML
        //private MenuBar menuBar;

        //@FXML
        //private TableView tableview1 = new TableView();



        @FXML
        public void login(ActionEvent event) throws SQLException, IOException, ParseException {

            Window owner = submitButton.getScene().getWindow();



            System.out.println(emailIdField.getText());
            System.out.println(passwordField.getText());

            if (emailIdField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter your email id");
                return;
            }
            if (passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a password");
                return;
            }

            String emailId = emailIdField.getText();
            String password = passwordField.getText();

            JdbcDao jdbcDao = new JdbcDao();
            if (jdbcDao.selectRecord(emailId, password)==true) {
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!",
                        "Welcome");

                //Formulas formulas = new Formulas();
                //formulas.formulas();

                DatabaseView databaseView = new DatabaseView();
                databaseView.createFXML();



            }else{
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Denied!",
                        "Please enter correct Email and Password");
            }



        }

        @FXML
        private void register(ActionEvent event) throws IOException {

            Parent window1; //we need to load the layout that we want to swap
            window1 = FXMLLoader.load(getClass().getResource("/layout/registration_form.fxml"));

            //Scene newSceneWindow1 = new Scene(window1);

            Stage mainStage;
            //mainStage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            mainStage = Main.parentWindow;
            mainStage.getScene().setRoot(window1); //we dont need to change whole sceene, only set new root.
        }

        private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
}
