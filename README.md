# Introduction

This is a test for The fork Interview.

## Description

The whole project has been implemented clean architecture with RxKotlin, and choose MVVM for app part to showing data.

## Architecture
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/interview/Basic%20Activity%20Diagram.png)

## Explanations

### Why Clean Architecture

1.First of all, it's required by interview,

2.Second of all, why not ?

 clean architecture stands for a group of practices that produce systems that are:

- Independent of Frameworks.
- Testable.
- Independent of UI.
- Independent of any external agency.

### Why choosing MVVM not MVP

Because MVVM is more simple and modern way to use in these years. It's recommended by Google, `ViewModel` has all features
Presenter, you can consider it as Presenter layer. Besides that, `ViewModel` has lifecycle scope with Activity or Fragment,
it's more convient and it can persisit state when you rotate the view.

### Why using RxKotlin,RxJava rather than Coroutine with LiveData
Personnally I think Reactive Extensions has more power than LiveData, both of them are using `Pattern Observer`, however `LiveData`
doesn't have such transformations like `Reactive Extextions`, however it's more like a personal taste.
