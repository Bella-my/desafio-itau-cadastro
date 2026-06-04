package br.com.ana.desafioitau.service;

import br.com.ana.desafioitau.dto.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String URL_VIA_CEP = "https://viacep.com.br/ws/%s/json/";

    public ViaCepResponseDTO buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(URL_VIA_CEP, cep);

        ViaCepResponseDTO endereco = restTemplate.getForObject(url, ViaCepResponseDTO.class);

        // Tratamento caso ViaCep Retorne erro
        if (endereco == null || Boolean.TRUE.equals(endereco.getErro())) {
            throw new IllegalArgumentException("CEP não encontrado.");
        }

        return endereco;
    }
}