# Sistema Basico de Pedidos  - Java API

[![Build Status](https://img.shields.io/badge/STATUS-EM_DESENVOLVIMENTO%20-green?style=for-the-badge)]()

API RESTful simples desenvolvida em Java/Spring Boot com operações CRUD completas para gerenciamento de pedidos, produtos e clientes. Projeto focado em demonstrar fundamentos de desenvolvimento back-end.

> 📌 **Nota:** O código-fonte está todo escrito em inglês para seguir boas práticas de desenvolvimento

## 🎯 Funcionalidades em Desenvolvimento

- **Produtos:** CRUD
- **Usuarios:** CRUD
- **Pedidos:** CRUD
- **API RESTful:** Endpoints padrão HTTP *(Apenas GET implementado)*

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.0+**
- **Spring Data JPA**
- **Banco de Dados H2**
- **Maven**
- **Lombok**



## 📖 Como Consumir a API - Sistema de Pedidos

### 🚀 Pré-requisitos

Antes de começar, certifique-se de ter:
- Java 11 ou superior instalado
- Maven 3.6+ instalado
- Postman ou similar para testar os endpoints
- Git para clonar o repositório

## 📦 Clonando e Executando o Projeto

```bash
# Clone o repositório
git clone https://github.com/Caroline-SC/Projeto-Spring-Boot.git

# Acesse o diretório
cd Projeto-Spring-Boot

# Compile o projeto
mvn clean compile

# Execute a aplicação
mvn spring-boot:run
````

### A API estará disponível em: http://localhost:8080

### 🔌 Endpoints da API

### **Usuarios**
| Método   | Endpoint             | Descrição                  | Parâmetros                                                  |
|----------|----------------------|----------------------------|-------------------------------------------------------------|
| `GET`    | `/users`             | Listar users               |                                                             |
| `GET`    | `/users/{id}`        | Buscar cliente             | `id` (path)                                                 |
| `GET`    | `/users/{id}/orders` | Buscar cliente com pedidos | `id` (path)                                                 |
| `GET`    | `/users/search`      | Buscar cliente com filtros | `name, email, phoneNumber,address, direction` (query param) |
| `POST`   | `/users`             | Criar cliente              | Body JSON                                                   |
| `PATCH`  | `/users/{id}`        | Atualizar cliente          | `id` (path) + Body JSON                                     |
| `DELETE` | `/users/{id}`        | Deletar cliente            | `id` (path)                                                 |

### **Produtos**
| Método   | Endpoint           | Descrição                  | Parâmetros                                                      |
|----------|--------------------|----------------------------|-----------------------------------------------------------------|
| `GET`    | `/products`        | Listar todos os produtos   |                                                                 |
| `GET`    | `/products/{id}`   | Buscar produto por ID      | `id` (path parameter)                                           |
| `GET`    | `/products/search` | Buscar produto com filtros | `name, minPrice, maxPrice, sortBy, direction` (query parameter) |
| `POST`   | `/products`        | Criar novo produto         | Body JSON com dados do produto                                  |
| `PATCH`  | `/products/{id}`   | Atualizar produto          | `id` (path) + Body JSON                                         |
| `DELETE` | `/products/{id}`   | Deletar produto            | `id` (path parameter)                                           |
### **Pedidos**
| Método   | Endpoint             | Descrição                  | Parâmetros                                                                    |
|----------|----------------------|----------------------------|-------------------------------------------------------------------------------|
| `GET`    | `/api/orders`        | Listar todos os pedidos    |                                                                               |
| `GET`    | `/api/orders/{id}`   | Buscar pedido por ID       | `id` (path parameter)                                                         |
| `GET`    | `/api/orders/search` | Buscar pedidos com filtros | `status, clientName, startDate, endDate, sortBy, direction` (query parameter) |
| `POST`   | `/api/orders`        | Criar novo pedido          | Body JSON com itens do pedido                                                 |
| `PATCH`  | `/api/orders/{id}`   | Atualizar status do pedido | `id` (path) + Body JSON                                                       |
| `DELETE` | `/api/orders/{id}`   | Deletar pedido             | `id` (path parameter)                                                         |

## 📋 Exemplos de uso

### **1.Buscar produtos com filtro**
```text 
GET /products/search?minPrice=50&sortBy=price&direction=desc
```
<br>

### **2.Mudar nome e email de um cliente**
```text 
POST /orders
```
**Body JSON**
```json 
{
  "name":"ALice Reis",
  "email":"all76@gmail.com"
}
```
<br>

### **3. Deletar um produto**
> 📌 **Nota:** Produtos nâo estão diretamente associados a pedidos, então podem ser deletados mesmo estando numm pedido.
```text 
DELETE /product/3
```
<br>

### **2.Criar um produto**
```text 
PATCH /orders
```
**Body JSON**
```json 
{
  "status":"WAITING_PAYMENT",
  "clientId":3,
  "items":[
    {
      "moment":"2024-08-19T15:54:06Z",
      "productId":2,
      "quantity": 3
    },
    {
      "productId":2,
      "quantity":1
    }
  ]
}
```

## 🏗️ Estrutura do Projeto
<br>

![Estrutura do projeto](https://github.com/Caroline-SC/Projeto-Spring-Boot/blob/main/docs/Estrutura_do_projeto.png?raw=true)