package com.example.findAPet.controller;

import com.example.findAPet.exception.AttributeValidationException;
import com.example.findAPet.exception.ResourceNotFoundException;
import com.example.findAPet.model.Pet;
import com.example.findAPet.payload.MessagePayload;
import com.example.findAPet.service.PetService;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pet"})
public class PetController {

    @Autowired
    PetService petService;

    public PetController() {
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animais encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Animal não encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) Optional<String> species) {
        if (species.isEmpty()) {
            return ResponseEntity.ok(petService.getAll());
        } else {
            List<Pet> pets = petService.filterBySpecies(species.get());
            if(pets.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhum resultado encontrado"));
            } else {
                return ResponseEntity.ok(pets);
            }
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal encontrado",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Animal não encontrado",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping({"/{id}"})
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            Pet localizado = petService.getById(id);
            return ResponseEntity.ok(localizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso!",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Verifique se os campos foram preenchidos corretamente",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Pet pet) {
        try {
            petService.save(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso!"));
        } catch (AttributeValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Animal não encontrado",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping({"/{id}"})
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Pet updatedPet) {
        try {
            petService.update(id, updatedPet);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Animal não encontrado",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            this.petService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}