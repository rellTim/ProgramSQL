package com.example.test02;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerRegister extends javafx.application.Application {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField siingUpCountry;

    @FXML
    private CheckBox sinUpCheckBoxFemMale;

    @FXML
    private Button singUpButton;

    @FXML
    private CheckBox singUpCheckBoxMale;

    @FXML
    private TextField singUpLastName;

    @FXML
    private TextField singUpName;
    @FXML
    private Button singBack;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage.setTitle("ITY Program");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void initialize() {
        singUpButton.setOnAction(actionEvent -> {
            singUpNewUser();
        });
        singBack.setOnAction(event -> {
            singBack.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("ITY Program");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

    private void singUpNewUser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String firstName = singUpName.getText();
        String lastName  = singUpLastName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String location = siingUpCountry.getText();
        String gender;
        if (singUpCheckBoxMale.isSelected())
            gender = singUpCheckBoxMale.getText();
        else
            gender = sinUpCheckBoxFemMale.getText();

        User user = new User(firstName, lastName, userName, password, location, gender);

        dataBaseHandler.singUpUser(user);
    }
}
