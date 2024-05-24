package org.example.mywarehouseclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.mywarehouseclient.entity.AnnotationEntity;
import org.example.mywarehouseclient.service.AnnotationService;

public class AnnotationController {

    private final AnnotationService service = new AnnotationService();
    private boolean addFlag = true;

    @FXML
    private ListView<AnnotationEntity> dataList;

    @FXML
    private Button myButton1;

    @FXML
    private Button myButton2;

    @FXML
    private Button myButton3;

    @FXML
    private TextArea textLastAnnotation;

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

            String description = textLastAnnotation.getText().trim();
            if (description.isEmpty()) {
                showEmptyDescriptionAlert();
                return;
            }

            AnnotationEntity annotation = new AnnotationEntity();
            annotation.setDescription(description);
            if (addFlag) {
                service.add(annotation);
            } else {
                annotation.setId(getSelectionElement().getId());
                service.update(annotation, getSelectionElement());
                // Сбрасываем флаг addFlag после успешного изменения
                addFlag = true;
                myButton1.setText("Добавить");
            }
            textLastAnnotation.clear();
    }


    private void showEmptyDescriptionAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода");
        alert.setHeaderText(null);
        alert.setContentText("Форма не может быть пустой!");
        alert.showAndWait();
    }


    private AnnotationEntity getSelectionElement(){
        AnnotationEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void clearAction(ActionEvent event) {
        AnnotationEntity annotation = dataList.getSelectionModel().getSelectedItem();
        if (annotation != null) {
            try {
                service.delete(annotation);
                dataList.getItems().remove(annotation);
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
                AnnotationEntity temp = getSelectionElement();
                textLastAnnotation.setText(temp.getDescription());
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

