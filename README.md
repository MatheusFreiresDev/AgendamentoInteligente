---

<h1 align="center">🏥 AgendamentoInteligente</h1>

<p align="center">
  API RESTful construída com Java e Spring Boot para gerenciamento de um sistema hospitalar simples.<br>
  Controla Pacientes, Médicos e Agendamentos, com relacionamentos e operações CRUD completas.
</p>

---

## 🚀 Sobre o projeto

Sistema backend para um hospital/clínica que permite o cadastro e gerenciamento de pacientes, médicos e agendamentos de consultas.

Permite que um médico atenda vários pacientes e gerencia os agendamentos com data e hora, além de vincular agendamentos a pacientes específicos.

---

## 🛠️ Tecnologias e ferramentas

* Java 17+
* Spring Boot
* Spring Data JPA
* Banco de dados H2 (in-memory para desenvolvimento/testes)
* Maven

---

## 🧱 Estrutura do projeto

* `com.SistemaAgendamento.Agendamento.Paciente` — entidades, DTOs, serviços e repositórios de paciente
* `com.SistemaAgendamento.Agendamento.Medicos` — entidades, DTOs, serviços e repositórios de médico
* `com.SistemaAgendamento.Agendamento.Agendamento` — entidades, DTOs, serviços e repositórios de agendamento
* `controller` — controllers REST que expõem os endpoints para cada entidade
* `service` — regras de negócio e lógica da aplicação
* `mapper` — conversores entre Model e DTO

---

## 🔧 Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/MatheusFreiresDev/AgendamentoInteligente.git
cd AgendamentoInteligente
```

2. Execute o projeto:

```bash
./mvnw spring-boot:run
```

*(No Windows: `mvnw.cmd spring-boot:run`)*

3. A API estará disponível em:

```
http://localhost:8080
```

---

## 📌 Endpoints principais

### Pacientes

* `GET /pacientes` — listar todos os pacientes
* `GET /pacientes/{id}` — buscar paciente por ID
* `POST /pacientes` — criar paciente
* `PUT /pacientes/{id}` — atualizar paciente
* `DELETE /pacientes/{id}` — deletar paciente
* `PATCH /pacientes/adicionarAgendamento/{idPaciente}/{idAgendamento}` — adicionar um agendamento ao paciente
* `PATCH /pacientes/removerAgendamento/{idPaciente}` — remover agendamento do paciente

---

### Médicos

* `GET /medicos` — listar médicos
* `GET /medicos/{id}` — buscar médico por ID
* `POST /medicos` — criar médico
* `PUT /medicos/{id}` — atualizar médico
* `DELETE /medicos/{id}` — deletar médico
* `PATCH /medicos/atender` — atender próximo agendamento pendente (sem ID no path)

---

### Agendamentos

* `GET /agendamentos` — listar agendamentos
* `GET /agendamentos/{id}` — buscar agendamento por ID
* `POST /agendamentos` — criar agendamento (vinculando paciente e médico)
* `PUT /agendamentos/{id}` — atualizar agendamento
* `DELETE /agendamentos/{id}` — deletar agendamento

---

## 📫 Contato

Feito com 💻 por **Matheus Freires**

* [GitHub](https://github.com/MatheusFreiresDev)
* [LinkedIn](https://www.linkedin.com/in/matheus-freires-pereira-a74580303/)

---

<p align="center">
  "Códigos claros, sistema funcional." 🚀
</p>

---


