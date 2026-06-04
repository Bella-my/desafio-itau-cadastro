package br.com.ana.desafioitau.config;

import br.com.ana.desafioitau.model.Person;
import br.com.ana.desafioitau.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarMassaInicial(PersonRepository personRepository) {
        return args -> {
            if (personRepository.count() > 0) {
                return;
            }

            List<PessoaInicial> pessoas = List.of(
                    new PessoaInicial(
                            "Maria Silva Souza",
                            "52998224725",
                            "maria.souza@email.com",
                            LocalDate.of(1990, 5, 12),
                            "mariasi",
                            "100"
                    ),
                    new PessoaInicial(
                            "Maria Silva Souza",
                            "11144477735",
                            "maria.silva@email.com",
                            LocalDate.of(1988, 8, 20),
                            "ariasil",
                            "200"
                    ),
                    new PessoaInicial(
                            "Joao Pedro Lima",
                            "39053344705",
                            "joao.lima@email.com",
                            LocalDate.of(1995, 3, 15),
                            "joaoped",
                            "300"
                    ),
                    new PessoaInicial(
                            "Ana Clara Souza",
                            "93541134780",
                            "ana.souza@email.com",
                            LocalDate.of(1998, 6, 10),
                            "anaclar",
                            "400"
                    ),
                    new PessoaInicial(
                            "Carlos Eduardo Lima",
                            "68158642604",
                            "carlos.lima@email.com",
                            LocalDate.of(1994, 11, 25),
                            "carlose",
                            "500"
                    )
            );

            personRepository.saveAll(
                    pessoas.stream()
                            .map(this::criarPessoa)
                            .toList()
            );
        };
    }

    private Person criarPessoa(PessoaInicial pessoaInicial) {
        Person person = new Person();
        person.setLogin(pessoaInicial.login());
        person.setNomeCompleto(pessoaInicial.nomeCompleto());
        person.setCpf(pessoaInicial.documento());
        person.setEmail(pessoaInicial.email());
        person.setDataNascimento(pessoaInicial.dataNascimento());
        person.setCep("01001000");
        person.setLogradouro("Praça da Sé");
        person.setNumero(pessoaInicial.numero());
        person.setComplemento("");
        person.setBairro("Sé");
        person.setCidade("São Paulo");
        person.setEstado("SP");

        return person;
    }

    private record PessoaInicial(
            String nomeCompleto,
            String documento,
            String email,
            LocalDate dataNascimento,
            String login,
            String numero
    ) {
    }
}
