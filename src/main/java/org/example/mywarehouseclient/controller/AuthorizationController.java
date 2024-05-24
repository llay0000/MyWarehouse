package org.example.mywarehouseclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.mywarehouseclient.entity.AuthorizationEntity;
import org.example.mywarehouseclient.service.AuthorizationService;

import java.util.Optional;

public class AuthorizationController {

    private Optional<AuthorizationEntity> authorization = Optional.empty();

    AuthorizationService service = new AuthorizationService();

    @FXML
    private Button myButton1;

    @FXML
    private Button myButton2;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    void addAction(ActionEvent event) {

    }

    @FXML
    private void initialize() {


    }

    String a;
}