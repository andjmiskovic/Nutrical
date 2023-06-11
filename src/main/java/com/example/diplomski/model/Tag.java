package com.example.diplomski.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<EatenFood> eatenFood;
}
