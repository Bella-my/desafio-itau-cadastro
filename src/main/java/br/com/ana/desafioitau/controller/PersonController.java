package br.com.ana.desafioitau.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ana.desafioitau.service.PersonService;
import br.com.ana.desafioitau.model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public Person criarPessoa(@RequestBody Person person) {
        return personService.criarPessoa(person);
    }
}