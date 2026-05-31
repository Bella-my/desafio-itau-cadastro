package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.dto.PersonRequestDTO;
import br.com.ana.desafioitau.dto.PersonResponseDTO;
import br.com.ana.desafioitau.repository.PersonRepository;
import org.springframework.stereotype.Service;
import br.com.ana.desafioitau.model.Person;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    private PersonResponseDTO converterParaResponseDTO(Person person) {
        return new PersonResponseDTO(
                person.getId(),
                person.getLogin(),
                person.getNomeCompleto(),
                person.getCpf(),
                person.getEmail(),
                person.getDataNascimento(),
                person.getCep(),
                person.getLogradouro(),
                person.getNumero(),
                person.getComplemento(),
                person.getBairro(),
                person.getCidade(),
                person.getEstado()
        );
    }
    public PersonResponseDTO criarPessoa(PersonRequestDTO request) {
        Person person = new Person();

        person.setNomeCompleto(request.getNomeCompleto());
        person.setCpf(request.getCpf());
        person.setEmail(request.getEmail());
        person.setDataNascimento(request.getDataNascimento());
        person.setCep(request.getCep());
        person.setNumero(request.getNumero());
        person.setComplemento(request.getComplemento());

        Person pessoaSalva = personRepository.save(person);

        return converterParaResponseDTO(pessoaSalva);
    }


    public List<PersonResponseDTO> listarPessoas() {

        return personRepository.findAll()
                .stream() //Percorre cada item da lista
                .map(this::converterParaResponseDTO) // Converte cada Person para ResponseDTO
                .toList();
    }


}