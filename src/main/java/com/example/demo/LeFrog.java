package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class LeFrog extends Application {

    private static void wewewow(ImageView frog) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), frog);
        tt.setByY(-200);
        tt.setByX(-200);
        tt.setAutoReverse(true);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.play();
    }

    private static void jump(ImageView frog) {

        RotateTransition rt = new RotateTransition(Duration.millis(100), frog);

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), frog);

        double byX = Math.random() * 100 + 30;
        double byY = Math.random() * 100 + 30;
        int randint = (int) (Math.random() * 4);
        if (randint % 2 == 0) {
            byX = -byX;
        }
        if (randint > 1) {
            byY = -byY;
        }

        double angle1 = Math.toDegrees(Math.atan(-byY / byX));
        double angle;
        if (byX >= 0 && -byY >= 0) angle = angle1;
        else if (byX < 0 && -byY < 0) angle = angle1 + 180;
        else if (byX < 0) angle = angle1 + 90;
        else angle = angle1;
        System.out.println("byX: " + byX + ", byY: " + -byY + ", angle: " + angle);
        rt.setByAngle(frog.getRotate() + angle);

        tt.setByX(byX);
        tt.setByY(byY);
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

        AtomicInteger points = new AtomicInteger();
        Text pointsText = new Text("0");
        pointsText.setFill(Color.BLACK);
        pointsText.setX(960);
        pointsText.setY(100);
        pointsText.setVisible(true);
        pointsText.setFont(new Font("Courier New", 100));
        pointsText.setVisible(false);

        ImageView quitPilt = new ImageView(new Image(new FileInputStream("sprites\\quit.png")));
        quitPilt.setPreserveRatio(true);
        quitPilt.setX(10);
        quitPilt.setY(600);
        root.getChildren().add(quitPilt);
        quitPilt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("edetabel.txt", true)));) {
                    out.println(pointsText.getText() + " punkti " + LocalDateTime.now());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Platform.exit();
            }
        });

        frog.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent firstClick) {
                FadeTransition ft = new FadeTransition(Duration.millis(1500), title);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
                pointsText.setVisible(true);

                frog.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        jump(frog);
                        //wewewow(frog);
                        points.getAndIncrement();
                        pointsText.setText(String.valueOf(points));
                    }
                });
            }
        });

        root.getChildren().add(frog);
        root.getChildren().add(pointsText);

        stage.setTitle("Le Frog");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}