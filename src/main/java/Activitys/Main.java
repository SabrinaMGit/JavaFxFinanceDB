package Activitys;

import de.codecentric.centerdevice.MenuToolkit;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.sql.Connection;
import java.sql.ResultSet;


public class Main extends Application {

    public static Stage parentWindow;
    public int height = 450;
    public int width = 650;

    @Override
    public void start(Stage stage) throws Exception{
        System.out.println(getClass());
        parentWindow = stage;


        Parent root = FXMLLoader.load(getClass().getResource("/layout/login_form.fxml"));
        stage.setTitle("Bordereau GEICO UK Database");
        stage.setScene(new Scene(root, width, height));
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);
        //stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/drawable/launch.png"))));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/drawable/launch.png")));
        stage.show();

        //Formulas formulas = new Formulas();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

