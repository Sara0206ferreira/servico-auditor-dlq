## Auditor de DLQ (Dead Letter Queue)
## Justificativa da Arquitetura Escolhida (Estrutura em Camadas)

Por causa da organização, optei pelo padrão de **Arquitetura em Camadas**, dividindo o sistema em: `consumer`, `dto`, `model`, `repository` e `service`.
Os principais motivos para essa escolha foram:

* **Separação de Responsabilidades (SoC):** Cada classe cuida exclusivamente do seu papel. O `DlqQueueListener` (consumer) apenas escuta a AWS; o `AuditoriaService` aplica as regras de negócio e severidade; e o `ErrorLogRepository` gerencia o banco de dados.
* **Facilidade de Manutenção:** Caso no futuro seja necessário trocar o banco H2 por um PostgreSQL, ou trocar o SQS da AWS por RabbitMQ, a alteração fica isolada em sua respectiva camada, sem impactar ou quebrar o restante do sistema.
* **Organização e Padronização:** Por ser um projeto de auditoria facil de entender (Recebe ➔ Processa ➔ Salva), a estrutura em camadas simplifica o código, tornando a revisão e a apresentação muito mais claras.