package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void deveGerarLoginComSeteCaracteres() {

        when(personRepository.existsByLogin(anyString()))
                .thenReturn(false);

        String login =
                loginService.gerarLogin("Maria Silva Souza");

        assertEquals(7, login.length());
    }

    @Test
    void deveGerarLoginSomenteComLetrasMinusculas() {

        when(personRepository.existsByLogin(anyString()))
                .thenReturn(false);

        String login =
                loginService.gerarLogin("João Pedro Lima");

        assertTrue(login.matches("[a-z]{7}"));
    }

    @Test
    void deveLancarExcecaoQuandoNomeTemMenosDeSeteLetras() {

        assertThrows(
                IllegalArgumentException.class,
                () -> loginService.gerarLogin("Ana")
        );
    }
}