package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LeFrog extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Canvas title = new Canvas(800,600);
        GraphicsContext gc = title.getGraphicsContext2D();
        Image text = new Image(new FileInputStream("C:\\Users\\karlk\\Documents\\2023Kevad\\OOP\\LeFrog\\sprites\\title.png")); // TODO: teha nii, et see ei peaks kasutama minu arvuti spetsiifilist pathi
        gc.drawImage(text, 50, 50, title.widthProperty().intValue() - 100, title.widthProperty().intValue() / 2 - 50);
        root.getChildren().add(title);
        Image silhouette = new Image(new FileInputStream("C:\\Users\\karlk\\Documents\\2023Kevad\\OOP\\LeFrog\\sprites\\frogSilhouette.png"));
        ImageView silhouetteView = new ImageView(silhouette);
        root.getChildren().add(silhouetteView);
        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("Le Frog");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}