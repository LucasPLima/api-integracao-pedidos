# Híbrido-API - Integração de pedidos

## Descrição do teste

Este teste visa simular um pequeno cenário de integração entre sistemas.
O teste consiste em buscar informações de uma lista de pedidos de um webservice e enviá-los para outro webservice. 

Uma lista de pedidos aleatória é disponibilizada em [Swagger UI - buscarPedidosUsingGET](http://origem.demacode.com.br:8181/swagger-ui.html#/pedido-controller/buscarPedidosUsingGET) informando dois parâmetros (data_inicial e data_final).

A lista deverá ser transformada e cada pedido deve ser enviado para [Swagger UI - salvarPedidoUsingPOST](http://destino.demacode.com.br:8282/swagger-ui.html#/pedido-controller/salvarPedidoUsingPOST)

> O webservice de destino possui algumas validações que precisam ser atendidas, como: valor total do pedido deve ser informado, os dados de CPF/CNPJ do cliente e representante são obrigatórios e cada pedido deve conter pelo menos um item.

## Validações

A API implementada para resolver o problema contém as seguintes **validações**:

 - Validação de período informado;
	 - Data de início não pode ser maior que a data final;
	 - Ambas as datas não podem ser maiores que a data atual a qual está sendo feita a solicitação;
- Validação de formato de período, informando qual campo está fora do padrão especificado; 

Ao se obter um erro a API retorna um **JSON** informando a causa e os detalhes do erro.


 ## Recursos
O link para a realizar as requisições de teste é: https://hibrido-pedidos-api.herokuapp.com/.
Os recursos disponíveis são:
|Método| Endpoint | Parâmetros | Descrição| Exemplo|
|--|--|--|--|--|
|GET| /integra-pedido | dataInicio e dataFinal **| Realiza o fluxo de integração| https://hibrido-pedidos-api.herokuapp.com/integra-pedido?dataInicio=2010-10-12T00:00&dataFinal=2021-02-07T00:00 |
|GET|/integra-pedido/ping|N/A|Verifica se o serviço está funcionando|https://hibrido-pedidos-api.herokuapp.com/integra-pedido/ping|
|GET|integra-pedido/historico|N/A|Traz o registro das últimas integrações (no máximo 5)|https://hibrido-pedidos-api.herokuapp.com/integra-pedido/historico|
|GET|integra-pedido/historico/{id}|Identificação da integração|Traz o registro das integração especificada | https://hibrido-pedidos-api.herokuapp.com/integra-pedido/historico/1|
 
 **Formato da data: **yyyy-MM-ddTHH:mm**.

## Informações adicionais

- O cálculo do valor total do pedido foi feito com a seguinte fórmula:
  **valor total de itens** + **valor de frete** - **valor de desconto**.

- A integração gera um JSON com um resumo do fluxo executado, informando:
	- Data da integração;
	- Hora da integração;
	- Quantidade de pedidos recebidos;
	- Quantidade de pedidos enviados;
	- Lista de pedidos recebidos (no formato de dados provenientes da origem);
	- Lista de pedidos enviados  (no formato de dados para envio ao destino);
	- Lista de pedidos com erro, informando a descrição do erro e os detalhes de algum pedido que não tenha atendido aos pré-requisitos do envio citados acima.
- A integração acontece periodicamente a cada 5 minutos. A funcionalidade do histórico foi adicionada para que fossem verificados os registros das integrações feitas automaticamente.

- O histórico de integrações só contém o registro de até 5 solicitações, devido a utilização de um banco em memória (H2).