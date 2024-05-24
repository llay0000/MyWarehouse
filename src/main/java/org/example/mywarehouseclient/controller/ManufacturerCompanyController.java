package org.example.mywarehouseclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.mywarehouseclient.entity.ManufacturerCompanyEntity;
import org.example.mywarehouseclient.service.ManufacturerCompanyService;

public class ManufacturerCompanyController {

    private final ManufacturerCompanyService service = new ManufacturerCompanyService();

    private boolean addFlag = true;

    @FXML
    private ListView<ManufacturerCompanyEntity> dataList;

    @FXML
    private Button myButton1;

    @FXML
    private Button myButton2;

    @FXML
    private Button myButton3;

    @FXML
    private TextField textTitle;

    @FXML
    void ExitAction(ActionEvent event) {
        // Получаем текущую сцену
        Scene currentScene = ((Node)event.getSource()).getScene();

        // Получаем текущий Stage (окно приложения)
        Stage currentStage = (Stage)currentScene.getWindow();

        // Закрываем текущее окно
        currentStage.close();
    }

    @FXML
    void addAction(ActionEvent event) {
        String manufacturer = textTitle.getText().trim();
        if (manufacturer.isEmpty()) {
            showEmptyAlert();
            return;
        }

        ManufacturerCompanyEntity manufacturerCompany = new ManufacturerCompanyEntity();
        manufacturerCompany.setManufacturerCompany(manufacturer);
        if (addFlag) {
            service.add(manufacturerCompany);
        } else {
            manufacturerCompany.setId(getSelectionElement().getId());
            service.update(manufacturerCompany, getSelectionElement());
            myButton1.setText("Добавить");
        }
        textTitle.clear();
    }

    private void showEmptyAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода");
        alert.setHeaderText(null);
        alert.setContentText("Форма не может быть пустой!");
        alert.showAndWait();
    }


    private ManufacturerCompanyEntity getSelectionElement(){
        ManufacturerCompanyEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void clearAction(ActionEvent event) {
        ManufacturerCompanyEntity manufacturerCompany = dataList.getSelectionModel().getSelectedItem();
        if (manufacturerCompany != null) {
            try {
                service.delete(manufacturerCompany);
                dataList.getItems().remove(manufacturerCompany);
            } catch (RuntimeException e) {
                // Обработка ошибки, связанной с удалением родительской записи
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка удаления");
                alert.setHeaderText("Невозможно удалить запись");
                alert.setContentText("Эта запись связана с другими данными и не может быть удалена.");
                alert.showAndWait();
            }
        }
    }


    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                ManufacturerCompanyEntity temp = getSelectionElement();
                textTitle.setText(temp.getManufacturerCompany());
                myButton1.setText("изменить");
            }

        }
    }

    @FXML
    private void initialize(){
        service.getAll();
        dataList.setItems(service.getData());
    }

}
