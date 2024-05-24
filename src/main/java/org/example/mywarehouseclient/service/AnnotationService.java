package org.example.mywarehouseclient.service;


import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.mywarehouseclient.entity.AnnotationEntity;
import org.example.mywarehouseclient.response.BaseResponse;
import org.example.mywarehouseclient.response.DataResponse;
import org.example.mywarehouseclient.response.ListResponse;

import java.lang.reflect.Type;

public class AnnotationService {
    @Getter
    private ObservableList<AnnotationEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<AnnotationEntity>>(){

    }.getType();

    private Type listType = new TypeToken<ListResponse<AnnotationEntity>>(){

    }.getType();

    public void getAll(){
        ListResponse<AnnotationEntity> data = new ListResponse<>();

        data = service.getObject(http.get(prop.getGetAllAnnotation()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(AnnotationEntity data){
        String temp = http.post(prop.getAddAnnotation(), service.getJson(data));
        DataResponse<AnnotationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(AnnotationEntity after, AnnotationEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateAnnotation(), service.getJson(after));
        DataResponse<AnnotationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else {
            throw new RuntimeException(response.getMessage());
        }

    }

    public void delete(AnnotationEntity data){
        String temp = http.delete(prop.getDeleteAnnotation(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

}
