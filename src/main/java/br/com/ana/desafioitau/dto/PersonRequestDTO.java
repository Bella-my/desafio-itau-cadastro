package br.com.ana.desafioitau.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {
    @NotBlank(message = "Nome completo é obrigatório")
    @Pattern(
            regexp = "^\\s*[A-Za-zÀ-ÿ]{2,}(\\s+[A-Za-zÀ-ÿ]{2,})+\\s*$",
            message = "Informe nome e sobrenome válido, cada um com pelo menos 2 letras"
    )
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(
            regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$",
            message = "CPF deve conter 11 dígitos, com ou sem máscara"
    )
    private String cpf;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve estar em um formato válido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    @PastOrPresent(message = "Data de nascimento não pode ser futura")
    private LocalDate dataNascimento;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(
            regexp = "^(\\d{5}-?\\d{3})$",
            message = "CEP deve conter 8 dígitos, com ou sem máscara"
    )
    private String cep;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    private String complemento;
}
