package br.com.ana.desafioitau.repository;

import br.com.ana.desafioitau.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByLogin(String login);
    boolean existsByCpf(String cpf);
}