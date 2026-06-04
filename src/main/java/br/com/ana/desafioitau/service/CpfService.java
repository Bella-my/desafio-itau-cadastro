package br.com.ana.desafioitau.service;

import org.springframework.stereotype.Service;

@Service
public class CpfService {

    public void validarCpf(String cpf) {
        String cpfLimpo = limparCpf(cpf);

        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve possuir 11 dígitos.");
        }
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        int primeiroDigitoCalculado = calcularPrimeiroDigito(cpfLimpo);
        if (primeiroDigitoCalculado != Character.getNumericValue(cpfLimpo.charAt(9))) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        int segundoDigitoCalculado = calcularSegundoDigito(cpfLimpo);
        if (segundoDigitoCalculado != Character.getNumericValue(cpfLimpo.charAt(10))) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    public String limparCpf(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private int calcularPrimeiroDigito(String cpfLimpo) {

        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;

        return resto < 2 ? 0 : 11 - resto;
    }

    private int calcularSegundoDigito(String cpfLimpo) {

        int soma = 0;
        int peso = 11;

        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;

        return resto < 2 ? 0 : 11 - resto;
    }
}
