package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public class BaseEntity {

    private LocalDateTime createdDateTime;

    @PrePersist
    public void onCreate(){
        this.createdDateTime=LocalDateTime.now();
    }
}
