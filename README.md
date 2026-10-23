# Makina

> Controle de ocorrências e histórico de equipamentos de TI.

## Sobre o projeto

O **Makina** é um sistema para a bancada de TI. Quando um computador, notebook ou impressora chega para manutenção, o técnico cadastra o equipamento, abre uma ocorrência, acompanha o andamento do reparo e guarda tudo no histórico. Se o equipamento voltar meses depois, basta buscar por ele para ver tudo o que já foi feito.

Não é um sistema de chamados (help desk): quem registra as ocorrências é o próprio técnico, direto na bancada.

## Como funciona

1. O equipamento chega e é cadastrado com seu tipo, setor de origem e um **ID único** gerado pelo sistema. Se a máquina tiver um **número de patrimônio** físico, ele também pode ser informado (opcional e único).
2. O técnico abre uma **ocorrência** vinculada ao equipamento informando quem o entregou e o problema relatado (com data/hora de entrada e status inicial gerados automaticamente).
3. A ocorrência passa pelos status: `ABERTA` → `EM_BANCADA` → `AGUARDANDO_PECA` → `CONCLUIDA` (ou `CANCELADA`).
4. Ao concluir, o técnico registra obrigatoriamente a **solução aplicada** e quem retirou o equipamento, cravando a data/hora de saída.
5. O histórico completo de manutenções do equipamento pode ser consultado a qualquer momento pelo ID.

## Funcionalidades planejadas

- [x] Cadastrar novo equipamento
- [x] Abrir ocorrência vinculada ao equipamento
- [x] Consultar histórico do equipamento
- [x] Atualizar status da ocorrência (`ABERTA`, `EM_BANCADA`, `AGUARDANDO_PECA`, `CANCELADA`)
- [x] Encerrar ocorrência (exige a solução aplicada e registra quem retirou)
- [ ] Login com autenticação via token (JWT)
- [ ] Controle de acesso por perfis de usuário (Roles)

## Tecnologias

- Java 25
- Spring Boot (Web, Data JPA)
- Spring Security com JWT
- Lombok
- MySQL

## Status

🚧 Em desenvolvimento (API core concluída; iniciando módulo de Autenticação e Segurança).