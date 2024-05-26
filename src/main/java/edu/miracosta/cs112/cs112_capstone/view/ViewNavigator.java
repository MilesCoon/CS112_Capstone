package edu.miracosta.cs112.cs112_capstone.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator  {

    private static Stage mainStage;

    /**
     * Navigates from one scene to another, e.g. from the MainScene to the AddScene.
     * @param title The title to display on the stage
     * @param mainScene The new scene to load
     */
    public static void loadScene(String title, MainScene mainScene) {
        mainStage.setTitle(title);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void setStage(Stage stage) {
        mainStage = stage;
    }
}
