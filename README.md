# CCKids - Totem Frontend

## Descrição

O **CCKids - Totem Frontend** é o aplicativo de autoatendimento desenvolvido para tablets e computadores, com o objetivo de realizar o check-in das crianças que frequentam os cultos do ministério infantil **CCKids**, parte da igreja **Cristo Centro**. O sistema visa garantir o controle de presença das crianças, facilitando o acompanhamento das famílias e aumentando a segurança durante os eventos.

Este projeto é o frontend do sistema, responsável pela interface de interação com os usuários (pais e responsáveis), enquanto o backend, que gerencia os dados e a lógica de negócio, está hospedado em outro local e não faz parte deste repositório.

## Funcionalidades

- **Check-in de crianças**: Interface intuitiva para que os pais ou responsáveis possam registrar a presença das crianças no culto.
- **Consulta de histórico**: Possibilidade de consultar o histórico de check-ins anteriores.
- **Integração com o backend**: Comunicação com o backend para envio e recebimento de dados via API.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Kotlin Multiplatform (KMP)**: Para permitir o desenvolvimento multiplataforma, com suporte tanto para tablets quanto para computadores.
- **Jetpack Compose Multiplatform**: Para a construção da interface de usuário de forma declarativa e reativa.
- **Ktor**: Para a comunicação com o backend via HTTP, utilizando coroutines para chamadas assíncronas.
- **Coroutines**: Para gerenciar operações assíncronas de forma eficiente e não bloqueante.
- **Arquitetura MVVM (Model-View-ViewModel)**: Para separar as responsabilidades da aplicação, facilitando a manutenção e a escalabilidade do código.
- **Testes Unitários**: Cobertura mínima de 90% para garantir a qualidade e a confiabilidade do código.

## Arquitetura

O projeto segue a arquitetura **MVVM (Model-View-ViewModel)**, que facilita a separação de responsabilidades e a manutenção do código. Abaixo estão os principais componentes e como eles se relacionam:

### Pacotes

- **Domain**: Contém as regras de negócio e os casos de uso (UseCases). Aqui é onde a lógica de negócio é implementada de forma independente da camada de dados ou de apresentação.
- **Data**: Contém os repositórios e as implementações de acesso a dados, como chamadas de API via Ktor. O repositório é responsável por fornecer os dados para a camada de domínio.
- **Presentation**: Contém as ViewModels e a lógica de apresentação. As ViewModels são responsáveis por fornecer os dados para a interface de usuário e reagir às interações do usuário.

### Componentes

- **ViewModel**: Responsável por gerenciar o estado da interface de usuário e interagir com os casos de uso da camada de domínio. Utiliza coroutines para realizar operações assíncronas, como chamadas de API.
- **UseCase**: Representa uma ação ou operação específica do sistema, como "Realizar Check-in". Os UseCases são chamados pela ViewModel e interagem com os repositórios para obter ou enviar dados.
- **Repository**: Responsável por fornecer os dados para os UseCases. Ele pode buscar dados de uma API remota (via Ktor) ou de um cache local, se necessário.

### Fluxo de Dados

. O usuário interage com a interface (UI) construída com **Jetpack Compose**.
. A **ViewModel** processa a interação e aciona o **UseCase** correspondente.
. O **UseCase** interage com o **Repository** para buscar ou enviar dados.
. O **Repository** utiliza o **Ktor** para se comunicar com o backend.
. A **ViewModel** atualiza o estado da UI com os dados recebidos ou com o resultado da operação.

## Testes

O projeto possui uma cobertura mínima de **90%** em testes unitários, garantindo a qualidade e a confiabilidade do código. Os testes são focados principalmente nas camadas de **Domain** e **Presentation**, onde a lógica de negócio e a interação com os dados são validadas.

- **Testes de ViewModel**: Garantem que a ViewModel está gerenciando corretamente o estado da UI e interagindo com os UseCases de forma adequada.
- **Testes de UseCase**: Validam a lógica de negócio e garantem que os casos de uso estão funcionando conforme o esperado.
- **Testes de Repository**: Garantem que os repositórios estão retornando os dados corretos, seja de uma API remota ou de um cache local.

## Estrutura de Pastas

```bash
CCKids-Totem-Frontend/
│
├── domain/
│   ├── model/        # Modelos de dados
│   ├── usecase/      # Casos de uso
│
├── data/
│   ├── repository/   # Implementações de repositórios
│   ├── api/          # Configurações e chamadas de API (Ktor)
│
├── presentation/
│   ├── viewmodel/    # ViewModels
│   ├── ui/           # Componentes de UI (Jetpack Compose)
│
├── tests/            # Testes unitários
│
└── README.md
```

## Requisitos
- Kotlin 1.5+
- Android Studio Arctic Fox ou superior (para desenvolvimento em Android)
- JDK 11+
- Gradle 7.0+

## Como Executar
1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/CCKids-Totem-Frontend.git
```

2. Abra o projeto no Android Studio ou IntelliJ IDEA.

3. Sincronize o projeto com o Gradle.

4. Execute o projeto em um emulador ou dispositivo físico.

## Comunicação com o Backend
A comunicação com o backend é feita utilizando o **Ktor**, que permite realizar chamadas HTTP de forma assíncrona utilizando **coroutines**. O backend é responsável por gerenciar os dados das crianças e dos check-ins, e o frontend se comunica com ele para enviar e receber essas informações.

## Figuras e Diagramas

#### Diagrama de Arquitetura
- **Tamanho sugerido**: 800x600px
- **Descrição**: Diagrama que ilustra a arquitetura MVVM do projeto, mostrando a interação entre as camadas de Presentation, Domain e Data. Deve incluir a comunicação com o backend via Ktor e o uso de coroutines para operações assíncronas.

#### Fluxo de Check-in
- **Tamanho sugerido**: 800x600px
- **Descrição**: Diagrama de fluxo que mostra o processo de check-in de uma criança, desde a interação do usuário na interface até a comunicação com o backend e a atualização da UI.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests. Certifique-se de seguir as diretrizes de contribuição e manter a cobertura de testes acima de 90%.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

**CCKids - Totem Frontend** é um projeto desenvolvido para a igreja **Cristo Centro** com o objetivo de melhorar a segurança e o acompanhamento das crianças durante os cultos.