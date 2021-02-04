# API de integração de pedidos - (Híbrido)

Este teste visa simular um pequeno cenário de integração entre sistemas.
O teste consiste em buscar informações de uma lista de pedidos de um webservice e enviá-los para outro webservice. 

Uma lista de pedidos aleatória é disponibilizada em [Swagger UI - buscarPedidosUsingGET](http://origem.demacode.com.br:8181/swagger-ui.html#/pedido-controller/buscarPedidosUsingGET) informando dois parâmetros (data_inicial e data_final).

A lista deverá ser transformada e cada pedido deve ser enviado para [Swagger UI - salvarPedidoUsingPOST](http://destino.demacode.com.br:8282/swagger-ui.html#/pedido-controller/salvarPedidoUsingPOST)

> O webservice de destino possui algumas validações que precisam ser atendidas, como: valor total do pedido deve ser informado, os dados de CPF/CNPJ do cliente e representante são obrigatórios e cada pedido deve conter pelo menos um item.


