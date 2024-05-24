package org.example.mywarehouseclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import org.example.mywarehouseclient.MainApplication;
import org.example.mywarehouseclient.entity.MedicationEntity;
import org.example.mywarehouseclient.service.MedicationService;

import java.util.Optional;

@Setter
public class MainController {

    private Optional<MedicationEntity> medication = Optional.empty();

    MedicationService service = new MedicationService();

    @FXML
    private TableView<MedicationEntity> MedicationTable;

    @FXML
    private TableColumn<MedicationEntity, String> column1;

    @FXML
    private TableColumn<MedicationEntity, String> column10;

    @FXML
    private TableColumn<MedicationEntity, String> column2;

    @FXML
    private TableColumn<MedicationEntity, String> column3;

    @FXML
    private TableColumn<MedicationEntity, String> column4;

    @FXML
    private TableColumn<MedicationEntity, String> column5;

    @FXML
    private TableColumn<MedicationEntity, String> column6;

    @FXML
    private TableColumn<MedicationEntity, String> column7;

    @FXML
    private TableColumn<MedicationEntity, String> column8;

    @FXML
    private TableColumn<MedicationEntity, String> column9;

    @FXML
    private TableColumn<MedicationEntity, String> column11;

    @FXML
    private Button myButton;

    @FXML
    private Button myButtonDelete;

    @FXML
    void AnnotationAction(ActionEvent event) {

    }

    @FXML
    void StorageLocationAction(ActionEvent event) {
        MainApplication.showStorageLocationDialog();

    }

    @FXML
    void ManufacturerCompanyAction(ActionEvent event) {
        MainApplication.showManufacturerCompanyDialog();

    }

    @FXML
    void addOrChangeAnnotationAction(ActionEvent event) {
        MainApplication.showAnnotationDialog();
    }

    @FXML
    void addOrChangeMedicationAction(ActionEvent event) {
        Optional<MedicationEntity> medication = Optional.empty();
        MainApplication.showMedicationDialogAdd(medication);
    }

    @FXML
    void changeMedicationAction(ActionEvent event) {
        Optional<MedicationEntity> medication = Optional.of(MedicationTable.getSelectionModel().getSelectedItem());
        MainApplication.showMedicationDialogUpdate(medication);
    }

    @FXML
    void deleteMedicationAction(ActionEvent event) {
        MedicationEntity medicationDelete = MedicationTable.getSelectionModel().getSelectedItem();
        if (medicationDelete != null) {
            service.delete(medicationDelete);
            MedicationTable.getItems().remove(medicationDelete);
        }
    }

    @FXML
    void addOrChangefilterAction(ActionEvent event) {

    }

    @FXML
    void filter1(ActionEvent event) {
        // Фильтрация по наличию рецепта
        filterByRecipe();
    }

    @FXML
    void filter2(ActionEvent event) {
        // Фильтрация без рецепта
        filterByNoRecipe();
    }

    @FXML
    void filter3(ActionEvent event) {
        initialize();
    }

    private void filterByRecipe() {
        // Очищаем таблицу
        MedicationTable.getItems().clear();

        // Выполняем фильтрацию по рецепту
        service.filterByRecipe1Medication();

        // Обновляем таблицу новыми данными
        column1.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("name"));
        column3.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateReceipt"));
        column4.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateManufacture"));
        column5.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("expirationDate"));
        column6.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("prices"));
        column7.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("remains"));
        column8.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("annotation"));
        column9.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("storageLocation"));
        column10.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("manufacturerCompany"));
        column11.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("recipe"));
        MedicationTable.setItems(service.getData());
        MedicationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void filterByNoRecipe() {
        // Очищаем таблицу
        MedicationTable.getItems().clear();

        // Выполняем фильтрацию по отсутствию рецепта
        service.filterByRecipe2Medication();

        // Обновляем таблицу новыми данными
        column1.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("name"));
        column3.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateReceipt"));
        column4.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateManufacture"));
        column5.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("expirationDate"));
        column6.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("prices"));
        column7.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("remains"));
        column8.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("annotation"));
        column9.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("storageLocation"));
        column10.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("manufacturerCompany"));
        column11.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("recipe"));
        MedicationTable.setItems(service.getData());
        MedicationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }



    @FXML
    private void initialize() {

        try {

            // Очищаем таблицу
            MedicationTable.getItems().clear();

            // получаем все книги с сервера
            service.getAll();
            // связываем поля таблицы со столбцами
            column1.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("id"));
            column2.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("name"));
            column3.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateReceipt"));
            column4.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("dateManufacture"));
            column5.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("expirationDate"));
            column6.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("prices"));
            column7.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("remains"));
            column8.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("annotation"));
            column9.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("storageLocation"));
            column10.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("manufacturerCompany"));
            column11.setCellValueFactory(new PropertyValueFactory<MedicationEntity, String>("recipe"));
            MedicationTable.setItems(service.getData());
            MedicationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Проблемы с сервером");
            alert.showAndWait();
        }
    }

    public void setMedication(Optional<MedicationEntity> medication) {
        this.medication = medication;
        if (medication.isPresent()){
            if (medication.get().getId() != null) {
                service.update(medication.get(),MedicationTable.getSelectionModel().getSelectedItem());
            }
            else{
                service.add(medication.get());
            }
        }
    }

}


