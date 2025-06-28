package br.com.joaoroberto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    // ObjectMapper é a classe principal da biblioteca Jackson.
    // Ela é responsável por ler (desserializar) e escrever (serializar) JSON.
    // Criamos uma instância dela que será reutilizada em toda a classe.
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Este é o método chave. Ele tenta ler o valor da string 'json'
            // e convertê-la para um objeto do tipo genérico 'classe' que foi passado como parâmetro.
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            // Se o Jackson não conseguir processar o JSON (por exemplo, se ele estiver mal formatado),
            // uma exceção é lançada. Aqui, capturamos o erro e o relançamos como uma RuntimeException
            // para simplificar o tratamento de erros nas camadas superiores da aplicação.
            throw new RuntimeException(e);
        }
    }
}