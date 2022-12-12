# RyckAndMorty

Nesse projeto esta sendo aplicado algumas provas de conceitos utilizando a API [rickandmorty](https://rickandmortyapi.com/)

## 🥅 Objetivos:

- [x] Tela de listagem de personagens (com paginação)
- [x] Tela de detalhes do personagem
- [x] Filtro de personagens
- [x] Suporte a rotação de tela (orientações portrait e landscape)
- [x] Escrever testes unitários e de UI
- [x] Suporte ao modo offline (cachear localmente response da API)
- [x] Adicionar regras do Proguard
- [ ] Outros tipos de filtragens
- [ ] github actions para validar builds e tests
- [ ] melhoria de interface considerando dark-mode e light-mode do device.

#### Principais comandos a serem considerados no projeto 
- ./gradlew build
- ./gradlew connectedCheck
- ./gradlew lintKotlin

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Certifique-se que seu gradle esta configurado com Java 11 usando ./gradlew -version

## ☕ Arquitetura e padrão de arquitetura
* [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* [MVVM](https://www.techtarget.com/whatis/definition/Model-View-ViewModel#:~:text=MVVM%20is%20also%20known%20as,Ken%20Cooper%20and%20John%20Gossman.)
* [Robot pattern](https://jakewharton.com/testing-robots/) (Tests UI)

## 🚀Principais Frameworks utilizados

* Version catalog
* koin
* kotlin serialization
* retrofit
* Flow
* Coroutines
* Compose

## 🏢 Modulos 

* App - concentrador de features
* data - Comunicação com repositórios
* domain - Modelos e regra de negócio
* RMComponent - Biblioteca de componentes visuais (Compose)

[⬆ Voltar ao topo](#RyckAndMorty)<br>
