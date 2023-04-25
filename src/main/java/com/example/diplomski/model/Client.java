package com.example.diplomski.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
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
    @OneToOne(cascade = CascadeType.ALL)
    private ClientData clientData;
}
