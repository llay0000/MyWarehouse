package org.example.mywarehouseclient.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.Setter;
import org.example.mywarehouseclient.MainApplication;
import org.example.mywarehouseclient.entity.MedicationEntity;
import org.example.mywarehouseclient.service.MedicationService;

import java.util.Optional;

@Setter
public class MainController {

    private boolean isUserAuthorized = false;

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
        if (isUserAuthorized) {
            MainApplication.showStorageLocationDialog();
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
        }
    }

    @FXML
    void ManufacturerCompanyAction(ActionEvent event) {
        if (isUserAuthorized) {
            MainApplication.showManufacturerCompanyDialog();
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
        }
    }

    @FXML
    void addOrChangeAnnotationAction(ActionEvent event) {
        if (isUserAuthorized) {
            MainApplication.showAnnotationDialog();
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
        }
    }

    @FXML
    void addOrChangeMedicationAction(ActionEvent event) {
        if (isUserAuthorized) {
            Optional<MedicationEntity> medication = Optional.empty();
            MainApplication.showMedicationDialogAdd(medication);
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
        }
    }

    @FXML
    void changeMedicationAction(ActionEvent event) {
        if (isUserAuthorized) {
            Optional<MedicationEntity> medication = Optional.of(MedicationTable.getSelectionModel().getSelectedItem());
            MainApplication.showMedicationDialogUpdate(medication);
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
        }
    }

    @FXML
    void deleteMedicationAction(ActionEvent event) {
        if (isUserAuthorized) {
            MedicationEntity medicationDelete = MedicationTable.getSelectionModel().getSelectedItem();
            if (medicationDelete != null) {
                service.delete(medicationDelete);
                MedicationTable.getItems().remove(medicationDelete);
            }
        } else {
            showErrorAlert("Доступ запрещен. Пожалуйста, авторизуйтесь.");
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

        showRegistrationDialog();
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

    @FXML
    void showRegistrationDialog(ActionEvent event) {
        showRegistrationDialog();
    }

    private void showRegistrationDialog() {

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
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("вход");
        dialog.setHeaderText("Введите логин и пароль для регистрации");

        ButtonType registerButtonType = new ButtonType("Регистрация", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField loginField = new TextField();
        loginField.setPromptText("Логин");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");

        grid.add(new Label("Логин:"), 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);

        Node registerButton = dialog.getDialogPane().lookupButton(registerButtonType);
        registerButton.setDisable(true);

        Platform.runLater(() -> loginField.requestFocus());

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            registerButton.setDisable(newValue.trim().isEmpty() || passwordField.getText().trim().isEmpty());
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            registerButton.setDisable(newValue.trim().isEmpty() || loginField.getText().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == registerButtonType) {
                return new Pair<>(loginField.getText(), passwordField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent() && isValidPassword(result.get().getValue())) {
            isUserAuthorized = true;
            // Если пароль верный, то открываем доступ ко всем функциям
            // ...
        } else {
            isUserAuthorized = false;
            showErrorAlert("Неверный пароль");
        }
    }

    private void showLoginDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Вход в систему");
        dialog.setHeaderText("Введите логин и пароль");

        // Стили для диалогового окна
        dialog.getDialogPane().getStylesheets().add("path/to/your/styles.css");
        dialog.getDialogPane().getStyleClass().add("login-dialog");

        ButtonType loginButtonType = new ButtonType("Войти", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField loginField = new TextField();
        loginField.setPromptText("Логин");
        loginField.getStyleClass().add("login-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.getStyleClass().add("password-field");

        grid.add(new Label("Логин:"), 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        Platform.runLater(() -> loginField.requestFocus());

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty() || passwordField.getText().trim().isEmpty());
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty() || loginField.getText().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(loginField.getText(), passwordField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent() && isValidPassword(result.get().getValue())) {
            isUserAuthorized = true;
            // Открываем доступ ко всем функциям
        } else {
            isUserAuthorized = false;
            showErrorAlert("Неверный логин или пароль");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add("styles.css");
        alert.getDialogPane().getStyleClass().add("error-alert");
        alert.showAndWait();
    }




    private boolean isValidPassword(String password) {
        String validPassword = "123";
        return password.equals(validPassword);
    }


}


