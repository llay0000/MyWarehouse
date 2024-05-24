package org.example.mywarehouseclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageLocationEntity {

    private Long id;
    private String storageLocation;

    @Override
    public String toString() {
        return storageLocation;
    }
}
