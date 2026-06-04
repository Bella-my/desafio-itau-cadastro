import { useState } from "react";
import { cadastrarPessoa } from "../services/personService";
import RegisterSuccess from "./RegisterSuccess";

function PersonRegister() {
    const [form, setForm] = useState({
        nomeCompleto: "",
        cpf: "",
        email: "",
        dataNascimento: "",
        cep: "",
        numero: "",
        complemento: "",
        logradouro: "",
        bairro: "",
        cidade: "",
        estado: "",
    });
    const [resultado, setResultado] = useState(null);
    const [erros, setErros] = useState({});
    const [carregando, setCarregando] = useState(false);
    const [enderecoPreview, setEnderecoPreview] = useState(null);
    const mostrarEndereco = Boolean(enderecoPreview);
    const temErros = Object.keys(erros).length > 0;
    const erroCampo = (campo) => erros[campo];
    const classeInput = (campo) => erroCampo(campo) ? "field-error" : "";
    const campoVazio = (campo) => !form[campo]?.trim();
    const dataAtual = obterDataAtualIso();
    const marcadorObrigatorio = (campo) => (
        campoVazio(campo) ? <strong className="required-mark">*</strong> : null
    );

    // Funções do Cadastro
    async function alterarCampo(event) {
        const { name, value } = event.target;

        let valorTratado = value;

        if (name === "nomeCompleto") {
            valorTratado = tratarNome(value);
        }
        if (name === "cpf") {
            const numeros = value
                .replace(/\D/g, "")
                .slice(0, 11);

            valorTratado = aplicarMascaraCpf(numeros);
        }
        if (name === "cep") {
            const numeros = value
                .replace(/\D/g, "")
                .slice(0, 8);

            valorTratado = aplicarMascaraCep(numeros);
        }

        const novoForm = {
            ...form,
            [name]: valorTratado,
        };

        setForm(novoForm);

        if (name === "cep") {
            const cepLimpo = valorTratado.replace(/\D/g, "");

            if (cepLimpo.length !== 8) {
                setEnderecoPreview(null);
                return;
            }

            if (cepLimpo.length === 8) {
                try {
                    const response = await fetch(
                        `https://viacep.com.br/ws/${cepLimpo}/json/`
                    );

                    const data = await response.json();

                    if (!data.erro) {
                        setEnderecoPreview(data);
                    } else {
                        setEnderecoPreview(null);
                    }
                } catch {
                    setEnderecoPreview(null);
                }
            }
        }
    }
    async function buscarEnderecoPorCep() {
        const cepLimpo = form.cep.replace(/\D/g, "");

        if (cepLimpo.length !== 8) {
            setEnderecoPreview(null);
            return;
        }

        try {
            const response = await fetch(`https://viacep.com.br/ws/${cepLimpo}/json/`);
            const data = await response.json();

            if (data.erro) {
                setEnderecoPreview(null);
                return;
            }

            setEnderecoPreview(data);
        } catch {
            setEnderecoPreview(null);
        }
    }
    async function enviarCadastro(event) {
        event.preventDefault();
        const errosFormulario = validarFormulario();

        if (Object.keys(errosFormulario).length > 0) {
            setErros(errosFormulario);
            return;
        }

        setCarregando(true);
        setErros({});
        setResultado(null);

        try {
            const resposta = await cadastrarPessoa(form);
            setResultado(resposta);
        } catch (erro) {
            setErros(normalizarErrosApi(erro));
        } finally {
            setCarregando(false);
        }
    }
    function novoCadastro() {
        setResultado(null);
        setErros({});
        setEnderecoPreview(null);

        setForm({
            nomeCompleto: "",
            cpf: "",
            email: "",
            dataNascimento: "",
            cep: "",
            numero: "",
            complemento: "",
        });
    }

    //Tratamento/Validação do Nome
    function tratarNome(value) {
        return value
            .replace(/[^A-Za-zÀ-ÿ\s]/g, "")
            .replace(/\s+/g, " ");
    }

    function aplicarMascaraCpf(numeros) {
        return numeros
            .replace(/^(\d{3})(\d)/, "$1.$2")
            .replace(/^(\d{3})\.(\d{3})(\d)/, "$1.$2.$3")
            .replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4");
    }

    function aplicarMascaraCep(numeros) {
        return numeros.replace(/^(\d{5})(\d)/, "$1-$2");
    }

    function validarNome(nome) {
        const nomeTratado = nome.trim().replace(/\s+/g, " ");
        const partes = nomeTratado.split(" ");

        if (partes.length < 2) {
            return "Informe nome e sobrenome.";
        }

        const nomeValido = partes.every((parte) => parte.length >= 2);

        if (!nomeValido) {
            return "Cada parte do nome deve ter pelo menos 2 letras.";
        }

        return null;
    }

    function validarEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        return regex.test(email);
    }

    function obterDataAtualIso() {
        const hoje = new Date();
        const ano = hoje.getFullYear();
        const mes = String(hoje.getMonth() + 1).padStart(2, "0");
        const dia = String(hoje.getDate()).padStart(2, "0");

        return `${ano}-${mes}-${dia}`;
    }

    function validarDataNascimento(dataNascimento) {
        if (!dataNascimento) {
            return "Data de nascimento é obrigatória.";
        }

        const formatoIso = /^\d{4}-\d{2}-\d{2}$/;

        if (!formatoIso.test(dataNascimento)) {
            return "Data de nascimento deve estar em um formato válido.";
        }

        const [ano, mes, dia] = dataNascimento.split("-").map(Number);
        const data = new Date(ano, mes - 1, dia);
        const dataReal = data.getFullYear() === ano
            && data.getMonth() === mes - 1
            && data.getDate() === dia;

        if (!dataReal) {
            return "Data de nascimento deve ser uma data real.";
        }

        if (dataNascimento > dataAtual) {
            return "Data de nascimento não pode ser futura.";
        }

        return null;
    }

    function validarFormulario() {
        const errosFormulario = {};
        const erroNome = validarNome(form.nomeCompleto);
        const erroDataNascimento = validarDataNascimento(form.dataNascimento);

        if (erroNome) {
            errosFormulario.nomeCompleto = erroNome;
        }

        if (!form.email.trim()) {
            errosFormulario.email = "E-mail é obrigatório.";
        } else if (!validarEmail(form.email)) {
            errosFormulario.email = "E-mail inválido.";
        }

        if (!form.cpf.trim()) {
            errosFormulario.cpf = "CPF é obrigatório.";
        }

        if (erroDataNascimento) {
            errosFormulario.dataNascimento = erroDataNascimento;
        }

        if (!form.cep.trim()) {
            errosFormulario.cep = "CEP é obrigatório.";
        }

        if (!form.numero.trim()) {
            errosFormulario.numero = "Número é obrigatório.";
        }

        return errosFormulario;
    }

    function normalizarErrosApi(erro) {
        if (!erro?.erro) {
            return erro;
        }

        const mensagem = erro.erro;
        const mensagemNormalizada = mensagem.toLowerCase();

        if (mensagemNormalizada.includes("cpf")) {
            return { cpf: mensagem };
        }

        if (mensagemNormalizada.includes("cep")) {
            return { cep: mensagem };
        }

        if (mensagemNormalizada.includes("e-mail") || mensagemNormalizada.includes("email")) {
            return { email: mensagem };
        }

        if (mensagemNormalizada.includes("login") || mensagemNormalizada.includes("nome")) {
            return { nomeCompleto: mensagem };
        }

        return erro;
    }


    if (resultado) {
        return (
            <RegisterSuccess
                person={resultado}
                onNovoCadastro={novoCadastro}
            />
        );
    }

    return (
        <main className="page">
            <section className="card">
                <aside className="info-area">
                    <div className="info-content">
                        <h1 className="register-title">Cadastro Inteligente</h1>

                        <div className="feature-box">
                            <div className="feature-icon">✓</div>
                            <div>
                                <strong>Login gerado automaticamente</strong>
                                <span>Preencha seus dados, valide o CEP e receba seu acesso em instantes.</span>
                            </div>
                        </div>

                        {resultado && (
                            <div className="result-card">
                                <span>Login gerado</span>
                                <strong>{resultado.login}</strong>

                                <small>
                                    {resultado.logradouro}, {resultado.numero} - {resultado.bairro}, {resultado.cidade}/{resultado.estado}
                                </small>
                            </div>
                        )}
                    </div>
                </aside>

                <div className="form-area">
                    <p className="subtitle">
                        Preencha seus dados para criar sua conta.
                    </p>

                    {temErros && (
                        <p className="error error-summary">
                            Existem erros no formulário. Por favor, revise os campos destacados.
                        </p>
                    )}

                    <form onSubmit={enviarCadastro}>
                        <label className="field  field-full">
                            <span>Nome Completo {marcadorObrigatorio("nomeCompleto")}</span>
                            <input className={classeInput("nomeCompleto")} name="nomeCompleto" placeholder="Digite seu nome completo" value={form.nomeCompleto} onChange={alterarCampo} />
                            {erroCampo("nomeCompleto") && <small className="field-message">{erroCampo("nomeCompleto")}</small>}
                        </label>

                        <label className="field">
                            <span>E-mail {marcadorObrigatorio("email")}</span>
                            <input className={classeInput("email")} name="email" placeholder="exemplo@email.com" value={form.email} onChange={alterarCampo} />
                            {erroCampo("email") && <small className="field-message">{erroCampo("email")}</small>}
                        </label>

                        <label className="field">
                            <span>CPF {marcadorObrigatorio("cpf")}</span>
                            <input className={classeInput("cpf")}
                                   name="cpf" placeholder="000.000.000-00"
                                   value={form.cpf} onChange={alterarCampo} />
                            {erroCampo("cpf") && <small className="field-message">{erroCampo("cpf")}</small>}
                        </label>

                        <label className="field">
                            <span>Data de Nascimento {marcadorObrigatorio("dataNascimento")}</span>
                            <input className={classeInput("dataNascimento")} name="dataNascimento" type="date" value={form.dataNascimento} onChange={alterarCampo} />
                            {erroCampo("dataNascimento") && <small className="field-message">{erroCampo("dataNascimento")}</small>}
                        </label>

                        <label className="field">
                            <span>CEP {marcadorObrigatorio("cep")}</span>
                            <input
                                className={classeInput("cep")}
                                name="cep"
                                placeholder="00000-000"
                                value={form.cep}
                                onChange={alterarCampo}
                            />
                            {erroCampo("cep") && <small className="field-message">{erroCampo("cep")}</small>}
                        </label>

                        {mostrarEndereco && (
                            <div className="auto-address-fields">
                                <label className="field field-full">
                                    <span>Endereço</span>
                                    <input
                                        value={enderecoPreview.logradouro || ""}
                                        placeholder="Preenchido automaticamente pelo CEP"
                                        readOnly
                                    />
                                </label>

                                <label className="field">
                                    <span>Bairro</span>
                                    <input
                                        value={enderecoPreview.bairro || ""}
                                        placeholder="Preenchido automaticamente"
                                        readOnly
                                    />
                                </label>

                                <label className="field">
                                    <span>Cidade/UF</span>
                                    <input
                                        value={`${enderecoPreview.localidade}/${enderecoPreview.uf}`}
                                        placeholder="Preenchido automaticamente"
                                        readOnly
                                    />
                                </label>
                            </div>
                        )}

                        <label className="field">
                            <span>Número {marcadorObrigatorio("numero")}</span>
                            <input className={classeInput("numero")} name="numero" placeholder="Ex: 123" value={form.numero} onChange={alterarCampo} />
                            {erroCampo("numero") && <small className="field-message">{erroCampo("numero")}</small>}
                        </label>

                        <label className="field">
                            <span>Complemento</span>
                            <input className={classeInput("complemento")} name="complemento" placeholder="Apto, bloco, casa..." value={form.complemento} onChange={alterarCampo} />
                            {erroCampo("complemento") && <small className="field-message">{erroCampo("complemento")}</small>}
                        </label>

                        <button className="submit-button" type="submit" disabled={carregando}>
                            {carregando ? "Cadastrando..." : "Cadastrar Agora"}
                        </button>
                    </form>

                    {erros.erro && <p className="error api-error">{erros.erro}</p>}

                    <p className="terms">Ao se cadastrar, seus dados serão validados conforme as regras do desafio.</p>
                </div>
            </section>
        </main>
    );
}

export default PersonRegister;
