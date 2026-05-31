import { useState } from "react";
import { cadastrarPessoa } from "../services/personService";

function PersonRegister() {
    const [form, setForm] = useState({
        nomeCompleto: "",
        cpf: "",
        email: "",
        dataNascimento: "",
        cep: "",
        numero: "",
        complemento: "",
    });

    const [resultado, setResultado] = useState(null);
    const [erros, setErros] = useState({});
    const [carregando, setCarregando] = useState(false);

    function alterarCampo(event) {
        const { name, value } = event.target;

        setForm({
            ...form,
            [name]: value,
        });
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

    return (
        <main className="page">
            <section className="card">
                <aside className="info-area">
                    <div className="info-content">
                        <h1>Cadastro Inteligente</h1>

                        <div className="feature-box">
                            <div className="feature-icon">✓</div>
                            <div>
                                <strong>Geracao de Login Automatico</strong>
                                <span>Dados e login gerados de ponta a ponta.</span>
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

                    <div className="steps">
                        <span>Cadastro</span>
                        <span>Login</span>
                    </div>
                </aside>

                <div className="form-area">
                    <p className="subtitle">
                        Preencha seus dados para criar sua conta.
                    </p>

                    <form onSubmit={enviarCadastro}>
                        <label className="field field-full">
                            <span>Nome Completo</span>
                            <input name="nomeCompleto" placeholder="Digite seu nome completo" value={form.nomeCompleto} onChange={alterarCampo} />
                        </label>

                        <label className="field">
                            <span>CPF</span>
                            <input name="cpf" placeholder="000.000.000-00" value={form.cpf} onChange={alterarCampo} />
                        </label>

                        <label className="field">
                            <span>Data de Nascimento</span>
                            <input name="dataNascimento" type="date" value={form.dataNascimento} onChange={alterarCampo} />
                        </label>

                        <label className="field field-full">
                            <span>E-mail</span>
                            <input name="email" placeholder="exemplo@email.com" value={form.email} onChange={alterarCampo} />
                        </label>

                        <label className="field">
                            <span>CEP</span>
                            <input name="cep" placeholder="00000-000" value={form.cep} onChange={alterarCampo} />
                        </label>

                        <label className="field">
                            <span>Numero</span>
                            <input name="numero" placeholder="Ex: 123" value={form.numero} onChange={alterarCampo} />
                        </label>

                        <label className="field field-full">
                            <span>Complemento</span>
                            <input name="complemento" placeholder="Apto, bloco, casa..." value={form.complemento} onChange={alterarCampo} />
                        </label>

                        <button className="submit-button" type="submit" disabled={carregando}>
                            {carregando ? "Cadastrando..." : "Cadastrar Agora"}
                        </button>
                    </form>

                    <div className="messages">
                        {erros.erro && <p className="error">{erros.erro}</p>}

                        {Object.entries(erros)
                            .filter(([campo]) => campo !== "erro")
                            .map(([campo, mensagem]) => (
                                <p className="error" key={campo}>
                                    {mensagem}
                                </p>
                            ))}
                    </div>

                    <p className="terms">Ao se cadastrar, seus dados serao validados conforme as regras do desafio.</p>
                </div>
            </section>
        </main>
    );
}

export default PersonRegister;
