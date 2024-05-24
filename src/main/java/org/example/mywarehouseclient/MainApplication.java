package org.example.mywarehouseclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.mywarehouseclient.controller.MainController;
import org.example.mywarehouseclient.controller.MedicationControllerAdd;
import org.example.mywarehouseclient.controller.MedicationControllerUpdate;
import org.example.mywarehouseclient.entity.MedicationEntity;

import java.io.IOException;
import java.util.Optional;

public class MainApplication extends Application {

    private FXMLLoader fxmlLoader;
    private static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Мой склад");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

        mainController = fxmlLoader.getController();
        primaryStage.show();
    }

    public static void showAnnotationDialog() {
        MainApplication.showDialog("annotation-viem.fxml", "Аннотация");
    }

    public static void showManufacturerCompanyDialog() {
        MainApplication.showDialog("manufacturerCompany-view.fxml", "Фирма-производителя");
    }

    public static void showStorageLocationDialog() {
        MainApplication.showDialog("storageLocation-view.fxml", "Места хранения");

    }

    public static void showMedicationDialogAdd(Optional<MedicationEntity> medication) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("medication-view-add.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с препаратом");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            MedicationControllerAdd controller = loader.getController();

            controller.setMedication(medication);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            medication = controller.getMedication();
            System.out.println(medication);
            mainController.setMedication(medication);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMedicationDialogUpdate(Optional<MedicationEntity> medication) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("medication-view-update.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с препаратом");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            MedicationControllerUpdate controller = loader.getController();

            controller.setMedication(medication);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            medication = controller.getMedication();
            System.out.println(medication);
            mainController.setMedication(medication);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDialog(String nameView, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}