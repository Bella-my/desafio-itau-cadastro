package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.repository.PersonRepository;
import org.springframework.stereotype.Service;
import br.com.ana.desafioitau.model.Person;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person criarPessoa(Person person) {
        return personRepository.save(person);
    }

}