package org.example.mywarehouseclient.service;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.mywarehouseclient.entity.ManufacturerCompanyEntity;
import org.example.mywarehouseclient.response.BaseResponse;
import org.example.mywarehouseclient.response.DataResponse;
import org.example.mywarehouseclient.response.ListResponse;

import java.lang.reflect.Type;

public class ManufacturerCompanyService {

    @Getter
    private ObservableList<ManufacturerCompanyEntity> data = FXCollections.observableArrayList();

    private final HttpService http = new HttpService();

    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<ManufacturerCompanyEntity>>(){

    }.getType();

    private Type listType = new TypeToken<ListResponse<ManufacturerCompanyEntity>>(){

    }.getType();

    public void getAll(){
        ListResponse<ManufacturerCompanyEntity> data = new ListResponse<>();

        data = service.getObject(http.get(prop.getGetAllManufacturerCompany()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(ManufacturerCompanyEntity data){
        String temp = http.post(prop.getAddManufacturerCompany(), service.getJson(data));
        DataResponse<ManufacturerCompanyEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(ManufacturerCompanyEntity after, ManufacturerCompanyEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateManufacturerCompany(), service.getJson(after));
        DataResponse<ManufacturerCompanyEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else {
            throw new RuntimeException(response.getMessage());
        }

    }

    public void delete(ManufacturerCompanyEntity data){
        String temp = http.delete(prop.getDeleteManufacturerCompany(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
