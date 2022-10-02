# Publicize Events App

Aplicativo responsável por buscar uma lista de eventos e através dos detalhes do evento, o usuário pode realizar o checkIn ou compartilhar as informações do evento escolhido.

## MVVM + Clean Architecture
A junção dessas arquiteturas faz com que através do Clean é possível separar o projeto em camadas e cada camada tem sua própria responsabilidade, dessa forma,  a camada tem apenas conhecimento da camada mais interna, e utilizando o MVVM tem a vantagem de realizar o gerenciamento dos dados considerando o ciclo de vida da view e através de notificações é possível realizar a comunicação com a view. Então foi separada nas seguintes camadas:
### Data
Responsável pelos dados da aplicação e sabe onde armazenar e buscar as informações.
### Domain 
Regra de negócio e onde consegue fazer qualquer validação dos dados e sabe como buscar as informações.
### Presentation 
Responsável pelas views e todas as regras relacionadas a elas. 

## Koin
Para a injeção de dependência foi utilizado o framework Koin que conecta as dependências no tempo de execução e é mais fácil para realizar configurações necessárias. 
 
## Navigation
Para a navegação das telas foi utilizado o Navigation Component que facilita no processo de transação entre os fragments e para o envio de informações entre eles foi usado o Safe Args que é recomendado por transmitir argumentos e navegação de tipos seguros até o destino.

## Material Design
Foi utilizado alguns padrões do Material Design como a padronização de cores, fontes e estilos.

## Vídeo


![Project Name](https://user-images.githubusercontent.com/39015507/193436282-beee40a8-384e-49a3-97ff-fb85b683aba1.gif)
