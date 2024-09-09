## Welcome Magis5 Tech Challenge!

Os requerimnetos do desafio podem ser vistos no link

### Como executar o projeto
- Instalar JDK 17++;
- Instalar MySql Server 8.0 ou superior;
- Desenvolvido utilizado: IntelliJ, basta importar o projeto maven e executar;
- Postman é sugerido para importar a coleção e executar;


### Endpoints
Tipos de Bebidas aceitos: Alcoholic; Non-Alcoholic
Novos tipos de bebidas podem ser adicionados diretamente na base de dados ou através de migration (recomendável)

- Cadastrar/Entrada de bebida:
	- Endpoint: localhost:8080/drink/create
	- Tipo de Requisicao: POST
	- BODY, raw, example:
		- > {
    "operatorName":"Diogo",
    "volume": 1,
    "drinkType":"Alcoholic",
    "sectorId":"2"
}

- Consultar Bebida
- Consultar volume no estoque por tipo de bebida
-  localhost:8080/sectors/check-storage?drinkTypeName=Non-Alcoholic&volume=101
-  TYPE: GET
-  Query param:
-  
- Consulta seção disponível para venda de bebida
- Venda de Bebida
- TYPE: post
- URL: localhost:8080/drink/sell
- {
    "operatorName":"Diogo",
    "drinkType":"Alcoolic",
    "volume":1,
    "sectorId":2
}
- Consulta de histórico (por tipo e/ou seção)

- A aplicação manuseia as exceções  de acordo com o erro, exibindo mensagem para orientar o usuario, exemplo
- {
    "errorCode": "INVALID_DRINK_TYPE",
    "message": "Drink type Non-AlcoholicXXX is invalid.",
    "errors": [
        "Drink type not supported"
    ]
}
 
### Pendencias
- Critério de somente aceitar a bebida nao-alcoolica 24 horas após ter tido alcoolica.: Razao: Ler feedback

# Feedback
- Sabemos que interpretação é necessário para um bom desenvolvedor, mas os criterios do desafio são mal organizados e mal estruturados. Por exemplo, o primeiro critério de aceite deixa bem claro "CADASTRO E CONSULTA" e entao quando se fala de histórico (Lá pelo quinto criterio de aceite) tem "Venda" - Da onde surgiu essa venda? Poderia ter unm tópico só para isso. Para uma melhor compreensão os requisitos similares devem ficar agrupados.
- Além de que, a venda - por exemplo - eu tratei como uma inserção lógica, ou seja, nao obriga o usuario a usar DELETE ou qualquer outra coisa, o que acaba por explorar pouco a tecnica do candidato. Pode se fazer? Sim, mais dada a implementação seria um over engineering para uma solucao simples.
- Também não se cobra nada relacionado a preocupação do desenvolvedor com a performance
- Normalmente, um desenvolvedor tem um suporte e comunicação sobre o contrato da API, o que se espera de resposta, qual tipo de objeto, deixar tudo isso a cargo de decisão do desenvolver (embora simples, não é objetivo). O desafio pode ser mais objetivo, alias, toda comunicação num time de dev deve ser sempre objetiva.
- O desafio é uma mistura de interpretação porém não se aprofunda muito na parte técnica é repetitivo e extenso. Esperar que o candidato desenvolva tudo e também a documentação  em 7 dias,  considerando que o mesmo candidato possui outras responsabilidades e que as horas para o desafio nao sao pagas, eu sugiro repensar pois podem perder bons candidatos 
