package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LeFrog extends Application {

    //TODO: konn peaks keerama ja siis hüppama selles suunas kuhu ta vaatab, aga ekraani pealt välja hüppamine oleks kehv
    private static void jump(ImageView frog) {
        double angle = Math.random() * 360 - 180;
        double length = 100 + Math.random() * 100;

        RotateTransition rt= new RotateTransition(Duration.millis(100), frog);
        rt.setByAngle(angle);
        rt.play();

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), frog);

        double xStart = frog.getX();
        double yStart = frog.getY();

        for (int i = 0; i < 4; i++) {
            double finalAngle = angle + i * 90;
            double xEnd = xStart + Math.cos(frog.rotateProperty().doubleValue()) * length;
            double yEnd = yStart + Math.sin(frog.rotateProperty().doubleValue()) * length;
            if(xEnd < 1100 && yEnd < 700 && xEnd < 0 && yEnd < 0) {
                tt.setToX(xEnd);
                tt.setToY(yEnd);
            }
        }


        rt.play();
        tt.play();
    }
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Group root = new Group();

        ImageView title = new ImageView(new Image(new FileInputStream("sprites\\title.png")));
        title.setPreserveRatio(true);
        title.setFitWidth(1000);
        title.setX(130);
        root.getChildren().add(title);

        ImageView frog = new ImageView(new Image(new FileInputStream("sprites\\frogStanding.png")));
        frog.setX(570);
        frog.setY(600);
        frog.setPreserveRatio(true);
        frog.setFitWidth(60);

        frog.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent firstClick) {
                FadeTransition ft = new FadeTransition(Duration.millis(1500), title);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
                frog.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        jump(frog);
                    }
                });
            }
        });
        root.getChildren().add(frog);

        stage.setTitle("Le Frog");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}