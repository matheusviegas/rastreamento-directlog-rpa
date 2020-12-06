# Rastreamento Direct Log

Repositório contendo o código do exemplo de rastreamento de pacotes na transportadora Direct Log para a talk de RPA.

# Requisitos

- Java 8
- Maven
- Google Chrome

# Como executar

Executar o comando `mvn clean install -DskipTests spring-boot:run` para compilar e executar o projeto.

# Como testar

A aplicação é um API, que recebe requisições do tipo **POST** na porta `8080` no path `/api/tracking`.

**Exemplo de request:**

Endpoint: `http://localhost:8080/api/tracking`

```json
{
	"type": "SHIPMENT_NUMBER",
	"code": "12345"
}
```

Tipos de código de rastreio válidos:

- `SHIPMENT_NUMBER` - Número de Remessa
- `COLLECTION_ORDER` - Ordem de Coleta
- `TRACKING_NUMBER` - Número de Rastreio/AWB


**Exemplo de response:**

```json
[
  {
    "datetime": "06/12/2020 20:07",
    "description": "Geração da Remessa (CAT)",
    "city": "São Paulo",
    "observations": "ARQUIVO - Remessa número : 12345"
  }
]
```
