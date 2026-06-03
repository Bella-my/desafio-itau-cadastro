package br.com.ana.desafioitau.config;

import br.com.ana.desafioitau.model.Person;
import br.com.ana.desafioitau.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarMassaInicial(PersonRepository personRepository) {
        return args -> {
            if (personRepository.count() > 0) {
                return;
            }

            Person pessoa1 = criarPessoa(
                    "mariasi",
                    "Maria Silva Souza",
                    "12345678909",
                    "maria.souza@email.com",
                    LocalDate.of(1990, 5, 12),
                    "01001000",
                    "Praça da Sé",
                    "100",
                    "Apto 10",
                    "Sé",
                    "São Paulo",
                    "SP"
            );

            Person pessoa2 = criarPessoa(
                    "ariasil",
                    "Maria Silva Souza",
                    "98765432100",
                    "maria.silva@email.com",
                    LocalDate.of(1988, 8, 20),
                    "01001000",
                    "Praça da Sé",
                    "200",
                    "",
                    "Sé",
                    "São Paulo",
                    "SP"
            );

            Person pessoa3 = criarPessoa(
                    "joaoped",
                    "João Pedro Lima",
                    "52998224725",
                    "joao.lima@email.com",
                    LocalDate.of(1995, 3, 15),
                    "01310930",
                    "Avenida Paulista",
                    "1500",
                    "Conjunto 12",
                    "Bela Vista",
                    "São Paulo",
                    "SP"
            );

            personRepository.save(pessoa1);
            personRepository.save(pessoa2);
            personRepository.save(pessoa3);
        };
    }

    private Person criarPessoa(
            String login,
            String nomeCompleto,
            String cpf,
            String email,
            LocalDate dataNascimento,
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado
    ) {
        Person person = new Person();
        person.setLogin(login);
        person.setNomeCompleto(nomeCompleto);
        person.setCpf(cpf);
        person.setEmail(email);
        person.setDataNascimento(dataNascimento);
        person.setCep(cep);
        person.setLogradouro(logradouro);
        person.setNumero(numero);
        person.setComplemento(complemento);
        person.setBairro(bairro);
        person.setCidade(cidade);
        person.setEstado(estado);

        return person;
    }
}