package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.dto.PersonRequestDTO;
import br.com.ana.desafioitau.dto.PersonResponseDTO;
import br.com.ana.desafioitau.dto.ViaCepResponseDTO;
import br.com.ana.desafioitau.model.Person;
import br.com.ana.desafioitau.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ViaCepService viaCepService;

    @Mock
    private LoginService loginService;

    @Mock
    private CpfService cpfService;

    @InjectMocks
    private PersonService personService;

    @Test
    void deveCadastrarPessoaComSucesso() {

        PersonRequestDTO request = new PersonRequestDTO();

        request.setNomeCompleto("Ana Silva");
        request.setCpf("52998224725");
        request.setEmail("ana@email.com");
        request.setDataNascimento(LocalDate.of(1998,3,14));
        request.setCep("01001000");
        request.setNumero("100");
        request.setComplemento("Apto 1");

        ViaCepResponseDTO endereco = new ViaCepResponseDTO();
        endereco.setLogradouro("Praça da Sé");
        endereco.setBairro("Sé");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");

        when(personRepository.existsByCpf(any()))
                .thenReturn(false);

        when(loginService.gerarLogin(any()))
                .thenReturn("anasilv");

        when(viaCepService.buscarEnderecoPorCep(any()))
                .thenReturn(endereco);

        Person pessoaSalva = new Person();
        pessoaSalva.setId(1L);
        pessoaSalva.setLogin("anasilv");
        pessoaSalva.setNomeCompleto("Ana Silva");

        when(personRepository.save(any(Person.class)))
                .thenReturn(pessoaSalva);

        PersonResponseDTO response =
                personService.criarPessoa(request);

        assertNotNull(response);
        assertEquals("anasilv", response.getLogin());

        verify(personRepository).save(any(Person.class));
    }
}