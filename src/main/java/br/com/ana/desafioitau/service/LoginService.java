package br.com.ana.desafioitau.service;

import org.springframework.stereotype.Service;
import br.com.ana.desafioitau.repository.PersonRepository;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    private final PersonRepository personRepository;

    public LoginService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String gerarLogin(String nomeCompleto) {

        String nomeLimpo = limparNome(nomeCompleto);
        validaNomeParaLogin(nomeLimpo);

        List<String> combinacoes = gerarCombinacoes(nomeLimpo);
        for (String combinacao : combinacoes) {
            if (!personRepository.existsByLogin(combinacao)) {
                return combinacao;
            }
        }

        throw new IllegalStateException(
                "Não foi possível gerar um login único para o nome informado."
        );
    }

    private void validaNomeParaLogin(String nomeLimpo) {
        if (nomeLimpo.length() < 7) {
            throw new IllegalArgumentException(
                    "O nome completo deve possuir pelo menos 7 letras para gerar o login."
            );
        }
    }

    // Remove acentos e caracteres especiais
    private String limparNome(String nomeCompleto) {
        String nomeSemAcento = Normalizer.normalize(nomeCompleto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return nomeSemAcento
                .replaceAll("[^A-Za-z]", "")
                .toLowerCase();
    }

    // Gera combinações sequenciais de 7 letras e testa unicidade.
    private List<String> gerarCombinacoes(String nomeLimpo) {

        List<String> combinacoes = new ArrayList<>();

        for (int i = 0; i <= nomeLimpo.length() - 7; i++) {
            combinacoes.add(nomeLimpo.substring(i, i + 7));
        }

        return combinacoes;
    }
}
