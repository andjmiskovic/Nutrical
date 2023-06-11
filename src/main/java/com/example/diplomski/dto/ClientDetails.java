package com.example.diplomski.dto;

import com.example.diplomski.model.ClientData;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ClientDetails {
    private String name;
    private String surname;
    private String email;
    private Long planId;
    private ClientData clientData;
}
