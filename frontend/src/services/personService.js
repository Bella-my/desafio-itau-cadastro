const API_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

export async function cadastrarPessoa(dados) {
    const response = await fetch(`${API_URL}/pessoas`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(dados),
    });

    const body = await response.json();

    if (!response.ok) {
        throw body;
    }

    return body;
}
