## Welcome Magis5 Tech Challenge!


## Como executar o projeto
- Instalar JDK 17++;
- Instalar MySql Server 8.0 ou superior;
- Desenvolvido utilizado: IntelliJ, basta importar o projeto maven e executar;
- Postman é sugerido para executar os endpoints;


## Endpoints

### Create a Drink
**Endpoint:**
POST /drink/create

**Description:**
Insere/Cria nova bebida no sistema

**Request Body:**
- `DrinkRequest` Objeto contém:
  - `String name`
  - `String type` (Aceita: "Alcoholic" or "Non-Alcoholic")
  - `int volume`
  - `int sectorId`

**Response:**
- `201 Created`
  - `DrinkHistorySimpleResponse`

**Exceptions:**
- `DrinkVolumeExceedException`
- `InvalidSectorIdException`
- `DrinkTypeMismatchException`
- `InsufficientSectorCapacityException`

---

### Encontrar/Buscar bebidas
**Endpoint:**
GET /drink/find-drinks

**Description:**
Recupera uma bebida baseado nos critérios dos filtros

**Query Parameters:**
- `volume` (opcional, type: `Integer`)
- `sectorId` (opcional, type: `Integer`)
- `drinkType` (opcional type: `String` | Exemplo: "Alcoholic" or "Non-Alcoholic")

**Response:**
- `200 OK`
  - List de `DrinkHistorySimpleResponse` 

---

### Obter volume total de um tipo de bebida
**Endpoint:**
GET /drink/volume

**Description:**
Busca o volume total disponível de um tipo de bebida.

**Query Parameters:**
- `drinkTypeName` (obrigatório, type: `String` | Aceita: "Alcoholic" or "Non-Alcoholic")

**Response:**
- `200 OK`
  - `DrinkTypeTotalVolumeResponse` 

**Exceptions:**
- `400 Bad Request` if `drinkTypeName` is not provided or is empty.

---

### Vender uma bebida
**Endpoint:**
POST /drink/sell
**Description:**
Vende uma bebida baseada nos critérios da requisição

**Request Body:**
- `DrinkRequest` object containing:
  - `String name`
  - `String type` (Accepts: "Alcoholic" or "Non-Alcoholic")
  - `int volume`
  - `int sectorId`

**Response:**
- `200 OK` se a venda ocorrer com sucesso.
- `400 Bad Request` com a mensagem de erro baseada na exceção

**Exceptions:**
- `DrinkVolumeExceedException`
- `InvalidSectorIdException`
- `DrinkTypeMismatchException`
- `InsufficientSectorCapacityException`

---

### Get Drink History
**Endpoint:**
GET /drink/history

**Description:**
Recupera o histórico de bebidas (entradas e saídas)

**Query Parameters:**
- `drinkTypeName` (opcional, type: `String` | Exemplo: "Alcoholic" or "Non-Alcoholic")
- `sectorId` (opcional, type: `Integer`)

**Response:**
- `200 OK`
  - List de `DrinkHistoryResponse` 

**Exceptions:**
- `400 Bad Request` if neither `drinkTypeName` nor `sectorId` is provided.

 
### Pendências & melhorias 
- Critério de somente aceitar a bebida nao-alcoolica 24 horas após ter tido alcoolica
- Desenvolvimento nao foi quebrado em commits com pequenas funcionalidades 😒
- Poucos testes 😒
- Commit na master 😒
- Utilizar cache
- Utilizar swagger
- Endpoint para adicionar tipos de bebida (?)

 ### Feedback
- Sabemos que interpretação é necessária para um bom desenvolvedor, mas os criterios do desafio são mal organizados e mal estruturados. Por exemplo, o primeiro critério de aceite deixa bem claro "CADASTRO E CONSULTA" e entao quando se fala de histórico (Lá pelo quinto criterio de aceite) tem "Venda" - Da onde surgiu essa venda? Poderia ter unm tópico só para isso. Portanto: Para uma melhor compreensão os requisitos similares devem ficar agrupados.
- Desafio extenso, muito boiler plate, poderia ser fornecido um archetype para o dev;
- Não força a variedade de uso de técnicas;
- Poderia ter usado melhor o tempo com menos serviços e mais ferramentas, por exemplo, documentação utilizando swagger,
