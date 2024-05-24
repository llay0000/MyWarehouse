package org.example.mywarehouseclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.mywarehouseclient.entity.AnnotationEntity;
import org.example.mywarehouseclient.entity.ManufacturerCompanyEntity;
import org.example.mywarehouseclient.entity.MedicationEntity;
import org.example.mywarehouseclient.entity.StorageLocationEntity;
import org.example.mywarehouseclient.service.AnnotationService;
import org.example.mywarehouseclient.service.ManufacturerCompanyService;
import org.example.mywarehouseclient.service.StorageLocationService;

import java.time.LocalDate;
import java.util.Optional;

@Setter
public class MedicationControllerAdd {

    @Setter
    @Getter
    private Optional<MedicationEntity> medication;

    private final AnnotationService annotationService = new AnnotationService();

    private final StorageLocationService storageLocationService = new StorageLocationService();

    private final ManufacturerCompanyService manufacturerCompanyService = new ManufacturerCompanyService();

    @FXML
    private ComboBox<AnnotationEntity> comboBox1;

    @FXML
    private ComboBox<StorageLocationEntity> comboBox2;

    @FXML
    private ComboBox<ManufacturerCompanyEntity> comboBox3;

    @FXML
    private Button myButton1;

    @FXML
    private Button myButton2;

    @FXML
    private TextField text1;

    @FXML
    private DatePicker text2;

    @FXML
    private DatePicker text3;

    @FXML
    private DatePicker text4;

    @FXML
    private TextField text5;

    @FXML
    private TextField text6;

    @FXML
    private TextField text7;

    @FXML
    void addAction(ActionEvent event) {

        String name = text1.getText();
        LocalDate dateReceipt = text2.getValue();
        LocalDate dateManufacture = text3.getValue();
        LocalDate expirationDate = text4.getValue();
        double prices = Double.parseDouble(text5.getText());
        int remains = Integer.parseInt(text6.getText());
        String recipe = text7.getText();
        AnnotationEntity selectedAnnotation = comboBox1.getValue();
        StorageLocationEntity selectedStorageLocation = comboBox2.getValue();
        ManufacturerCompanyEntity selectedCompanyEntity = comboBox3.getValue();

            MedicationEntity temp = MedicationEntity.builder()
                    .name(name)
                    .dateReceipt(dateReceipt)
                    .dateManufacture(dateManufacture)
                    .expirationDate(expirationDate)
                    .prices(prices)
                    .remains(remains)
                    .recipe(recipe)
                    .annotation(selectedAnnotation)
                    .storageLocation(selectedStorageLocation)
                    .manufacturerCompany(selectedCompanyEntity)
                    .build();

            System.out.println(temp);

            Stage stage = (Stage) myButton1.getScene().getWindow();

            if (medication.isEmpty()){
                medication = Optional.of(temp);
            } else {
                temp.setId(medication.get().getId());
                medication = Optional.of(temp);
            }
            // Закрываем окно
            stage.close();
    }

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage) myButton2.getScene().getWindow();
        stage.close();
    }

    public void start() {

        if (medication.isPresent()){
            text1.setText(medication.get().getName());
            text2.setValue(medication.get().getDateReceipt());
            text3.setValue(medication.get().getDateManufacture());
            text4.setValue(medication.get().getExpirationDate());
            text5.setText(String.valueOf(medication.get().getPrices()));
            text6.setText(String.valueOf(medication.get().getRemains()));
            text7.setText(medication.get().getRecipe());
            comboBox1.setValue(medication.get().getAnnotation());
            comboBox2.setValue(medication.get().getStorageLocation());
            comboBox3.setValue(medication.get().getManufacturerCompany());
        }
    }

    @FXML
    private void initialize(){
        annotationService.getAll();
        storageLocationService.getAll();
        manufacturerCompanyService.getAll();
        comboBox1.setItems(annotationService.getData());
        comboBox2.setItems(storageLocationService.getData());
        comboBox3.setItems(manufacturerCompanyService.getData());

        setButtonStyle(myButton1);
        setButtonStyle(myButton2);
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: #ffffff;");

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #8f8f8f; -fx-text-fill: #ffffff;");
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: #ffffff;");
        });
    }

}
