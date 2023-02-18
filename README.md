# StartWars App

Desafio Hurb Android

O desafio consiste em fazer um aplicativo do universo Star Wars, utilizando a api Rest (https://swapi.dev/api/).

[App1](/app1.png)
[App1](/app2.png)
[App1](/app3.png)

## Pontos de atenção:

* Não foi utilizado a API de GraphQL pois ela não possui a listagem de categorias, tambem não possui o Id necessário para achar a imagem de detalhe das categorias. Dessa maneira, para facilitar o desenvolvimento e manutenção foi escolhida apenas a RestApi

## Requisitos Gerais:
- AGP: 7.2.1
- Gradle: 7.3.3
- Min version: 23
- Target Sdk: 30
- Deve ser escrito em Kotlin
- Você pode usar qualquer gerenciador de dependência e qualquer biblioteca de terceiros, só tenha em mente a pergunta; "realmente precisa usar essa biblioteca?"
- Preferência por construção de layout utilizando Constraint Layout.

## Arquitetura utilizada:

* Clean Architecture + MVVM

## Bibliotecas utilizadas:
* Kotlin Coroutines
* Architecture Components (LiveData, ViewModel)
* Hilt
* Retrofit
* OkHttp
* Moshi
* Picasso
* Room