package br.com.ana.desafioitau.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ana.desafioitau.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import br.com.ana.desafioitau.dto.PersonRequestDTO;
import br.com.ana.desafioitau.dto.PersonResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonResponseDTO criarPessoa(@Valid @RequestBody PersonRequestDTO request) {
        return personService.criarPessoa(request);
    }

    @GetMapping
    public List<PersonResponseDTO> listarPessoas() {
        return personService.listarPessoas();
    }
}