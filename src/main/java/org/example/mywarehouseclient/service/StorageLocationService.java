package org.example.mywarehouseclient.service;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.mywarehouseclient.entity.StorageLocationEntity;
import org.example.mywarehouseclient.response.BaseResponse;
import org.example.mywarehouseclient.response.DataResponse;
import org.example.mywarehouseclient.response.ListResponse;

import java.lang.reflect.Type;

public class StorageLocationService {

    @Getter
    private ObservableList<StorageLocationEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<StorageLocationEntity>>(){

    }.getType();

    private Type listType = new TypeToken<ListResponse<StorageLocationEntity>>(){

    }.getType();

    public void getAll(){
        ListResponse<StorageLocationEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getGetAllStorageLocation()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(StorageLocationEntity data){
        String temp = http.post(prop.getAddStorageLocation(), service.getJson(data));
        DataResponse<StorageLocationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }


    public void update(StorageLocationEntity after, StorageLocationEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateStorageLocation(), service.getJson(after));
        DataResponse<StorageLocationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(StorageLocationEntity data){
        String temp = http.delete(prop.getDeleteStorageLocation(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
