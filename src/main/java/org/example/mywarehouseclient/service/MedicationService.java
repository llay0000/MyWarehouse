package org.example.mywarehouseclient.service;


import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import org.example.mywarehouseclient.entity.MedicationEntity;
import org.example.mywarehouseclient.response.BaseResponse;
import org.example.mywarehouseclient.response.DataResponse;
import org.example.mywarehouseclient.response.ListResponse;

import java.lang.reflect.Type;

@Setter
public class MedicationService {

    @Getter
    private ObservableList<MedicationEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<MedicationEntity>>(){

    }.getType();

    private Type listType = new TypeToken<ListResponse<MedicationEntity>>(){

    }.getType();

    public void getAll(){
        ListResponse<MedicationEntity> data = new ListResponse<>();

        data = service.getObject(http.get(prop.getGetAllMedication()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(MedicationEntity data){
        String temp = http.post(prop.getAddMedication(), service.getJson(data));
        DataResponse<MedicationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }


    public void update(MedicationEntity after, MedicationEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateMedication(), service.getJson(after));
        DataResponse<MedicationEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(MedicationEntity data){
        String temp = http.delete(prop.getDeleteMedication(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void filterByRecipe1Medication(){
        ListResponse<MedicationEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getFilterByRecipe1Medication()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void filterByRecipe2Medication(){
        ListResponse<MedicationEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getFilterByRecipe2Medication()), listType);

        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

}
