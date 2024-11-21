# Agencia de Viagens - API REST com Spring Boot e PostgreSQL
Projeto desenvolvido para fins acadêmicos na Faculdade de Ampére FAMPER, buscando compreender o funcionamendo das APIs e seus componentes como um todo, para tal, cada cursando da matéria de Sistemas Distributivos escolheu um tema para desenvolvimento do projeto, com foco no aprendizado e evolução contínua ao decorrer das aulas.

## Contrução
A aplicação foi desenvolvida utilizando Spring Boot para a implementação da API e PostgreSQL como banco de dados. O códigosegue o padrão padrão MVC (Model-View-Controller) ademais foi utilizado o JPA/Hibernate para a persistência dos dados.


# Instalação

Passo a Passo para Instalar e Rodar uma Aplicação Spring Boot
### Pré-requisitos
Certifique-se de que você possui:

- Java Development Kit (JDK) (Java 8 ou superior).
- Maven (usado para construir o projeto Spring Boot).
- Git (para clonar o repositório, se o projeto estiver no GitHub).
Para verificar se estão instalados, use os comandos a seguir:

```
java -version
mvn -version
git --version
```
Caso algum dos comandos retorne um erro, você precisará instalar o respectivo software.

### Instalação JDK e MAVEN

#### Passo a Passo para Instalar o Java (JDK)

Acesse o site oficial do Java (Oracle ou OpenJDK).

Oracle JDK-->https://www.oracle.com/java/technologies/downloads/#java11?er=221886

OpenJDK - (versão open-source do JDK) -->https://adoptium.net/

Baixe a versão mais recente do JDK (Java Development Kit) compatível com o seu sistema operacional (Windows, macOS ou Linux).

#### Instalar o JDK:
Execute o instalador que você baixou e siga as instruções.

Lembre-se do local de instalação (por exemplo, C:\Program Files\Java\jdk-<versão>).

Configurar a Variável de Ambiente JAVA_HOME:

Abra o Painel de Controle e vá em Sistema e Segurança > Sistema > Configurações avançadas do sistema.

Clique em Variáveis de Ambiente.

Em Variáveis do sistema, clique em Novo e adicione uma nova variável chamada JAVA_HOME com o caminho do JDK que você instalou, por exemplo:
```
JAVA_HOME = C:\Program Files\Java\jdk-<versão>
```
Encontre a variável Path nas variáveis do sistema, selecione-a e clique em Editar.

Adicione o caminho ```%JAVA_HOME%\bin``` no final da lista de valores de Path e salve.


### Passo a Passo para Instalar o Maven


Acesse o site oficial do Maven e baixe a versão mais recente:

Apache Maven--> https://maven.apache.org/download.cgi

Baixe o arquivo ZIP para o sistema operacional Windows.

Extrair e Instalar o Maven:

Extraia o arquivo ZIP em um diretório onde deseja instalar o Maven, por exemplo, C:\Program Files\Apache\maven.

Após a extração, você verá uma pasta chamada apache-maven-<versão>.

#### Configurar a Variável de Ambiente M2_HOME e Path:

Abra o Painel de Controle e vá em Sistema e Segurança > Sistema > Configurações avançadas do sistema.

Clique em Variáveis de Ambiente.

Em Variáveis do sistema, clique em Novo e adicione uma nova variável chamada M2_HOME com o caminho do Maven que você extraiu, por exemplo:
```
M2_HOME = C:\Program Files\Apache\maven\apache-maven-<versão>
```
Encontre a variável Path nas variáveis do sistema, selecione-a e clique em Editar.

Adicione o caminho %M2_HOME%\bin no final da lista de valores de Path e salve.








### Clonando o Projeto
Se o projeto Spring Boot está em um repositório Git, você pode cloná-lo com o comando abaixo. Abra o Git Bash e execute:

```
git clone https://github.com/FelipeSfolias/AgenciaDeViagemFamper
```
### Entrando na Pasta do Projeto
Após o clone, entre na pasta do projeto com:
```
cd nome-do-projeto
```
Substitua nome-do-projeto pelo nome da pasta criada pelo comando git clone.


### Compilando o Projeto com Maven
Antes de rodar a aplicação, compile-a para garantir que todas as dependências estejam baixadas e o código esteja sem erros. Ainda no Git Bash, rode:
```
mvn clean install
```
Este comando irá:
Limpar qualquer build anterior (clean).
Baixar as dependências especificadas no arquivo pom.xml.
Compilar o projeto e criar o arquivo jar no diretório target.

### Executando a Aplicação Spring Boot
Depois de compilar, você pode rodar a aplicação usando um dos seguintes comandos:

Opção 1: Usando Maven para Rodar
Este comando inicia a aplicação diretamente pelo Maven:
```
mvn spring-boot:run
```
Opção 2: Usando o Arquivo JAR
Se o comando mvn clean install gerou um arquivo .jar na pasta target, você pode rodar a aplicação diretamente a partir desse arquivo:
```
java -jar target/nome-do-arquivo.jar
```
Substitua nome-do-arquivo.jar pelo nome do arquivo .jar gerado, que normalmente segue o padrão nome-do-projeto-versao.jar.

### Acessando a Aplicação
Depois que a aplicação iniciar, você verá no console uma mensagem indicando que o servidor foi iniciado em um determinado port. Por padrão, a porta é 8080. Para acessar a aplicação, abra o navegador e vá para:
```
http://localhost:8080
```
Caso a aplicação use outra porta, substitua 8080 pela porta correta.


## Configuração do Banco de Dados

### Inicie o PostgreSQL e crie o banco de dados:
```
CREATE DATABASE api_votos;
```
### (Opcional) Crie um usuário específico para o projeto:
```
CREATE USER api_votos_user WITH PASSWORD 'sua_senha';
ALTER DATABASE api_votos OWNER TO api_votos_user;
```
### Configure as credenciais no arquivo src/main/resources/application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/api_votos
spring.datasource.username=api_votos_user
spring.datasource.password=sua_senha
```




Parando a Aplicação
Para parar a execução da aplicação, vá até o terminal onde ela está rodando e pressione Ctrl + C.

# API Endpoints
Os endpoints da API na estrutura de dados têm funções específicas para lidar com as operações CRUD (Create, Read, Update, Delete,Patch(ou Put)) das entidades do sistema.Cada classe da aplicação ao ser requisitada manipula  e retorna dados no formato.JSON em suas respostas.


# 1. Classe Pacote
## 1.1. GET /api/pacote - Busca todos os pacotes

Exemplo de resposta:


```[
  {
    "id_pacote": 1,
    "nome": "Pacote Europa",
    "descricao": "Pacote turístico pela Europa",
    "preco": 5000.0,
    "data_inicio": "2024-06-01",
    "data_fim": "2024-06-15",
    "id_destino": 1
  },
  {
    "id_pacote": 2,
    "nome": "Pacote América do Sul",
    "descricao": "Explorando os países da América do Sul",
    "preco": 3000.0,
    "data_inicio": "2024-08-10",
    "data_fim": "2024-08-20",
    "id_destino": 2
  }
]
```
## 1.2. POST /api/pacote - Salva um novo pacote

Exemplo de request:
```
{
  "nome": "Pacote Ásia",
  "descricao": "Visita aos principais países da Ásia",
  "preco": 7000.0,
  "data_inicio": "2024-09-01",
  "data_fim": "2024-09-15",
  "id_destino": 3
}
```
Exemplo de resposta:
```
{
  "message": "Pacote criado com sucesso",
  "id_pacote": 3
}
```
## 1.3. GET /api/pacote/{id} - Busca um pacote específico

Exemplo de resposta:
```
{
  "id_pacote": 1,
  "nome": "Pacote Europa",
  "descricao": "Pacote turístico pela Europa",
  "preco": 5000.0,
  "data_inicio": "2024-06-01",
  "data_fim": "2024-06-15",
  "id_destino": 1
}
```
## 1.4. DELETE /api/pacote/{id} - Remove um pacote

Exemplo de resposta:
```
{
  "message": "Pacote removido com sucesso"
}
```
## 1.5. PATCH /api/pacote/{id} - Atualiza um pacote

Exemplo de request:
```{
  "preco": 5200.0
}
```
Exemplo de resposta:
```{
  "message": "Pacote atualizado com sucesso"
}
```
# 2. Classe Cliente
## 2.1. GET /api/cliente - Busca todos os clientes

Exemplo de resposta:
```
[
  {
    "id_cliente": 1,
    "nome": "Felipe",
    "cpf": "999999999",
    "email": "felipe@gmail.com",
    "telefone": "4635478800",
    "endereco": "Rua do Pipipip"
  },
  {
    "id_cliente": 2,
    "nome": "Ana",
    "cpf": "888888888",
    "email": "ana@gmail.com",
    "telefone": "4635478801",
    "endereco": "Avenida Brasil"
  }
]
```
## 2.2. POST /api/cliente - Salva um novo cliente

Exemplo de request:
```
{
  "nome": "Carlos",
  "cpf": "777777777",
  "email": "carlos@gmail.com",
  "telefone": "4635478802",
  "endereco": "Rua Nova"
}
```
Exemplo de resposta:
```
{
  "message": "Cliente criado com sucesso",
  "id_cliente": 3
}
```
## 2.3. GET /api/cliente/{id} - Busca um cliente específico

Exemplo de resposta:
```
{
  "id_cliente": 1,
  "nome": "Felipe",
  "cpf": "999999999",
  "email": "felipe@gmail.com",
  "telefone": "4635478800",
  "endereco": "Rua do Pipipip"
}
```
## 2.4. DELETE /api/cliente/{id} - Remove um cliente

Exemplo de resposta:
```
{
  "message": "Cliente removido com sucesso"
}
```
## 2.5. PATCH /api/cliente/{id} - Atualiza um cliente

Exemplo de request:
```
{
  "id_cliente": 1,
  "telefone": "4635478805"
}
```
Exemplo de resposta:
```
{
  "message": "Cliente atualizado com sucesso"
}
```
# 3. Classe Destino
## 3.1. GET /api/destino - Busca todos os destinos

Exemplo de resposta:

```
[
  {
    "id_destino": 1,
    "nome_destino": "Paris",
    "pais": "França",
    "cidade": "Paris",
    "descricao": "Cidade da luz e do amor"
  },
  {
    "id_destino": 2,
    "nome_destino": "Buenos Aires",
    "pais": "Argentina",
    "cidade": "Buenos Aires",
    "descricao": "Capital da Argentina"
  }
]
```
## 3.2. POST /api/destino - Salva um novo destino

Exemplo de request:
```
{
  "nome_destino": "Roma",
  "pais": "Itália",
  "cidade": "Roma",
  "descricao": "Capital histórica da Itália"
}
```
Exemplo de resposta:
```
{
  "message": "Destino criado com sucesso",
  "id_destino": 3
}
```
## 3.3. GET /api/destino/{id} - Busca um destino específico

Exemplo de resposta:
```
{
  "id_destino": 1,
  "nome_destino": "Paris",
  "pais": "França",
  "cidade": "Paris",
  "descricao": "Cidade da luz e do amor"
}
```
## 3.4. DELETE /api/destino/{id} - Remove um destino

Exemplo de resposta:
```
{
  "message": "Destino removido com sucesso"
}
```
## 3.5. PATCH /api/destino/{id} - Atualiza um destino

Exemplo de request:
```
{
  "descricao": "Cidade da luz e do romance"
}
```
Exemplo de resposta:
```
{
  "message": "Destino atualizado com sucesso"
}
```
# 4. Classe Reserva
## 4.1. GET /api/reserva - Busca todas as reservas

Exemplo de resposta:
```
[
  {
    "id_reserva": 1,
    "data_reserva": "2024-05-01",
    "status": "Confirmada",
    "id_cliente": 1,
    "id_pacote": 1
  },
  {
    "id_reserva": 2,
    "data_reserva": "2024-06-10",
    "status": "Pendente",
    "id_cliente": 2,
    "id_pacote": 2
  }
]
```
## 4.2. POST /api/reserva - Salva uma nova reserva

Exemplo de request:
```
{
  "data_reserva": "2024-07-15",
  "status": "Confirmada",
  "id_cliente": 1,
  "id_pacote": 2
}
```
Exemplo de resposta:
```
{
  "message": "Reserva criada com sucesso",
  "id_reserva": 3
}
```
## 4.3. GET /api/reserva/{id} - Busca uma reserva específica

Exemplo de resposta:
```
{
  "id_reserva": 1,
  "data_reserva": "2024-05-01",
  "status": "Confirmada",
  "id_cliente": 1,
  "id_pacote": 1
}
```
## 4.4. DELETE /api/reserva/{id} - Remove uma reserva

Exemplo de resposta:
```
{
  "message": "Reserva removida com sucesso"
}
```
## 4.5. PATCH /api/reserva/{id} - Atualiza uma reserva

Exemplo de request:
```
{
  "status": "Cancelada"
}
```
Exemplo de resposta:
```
{
  "message": "Reserva atualizada com sucesso"
}
```
# 5. Classe Pagamento
## 5.1. GET /api/pagamento - Busca todos os pagamentos

Exemplo de resposta:
```
[
  {
    "id_pagamento": 1,
    "data_pagamento": "2024-05-02",
    "valor": 5000.0,
    "metodo_pagamento": "Cartão de Crédito",
    "id_reserva": 1
  },
  {
    "id_pagamento": 2,
    "data_pagamento": "2024-06-11",
    "valor": 3000.0,
    "metodo_pagamento": "Boleto",
    "id_reserva": 2
  }
]
```
## 5.2. POST /api/pagamento - Salva um novo pagamento

Exemplo de request:
```
{
  "data_pagamento": "2024-07-16",
  "valor": 7000.0,
  "metodo_pagamento": "Transferência",
  "id_reserva": 3
}
```
Exemplo de resposta:
```
{
  "message": "Pagamento criado com sucesso",
  "id_pagamento": 3
}
```
## 5.3. GET /api/pagamento/{id} - Busca um pagamento específico

Exemplo de resposta:
```
{
  "id_pagamento": 1,
  "data_pagamento": "2024-05-02",
  "valor": 5000.0,
  "metodo_pagamento": "Cartão de Crédito",
  "id_reserva": 1
}
```
## 5.4. DELETE /api/pagamento/{id} - Remove um pagamento

Exemplo de resposta:
```
{
  "message": "Pagamento removido com sucesso"
}
```
## 5.5. PATCH /api/pagamento/{id} - Atualiza um pagamento

Exemplo de request:
```
{
  "valor": 5500.0
}
```
Exemplo de resposta:
```
{
  "message": "Pagamento atualizado com sucesso"
}
```

