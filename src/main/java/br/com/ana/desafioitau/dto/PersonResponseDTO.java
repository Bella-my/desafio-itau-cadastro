package br.com.ana.desafioitau.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "anavito")
    private String login;

    @Schema(example = "Ana Vitoria Silva")
    private String nomeCompleto;

    @Schema(example = "52998224725")
    private String cpf;

    @Schema(example = "ana@email.com")
    private String email;

    @Schema(example = "1995-03-15")
    private LocalDate dataNascimento;

    @Schema(example = "01001000")
    private String cep;

    @Schema(example = "Praça da Sé")
    private String logradouro;

    @Schema(example = "123")
    private String numero;

    @Schema(example = "Apto 10")
    private String complemento;

    @Schema(example = "Sé")
    private String bairro;

    @Schema(example = "São Paulo")
    private String cidade;

    @Schema(example = "SP")
    private String estado;
}
