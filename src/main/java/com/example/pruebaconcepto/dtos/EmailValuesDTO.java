package com.example.pruebaconcepto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailValuesDTO {

    private String mailFrom;
    private String mailTo;
    private String mailSubject;
    private String username;
    private String jwt;
}
