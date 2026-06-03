function RegisterSuccess({ person, onNovoCadastro }) {
    return (
        <main className="page">
            <section className="card">
                <aside className="info-area success-info">
                    <div className="info-content">
                        <h1>Seu acesso foi criado</h1>
                        <p className="welcome-text">
                            Seu cadastro foi concluído com sucesso.
                            <br />
                            O login abaixo já está pronto para uso.
                        </p>
                        <button className="panel-button" type="button" disabled>
                            Login [em construção]
                        </button>
                    </div>
                </aside>

                <div className="success-area">
                    <div className="success-icon">✓</div>

                    <h2>Cadastro realizado com sucesso</h2>

                    <p className="success-description">
                        Seu login foi gerado automaticamente.
                    </p>

                    <div className="success-login">
                        <span>Login gerado</span>
                        <strong>{person.login}</strong>
                    </div>

                    <button className="submit-button success-button" type="button" onClick={onNovoCadastro}>
                        Novo cadastro
                    </button>
                </div>
            </section>
        </main>
    );
}

export default RegisterSuccess;
