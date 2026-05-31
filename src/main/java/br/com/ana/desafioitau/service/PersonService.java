package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.dto.PersonRequestDTO;
import br.com.ana.desafioitau.dto.PersonResponseDTO;
import br.com.ana.desafioitau.dto.ViaCepResponseDTO;
import br.com.ana.desafioitau.repository.PersonRepository;
import org.springframework.stereotype.Service;
import br.com.ana.desafioitau.model.Person;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ViaCepService viaCepService;
    private final LoginService loginService;
    private final CpfService cpfService;

    public PersonService(PersonRepository personRepository, ViaCepService viaCepService, LoginService loginService, CpfService cpfService) {
        this.personRepository = personRepository;
        this.viaCepService = viaCepService;
        this.loginService = loginService;
        this.cpfService = cpfService;

    }

    public PersonResponseDTO criarPessoa(PersonRequestDTO request) {

        cpfService.validarCpf(request.getCpf());
        String cpfLimpo = cpfService.limparCpf(request.getCpf());

        if (personRepository.existsByCpf(cpfLimpo)) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        //Limpeza do Nome e Cep
        String nomeTratado = request.getNomeCompleto()
                .trim()
                .replaceAll("\\s+", " ");

        String cepLimpo = request.getCep()
                .replaceAll("[^0-9]", "");

        ViaCepResponseDTO endereco = viaCepService.buscarEnderecoPorCep(cepLimpo);
        String login = loginService.gerarLogin(nomeTratado);

        Person person = new Person();
        person.setLogin(login);
        person.setNomeCompleto(nomeTratado);
        person.setCpf(cpfLimpo);
        person.setEmail(request.getEmail());
        person.setDataNascimento(request.getDataNascimento());
        person.setCep(cepLimpo);
        person.setNumero(request.getNumero());
        person.setComplemento(request.getComplemento());

        person.setLogradouro(endereco.getLogradouro());
        person.setBairro(endereco.getBairro());
        person.setCidade(endereco.getLocalidade());
        person.setEstado(endereco.getUf());

        Person pessoaSalva = personRepository.save(person);

        return converterParaResponseDTO(pessoaSalva);
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
    public List<PersonResponseDTO> listarPessoas() {

        return personRepository.findAll()
                .stream() //Percorre cada item da lista
                .map(this::converterParaResponseDTO) // Converte cada Person para ResponseDTO
                .toList();
    }

}