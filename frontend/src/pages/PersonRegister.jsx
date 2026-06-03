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


    async function alterarCampo(event) {
        const { name, value } = event.target;

        const novoForm = {
            ...form,
            [name]: value,
        };

        setForm(novoForm);

        if (name === "cep") {
            const cepLimpo = value.replace(/\D/g, "");

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
        setCarregando(true);
        setErros({});
        setResultado(null);

        try {
            const resposta = await cadastrarPessoa(form);
            setResultado(resposta);
        } catch (erro) {
            setErros(erro);
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

    const temErros = Object.keys(erros).length > 0;
    const erroCampo = (campo) => erros[campo];
    const classeInput = (campo) => erroCampo(campo) ? "field-error" : "";
    const mostrarEndereco = Boolean(enderecoPreview);

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
                            <span>Nome Completo</span>
                            <input className={classeInput("nomeCompleto")} name="nomeCompleto" placeholder="Digite seu nome completo" value={form.nomeCompleto} onChange={alterarCampo} />
                            {erroCampo("nomeCompleto") && <small className="field-message">{erroCampo("nomeCompleto")}</small>}
                        </label>

                        <label className="field">
                            <span>E-mail</span>
                            <input className={classeInput("email")} name="email" placeholder="exemplo@email.com" value={form.email} onChange={alterarCampo} />
                            {erroCampo("email") && <small className="field-message">{erroCampo("email")}</small>}
                        </label>

                        <label className="field">
                            <span>CPF</span>
                            <input className={classeInput("cpf")} name="cpf" placeholder="000.000.000-00" value={form.cpf} onChange={alterarCampo} />
                            {erroCampo("cpf") && <small className="field-message">{erroCampo("cpf")}</small>}
                        </label>

                        <label className="field">
                            <span>Data de Nascimento</span>
                            <input className={classeInput("dataNascimento")} name="dataNascimento" type="date" value={form.dataNascimento} onChange={alterarCampo} />
                            {erroCampo("dataNascimento") && <small className="field-message">{erroCampo("dataNascimento")}</small>}
                        </label>

                        <label className="field">
                            <span>CEP</span>
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
                            <span>Número</span>
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
