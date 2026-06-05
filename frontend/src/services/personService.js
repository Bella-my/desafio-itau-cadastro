const API_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

export async function cadastrarPessoa(dados) {
    let response;

    try {
        response = await fetch(`${API_URL}/pessoas`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(dados),
        });
    } catch {
        throw {
            erro: "Não foi possível conectar ao backend. Verifique a URL da API ou a configuração de CORS.",
        };
    }

    const body = await response.json().catch(() => ({
        erro: "Erro inesperado ao processar resposta do backend.",
    }));

    if (!response.ok) {
        throw body;
    }

    return body;
}
