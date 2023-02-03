package com.example.test02;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSiginButtion;

    @FXML
    private Button loginSingUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSiginButtion.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else
                System.out.println("Error");
        });
        loginSingUpButton.setOnAction(actionEvent -> {
            openNewScene(loginSingUpButton, "register.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);

        try (ResultSet resultSet = dataBaseHandler.getUser(user)) {
            int counter = 0;
            while (resultSet.next()) {
                counter++;
            }

            if (counter >= 1) {
                openNewScene(authSiginButtion, "app.fxml");
            } else {
                Shake userLoginAnim = new Shake(login_field);
                Shake userPasswordAnim = new Shake(password_field);
                userPasswordAnim.playAnim();
                userLoginAnim.playAnim();
            }
        } catch (SQLException | RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    public void openNewScene(Button button, String window) {
        button.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(window));
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
    }
}
