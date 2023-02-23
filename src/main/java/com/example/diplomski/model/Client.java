package com.example.diplomski.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String email;
    private String name;
    private String surname;
    private ClientData clientData;
}
