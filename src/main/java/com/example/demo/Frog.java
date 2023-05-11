package com.example.demo;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Frog{
    private ImageView standing;
    private ImageView jumping;
    private double facing;

    public Frog(ImageView standing, ImageView jumping, double facing, double x, double y, double w) {
        this.standing = standing;
        this.jumping = jumping;
        this.facing = facing;
        jumping.setVisible(false);
        for (ImageView elem : new ImageView[] {standing, jumping}) {
            elem.setX(x);
            elem.setY(y);
            elem.setPreserveRatio(true);
            elem.setFitWidth(w);
        }
    }

    public ImageView getStanding() {
        return standing;
    }

    public ImageView getJumping() {
        return jumping;
    }

    public double getFacing() {
        return facing;
    }

    public void setFacing(double facing) {
        this.facing = facing;
    }

    public void switchIcons() {
        if (standing.isVisible()) {
            standing.setVisible(false);
            jumping.setVisible(true);
        }
        else {
            standing.setVisible(true);
            jumping.setVisible(false);
        }
    }

    public void jump() {
        double angle = Math.random() * 360 - 180;
        double length = 100 + Math.random() * 100;

        RotateTransition rt= new RotateTransition(Duration.millis(100), standing);
        rt.setByAngle(angle);
        rt.play();

        double xStart = standing.getX();
        double yStart = standing.getY();
        double xEnd = xStart + Math.cos(facing) * length;
        double yEnd = yStart + Math.sin(facing) * length;

        TranslateTransition tt = new TranslateTransition(Duration.millis(100), jumping);
        tt.setToX(xEnd);
        tt.setToY(yEnd);

        tt.play();
    }
}
