package org.example.mywarehouseclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationEntity {

    private Long id;
    private String description;

    @Override
    public String toString() {
        return description;
    }
}