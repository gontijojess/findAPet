package com.example.findAPet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="PET")
@Builder
public class Pet {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sex;
    // @Column(name="descrição") -> Para alterar o nome da coluna
    private String description;
    @NotBlank
    private String rescueLocation;
    @NotBlank
    private String urlPicture;

    @ManyToOne
    @JoinColumn(name="species_id")
    @JsonBackReference
    private Species species;

    @ManyToOne
    @JoinColumn(name="member_id")
    @JsonBackReference
    private Member member;
}