# 🕯️ Sentinel - Sistema de Gerenciamento de Fornecedores e Dependentes

## 🕶️ Visão Geral

O Sentinel é um sistema Spring Boot que gerencia fornecedores (representados como  `Patient`), seus dependentes (`CoPatient`), exames (`Exam`) e registros de exames realizados (`PatientExam`). O sistema também inclui autenticação de usuários (`User`) para acesso à API.

## 🎪 Modelos Principais

### Patient (Fornecedor)

-   Representa um fornecedor principal
    
-   Campos: nome, email, cidade, telefone, CPF, endereço e créditos
    
-   Pode ter múltiplos  `CoPatient`  (dependentes)
    

### CoPatient (Dependente do Fornecedor)

-   Representa um dependente associado a um fornecedor
    
-   Campos similares ao `Patient`, mais um tipo (enum  `CoPatientType`)
    
-   Relacionado a um  `Patient`
    

### Exam (Exame/Serviço)

-   Representa um exame ou serviço que pode ser realizado
    
-   Campos: nome e preço
    

### PatientExam (Registro de Exame)

-   Registra exames realizados por fornecedores ou seus dependentes
    
-   Relaciona  `Patient`  ou  `CoPatient`  com  `Exam`  e data
    

### User (Usuário do Sistema)

-   Para autenticação na API
    
-   Campos: username, email, password
    

## 🚩 Endpoints da API

### Autenticação

-   `POST /auth/register`  - Registrar novo usuário
    
-   `POST /auth/login`  - Fazer login e obter token JWT
    

### Fornecedores (Patients)

-   `POST /api/patients`  - Criar novo fornecedor
    
-   `GET /api/patients`  - Listar todos fornecedores
    
-   `GET /api/patients/{id}`  - Obter fornecedor por ID
    
-   `DELETE /api/patients/{id}`  - Remover fornecedor
    
-   `POST /api/patients/{id}/credits`  - Adicionar créditos ao fornecedor
    

### Dependentes (CoPatients)

-   `POST /api/copatients/register/{patientId}`  - Registrar novo dependente para um fornecedor
    
-   `GET /api/copatients/by-patient/{patientId}`  - Listar dependentes de um fornecedor
    

### Exames (Exams)

-   `POST /api/exams`  - Criar novo exame/serviço
    
-   `GET /api/exams`  - Listar todos exames
    

### Registros de Exames (PatientExams)

-   `POST /api/patient-exams/register/patient`  - Registrar exame para fornecedor
    
-   `POST /api/patient-exams/register/copatient`  - Registrar exame para dependente
    
-   `GET /api/patient-exams/patient/{patientId}`  - Listar exames de um fornecedor
    
-   `GET /api/patient-exams/copatient/{coPatientId}`  - Listar exames de um dependente
    

## ⚙️ Tecnologias Utilizadas

-   Spring Boot
    
-   Spring Data JPA
    
-   Lombok
    
-   H2 Database (ou outro banco configurado)
    
-   JWT para autenticação
    

## 🛼 Configuração e Execução

1.  Clone o repositório
    
2.  Configure o arquivo  `application.properties`  com suas configurações de banco de dados
    
3.  Execute o projeto com:
    
	    mvn spring-boot:run
    
4.  Acesse a API em  `http://localhost:8080`

## 📔 Exemplos de Uso

### Registrar um Fornecedor


POST /api/patients

    {
        "name": "Fornecedor A",
        "email": "fornecedor@a.com",
        "phone": "11999999999",
        "cpf": "12345678901",
        "city": "São Paulo",
        "address": "Rua Exemplo, 123"
    }

### Adicionar Dependente a um Fornecedor

POST /api/copatients/register/1

    {
        "name": "Dependente 1",
        "email": "dependente@a.com",
        "phone": "11888888888",
        "cpf": "98765432109",
        "type": "FILHO"
    }

### Registrar um Exame

POST /api/exams

    {
        "name": "Exame de Sangue",
        "price": 150.00
    }

### Registrar Exame para Fornecedor

POST /api/patient-exams/register/patient

    {
        "patientId": 1,
        "examId": 1,
        "examDate": "2023-11-15"
    }

## 🚶‍♂️ Próximos Passos

-   Adicionar mais validações
    
-   Implementar paginação nas listagens
    
-   Adicionar testes automatizados
    
-   Implementar controle de permissões mais granular

## 🚶‍♂️ Licenças

- Este projeto está sobre a [Licença MIT](www.google.com)
- Projeto Original de [Lucas Corrêa](https://github.com/LucaoCorrea) 🧑‍💻


