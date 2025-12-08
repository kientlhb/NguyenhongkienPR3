package com.nguyenhongkien.fruitstore.rest.dto.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String username;
    private String email;
}
