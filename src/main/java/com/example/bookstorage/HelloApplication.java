package com.example.bookstorage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 680);
        stage.setTitle("BookStorage");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("libros.png"))));
        stage.show();

        // Establecer la conexión a la base de datos SQLite
        Connection conn = conetSQL.connect();
    }

    public static void main(String[] args) {
        launch();
    }
}