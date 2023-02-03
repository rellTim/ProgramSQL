package com.example.test02;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("ITY Program");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        stage.setTitle("Timur");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}