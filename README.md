# Makina

> Controle de ocorrências e histórico de equipamentos de TI.

## Sobre o projeto

O **Makina** é um sistema para a bancada de TI. Quando um computador, notebook ou impressora chega para manutenção, o técnico cadastra o equipamento, abre uma ocorrência, acompanha o andamento do reparo e guarda tudo no histórico. Se o equipamento voltar meses depois, basta buscar por ele para ver tudo o que já foi feito.

Não é um sistema de chamados (help desk): quem registra as ocorrências é o próprio técnico, direto na bancada.

## Como funciona

1. O equipamento chega e é cadastrado. O sistema gera um **código interno** (ex.: `MK-0001`) para etiquetar a máquina. Se ela já tiver um **número de patrimônio**, ele pode ser informado também.
2. O técnico abre uma **ocorrência** com o problema relatado.
3. A ocorrência passa pelos status: *Na fila → Em bancada → Aguardando peça → Pausado → Concluído*.
4. Ao concluir, o técnico registra a **solução aplicada**.
5. O histórico do equipamento pode ser consultado a qualquer momento, pelo código interno ou pelo patrimônio.

## Funcionalidades planejadas

- [ ] Cadastrar novo equipamento
- [ ] Abrir ocorrência
- [ ] Consultar histórico do equipamento
- [ ] Atualizar status da ocorrência
- [ ] Registrar observação ou pausa
- [ ] Encerrar ocorrência (exige a solução aplicada)
- [ ] Login com autenticação via token (JWT)
- [ ] Controle de acesso por perfis de usuário

## Tecnologias

- Java 25
- Spring Boot (Web, Data JPA, Validation)
- Spring Security com JWT
- MySQL

## Status

🚧 Em desenvolvimento.
