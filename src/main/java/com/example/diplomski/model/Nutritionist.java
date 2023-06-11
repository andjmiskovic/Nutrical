package com.example.diplomski.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nutritionist extends User {
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Client> clients;
    private String licence;
    private String phoneNumber;
}
