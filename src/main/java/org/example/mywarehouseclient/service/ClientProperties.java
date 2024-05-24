package org.example.mywarehouseclient.service;

import lombok.Getter;
import org.example.mywarehouseclient.MainApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {

    private final Properties properties = new Properties();


    private String addAnnotation;
    private String getAllAnnotation;
    private String updateAnnotation;
    private String deleteAnnotation;


    private String authorizationRegister;
    private String authorizationEntrance;


    private String addManufacturerCompany;
    private String getAllManufacturerCompany;
    private String updateManufacturerCompany;
    private String deleteManufacturerCompany;


    private String AddMedication;
    private String getAllMedication;
    private String updateMedication;
    private String deleteMedication;
    private String filterByRecipe2Medication;
    private String filterByRecipe1Medication;


    private String addStorageLocation;
    private String getAllStorageLocation;
    private String updateStorageLocation;
    private String deleteStorageLocation;

    public ClientProperties(){
        try (InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {

            System.out.println(input);
            properties.load(input);

            addAnnotation = properties.getProperty("annotation.add");
            getAllAnnotation = properties.getProperty("annotation.getAll");
            updateAnnotation = properties.getProperty("annotation.update");
            deleteAnnotation = properties.getProperty("annotation.delete");


            authorizationRegister = properties.getProperty("authorization.register");
            authorizationEntrance = properties.getProperty("authorization.login");


            addManufacturerCompany = properties.getProperty("manufacturerCompany.add");
            getAllManufacturerCompany = properties.getProperty("manufacturerCompany.getAll");
            updateManufacturerCompany = properties.getProperty("manufacturerCompany.update");
            deleteManufacturerCompany = properties.getProperty("manufacturerCompany.delete");


            AddMedication = properties.getProperty("medication.add");
            getAllMedication = properties.getProperty("medication.getAll");
            updateMedication = properties.getProperty("medication.update");
            deleteMedication = properties.getProperty("medication.delete");
            filterByRecipe2Medication = properties.getProperty("medication.filterByRecipe2");
            filterByRecipe1Medication = properties.getProperty("medication.filterByRecipe1");



            addStorageLocation = properties.getProperty("storageLocation.add");
            getAllStorageLocation = properties.getProperty("storageLocation.getAll");
            updateStorageLocation = properties.getProperty("storageLocation.update");
            deleteStorageLocation = properties.getProperty("storageLocation.delete");


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
