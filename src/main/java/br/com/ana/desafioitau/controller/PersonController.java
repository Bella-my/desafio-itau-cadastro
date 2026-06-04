package br.com.ana.desafioitau.controller;

import br.com.ana.desafioitau.dto.PersonRequestDTO;
import br.com.ana.desafioitau.dto.PersonResponseDTO;
import br.com.ana.desafioitau.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@Tag(
        name = "Pessoas",
        description = "Operações de cadastro e consulta de pessoas"
)
@RestController
@RequestMapping("/pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(
            summary = "Cadastrar pessoa"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponseDTO criarPessoa(@Valid @RequestBody PersonRequestDTO request) {
        return personService.criarPessoa(request);
    }

    @Operation(
            summary = "Listar pessoas cadastradas"
    )
    @GetMapping
    public List<PersonResponseDTO> listarPessoas() {
        return personService.listarPessoas();
    }
}
