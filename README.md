# election-rmi-murilocg

Serviço que permite ao eleitores votar e conferir o resultado da eleição.

## Dependências
 - Java 13
 - Python 3

## Iniciando a aplicação

Para iniciar a aplicação vamos precisar dos arquivos client.jar, server.jar e service.jar.

### Executando no localhost

Para a aplicação funcione é necessário hospedar o arquivo service.jar em um servidor web, nesse caso iremos usar o servidor do Python. Reserve uma pasta onde iremos hospedar o arquivo service.jar. Dentro dessa pasta execute o seguinte comando:
`python -m http.server 8000`

O próximo passo é iniciar o serviço do Java RMI Registry, iremos inicia-lo referenciando para code base service.jar hospedado no servidor do Python:
`rmiregistry -J-Djava.rmi.server.codebase=http://localhost:8000/service.jar`

Após o inicio do RMI Registry podemos iniciar o servidor:
`java -jar server.jar`
Caso, receba a mensagem "The Election's Server is running..." o servidor está pronto para uso.

Agora já podemos realizar requisições com o cliente. O Cliente pode executar dois tipos de ação vote e result. A primeira permite a um eleitor votar em um candidato e a segunda permiete o eleitor verificar as contagens de votos de um candidato. Segue o padrão de requisição

`java -jar client.jar <serverHost> <actionName> <param1> <param2>`
  
Segue uma descrição dos parâmetros:

- serverHost: se refere ao host do servidor iniciado anteriormente.
- actionName: pode possuir os valores vote ou result
- param1: caso a ação seja vote, o primeiro parâmetro é o nome do eleitor. Se a ação for result o primeiro parâmetro é o nome do candidato.
- param2: Esse parâmetro é apenas utilizado pelo pela ação vote, ele se refere ao nome do candidato.

#### Exemplos de requisição com o Cliente

- `java -jar client.jar localhost vote "Murilo Costa" "Vereador Bananinha"`
- `java -jar client.jar localhost result "Vereador Bananinha"`

### Executando em diferentes hosts

Caso não queira executar a aplicação em localhost, na pasta script temos scripts de execução para os casos que o servidor e o cliente não estão na mesma instância.

## Diagrama de Classe Cliente

![alt text](https://github.com/PUC-ES-LDAMD/election-rmi-murilocg/blob/master/doc/election-rmi-client.png)

## Diagrama de Classe Servidor

![alt text](https://github.com/PUC-ES-LDAMD/election-rmi-murilocg/blob/master/doc/election-rmi-server.png)
