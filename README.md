# 🛠️ Makina — Controle de Bancada e Manutenção de TI

*(Sim, o nome **Makina** veio de uma brincadeira misturando "Máquina" com a personagem Makima de Chainsaw Man, já que a ideia do sistema é ter o controle total de tudo o que entra e sai da bancada de manutenção!)*

Este projeto é a API Back-end desenvolvida para a disciplina de Back-end (Professor Junio Figueiredo), aplicando **Spring Boot**, **JPA/Hibernate**, **Bean Validation** e **Spring Security com Token JWT**.

---

## 💡 De onde surgiu a ideia?

Trabalhando na prática com manutenção de TI, um problema muito comum é perder o histórico das máquinas que passam pela bancada: saber quantas vezes o mesmo computador já voltou pelo mesmo defeito, qual peça está faltando, de qual setor ele veio e quem retirou o equipamento quando ficou pronto.

O **Makina** foi pensado para resolver exatamente essa rotina de bancada, separando o cadastro físico do **Equipamento** do histórico de **Ocorrências (Ordens de Serviço)** vinculadas a ele.

---

## 🔄 Evolução do Projeto e Mudanças no Caminho (Diário de Bordo)

Construir a API não foi uma linha reta; várias ideias mudaram conforme fui testando o sistema na prática:

1. **Identificação dos Equipamentos (De `MK-0001` para ID + Patrimônio opcional):**
    - No início, pensei em gerar um código automático no formato `MK-0001`. Durante o desenvolvimento, percebi que usar o próprio `ID` do banco junto com um campo de `numeroPatrimonio` (único, porém opcional) fazia muito mais sentido para a vida real, já que na manutenção muitas vezes chegam máquinas sem etiqueta de patrimônio, mas que ainda precisam ser cadastradas pelo setor e descrição.
2. **O problema do Loop Infinito no JSON:**
    - Ao relacionar `Equipamento` (`@OneToMany`) com `Ocorrencia` (`@ManyToOne`), listar os dados gerava um loop infinito no JSON. Resolvi isso controlando a serialização com `@JsonIgnore` para que o equipamento traga seu histórico sem travar a API.
3. **O "Susto" dos Equipamentos Vazios e a entrada dos DTOs:**
    - Durante os testes no Insomnia, mandei sem querer um JSON de login (`login` e `senha`) para a rota de criar equipamento (`POST /equipamentos`) e a API cadastrou um equipamento inteiro `null` no banco!
    - Foi aí que entendi na prática a importância de não receber a Entidade direto no Controller e criei os **DTOs (`record`)** com **Bean Validation (`@Valid`, `@NotBlank`, `@NotNull`)** junto com a classe `TratadorDeErros` para barrar requisições inválidas com erro `400 Bad Request`.
4. **Hierarquia de Perfis no Spring Security (`ADMIN`, `TECNICO` e `USUARIO`):**
    - Além do login com token JWT ensinado em aula, quis implementar três níveis de acesso:
        - **`ADMIN`:** Superusuário (criado automaticamente na primeira subida do banco via `AdminInitializer`), único que pode cadastrar novos usuários/técnicos na rota `/cadastros`.
        - **`TECNICO`:** Pode cadastrar equipamentos, abrir ocorrências, mudar o status na bancada e finalizar os consertos.
        - **`USUARIO`:** Perfil apenas de visualização (`GET`), pensado para consulta de andamento sem poder alterar nada no sistema.
5. **Trava de Segurança na Conclusão de Ocorrência:**
    - Para evitar que uma ocorrência fosse marcada como `CONCLUIDA` pelo endpoint comum de troca de status sem informar o que foi feito na máquina, adicionei uma trava no Service obrigando o encerramento a passar pela rota `/finalizar`, que exige a `solucaoAplicada` e o nome de quem retirou (`retiradoPor`).

---

## 🤖 Uso de Inteligência Artificial no Desenvolvimento

Para ser 100% transparente sobre o processo de estudo: utilizei IAs (Gemini, Claude e GitHub Copilot) como ferramentas de apoio durante o projeto.

Toda a idealização do domínio de manutenção de TI, regras de bancada, escolha dos fluxos de status, criação dos perfis de acesso e testes de rota no Insomnia partiram de mim, enquanto as IAs me ajudaram a:
- Destravar erros de sintaxe do Java/Spring e configurações do Lombok;
- Entender como conectar as peças do Spring Security (`SecurityFilter`, `TokenService` e `SecurityConfigurations`) seguindo o padrão visto nas apostilas de aula;
- Revisar brechas de validação nos Pull Requests e gerar ideias visuais (protótipos) para o futuro Front-end.

---

## 🚀 Tecnologias Utilizadas

- **Java 21** + **Spring Boot**
- **Spring Data JPA / Hibernate**
- **MySQL** (Banco de dados `Makina`)
- **Spring Security** + **Auth0 Java-JWT** (Autenticação Stateless com Token Bearer)
- **Bean Validation** (`jakarta.validation`)
- **Lombok**

---

## 📌 Principais Rotas da API

| Método | Rota | Acesso | Descrição |
| :--- | :--- | :--- | :--- |
| `POST` | `/login` | Público | Autentica o usuário e devolve o Token JWT |
| `POST` | `/cadastros` | `ADMIN` | Cadastra novos usuários/técnicos com senha em BCrypt |
| `POST` | `/equipamentos` | `ADMIN`, `TECNICO` | Cadastra um novo equipamento |
| `GET` | `/equipamentos` | Todos logados | Lista todos os equipamentos e seus históricos |
| `GET` | `/equipamentos/{id}` | Todos logados | Busca um equipamento específico pelo ID |
| `POST` | `/ocorrencias/equipamento/{id}` | `ADMIN`, `TECNICO` | Abre uma ocorrência (`ABERTA`) para o equipamento |
| `GET` | `/ocorrencias/equipamento/{id}` | Todos logados | Lista as ocorrências de um equipamento |
| `PATCH` | `/ocorrencias/{id}/status` | `ADMIN`, `TECNICO` | Atualiza o status (`EM_BANCADA`, `AGUARDANDO_PECA`, `CANCELADA`) |
| `PATCH` | `/ocorrencias/{id}/finalizar` | `ADMIN`, `TECNICO` | Conclui a ocorrência exigindo `solucaoAplicada` e `retiradoPor` |

---

## ⚙️ Como Executar o Projeto

1. Crie um banco de dados MySQL chamado **`Makina`**.
2. Configure o usuário e senha do seu MySQL no arquivo `src/main/resources/application.properties`.
3. Execute a aplicação (`MakinaApplication`).
4. Na primeira execução, o **`AdminInitializer`** criará automaticamente o primeiro usuário administrador no banco (`login: admin` / `senha: admin123`) para que você já possa fazer login em `POST /login` e testar todas as rotas no Insomnia/Postman!