package com.pets.veterinary_prop2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    private Object data;
    private List<String> errors;
}//FIN DE LA CLASE DTO
