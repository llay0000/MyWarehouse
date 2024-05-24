package org.example.mywarehouseclient.service;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.mywarehouseclient.entity.AnnotationEntity;
import org.example.mywarehouseclient.entity.AuthorizationEntity;
import org.example.mywarehouseclient.response.DataResponse;
import org.example.mywarehouseclient.response.ListResponse;

import java.lang.reflect.Type;

public class AuthorizationService {

    @Getter
    private ObservableList<AuthorizationEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<AnnotationEntity>>(){

    }.getType();

    private Type listType = new TypeToken<ListResponse<AnnotationEntity>>(){

    }.getType();

    public void authorizationEntrance(AuthorizationEntity data) {
        String response = http.post(prop.getAuthorizationEntrance(), service.getJson(data));
        DataResponse<AuthorizationEntity> dataResponse = service.getObject(response, DataResponse.class);

        if (dataResponse.isSuccess()) {
            // Сохраняем авторизованного пользователя
            this.data.add(dataResponse.getData());
        } else {
        }
    }


    public void authorizationRegister(AuthorizationEntity data) {
        String response = http.post(prop.getAuthorizationRegister(), service.getJson(data));
        DataResponse<AuthorizationEntity> dataResponse = service.getObject(response, DataResponse.class);

        if (dataResponse.isSuccess()) {
            this.data.add(dataResponse.getData());
        } else {

        }
    }

}

