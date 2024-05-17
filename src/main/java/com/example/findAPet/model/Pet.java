package com.example.findAPet.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pet {
    @NotNull
    private int id;
    @NotBlank
    private String species;
    private String sex;
    private String description;
    @NotBlank
    private String rescueLocation;
    @NotBlank
    private String urlPicture;
    @Pattern(regexp="(^$|[0-9]{11})")
    private String contato;
}