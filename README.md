# Real-Time Market Data App 💹

A high-performance Android application that streams live cryptocurrency market data from the Binance Exchange. Built with Jetpack Compose, Ktor WebSockets, and Clean Architecture, this app demonstrates modern Android development best practices while providing a smooth, real-time user experience.

## 🏗 Architecture & Design

This project follows Clean Architecture principles with a strict separation of concerns across three layers, ensuring testability, maintainability, and UI independence.

## 1. Data Layer 

Responsible for data sources and their management.

### WebSocket (/websocket):
Uses Ktor with the OkHttp engine to maintain a persistent bi-directional connection to stream.binance.com. Manages connection states like Connecting, Connected, and Disconnected.

### Local (/local):
Uses Room Database for offline-first support. Stores trade history and applies a trim() logic to optimize database size.

### Repository Implementation:
MarketRepositoryImpl coordinates WebSocket streaming and local database persistence, acting as the mediator between layers.

## 2. Domain Layer 

The heart of the application, containing business rules implemented in pure Kotlin.

### Models: Data classes such as Trade.kt used across the app.

### Repository Interface: Defines contracts for the data layer, ensuring dependency inversion.

### Use Cases (/usecase): Encapsulates business logic into focused classes:

StreamMarketUseCase – Handles real-time market streaming.

ObserveTradesUseCase – Observes trade updates from the database.

SaveTradeUseCase – Persists trades locally.

GetConnectionStatusUseCase – Provides WebSocket connection status.

This approach keeps ViewModels lightweight, delegating logic to dedicated use cases.

## 3. Presentation Layer

Handles UI and user interactions using Jetpack Compose.

ViewModel:
MarketViewModel manages UI state via StateFlow, transforming raw trade data into UI-ready states, such as price direction coloring (green/red).

## Screens:

MarketWatch – Displays live cryptocurrency prices with real-time animations.

Detail – Shows a list of recent trades and market history.

ConnectionStatus – Diagnostic dashboard showing WebSocket health and latency.

## 💻 Key Features

Real-Time Market Data: Instant updates from Binance WebSocket streams.

Offline Persistence: Room database stores recent trade history for offline access.

Clean Architecture: Clear separation between Data, Domain, and UI layers.

StateFlow for UI: Efficient, reactive UI updates.

Lightweight and Testable: Every layer is independent and easy to maintain.

## 🛠 Tech Stack

#### Android: Kotlin, Jetpack Compose, ViewModel, StateFlow

#### Networking: Ktor WebSockets, OkHttp

#### Database: Room Database

#### Architecture: Clean Architecture, MVVM

#### DI: Koin

## 📁 Project Structure

```The project is organized following Clean Architecture principles:
│
├── data
│   ├── local
│   │   ├── AppDatabase.kt
│   │   ├── TradeDao.kt
│   │   └── TradeEntity.kt
│   ├── repository
│   │   └── MarketRepositoryImpl.kt
│   └── websocket
│       └── BinanceWebSocketService.kt
│
├── domain
│   ├── model
│   │   └── Trade.kt
│   ├── repository
│   │   └── MarketRepository.kt
│   └── usecase
│       ├── ObserveTradesUseCase.kt
│       ├── SaveTradeUseCase.kt
│       └── StreamMarketUseCase.kt
│
├── di
│   └── KoinInit.kt
│
├── ui
│   └── viewmodel
│       └── MarketViewModel.kt ```

### 🔌 Dependency Injection (Koin)
```kotlin
fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(repositoryModule, useCaseModule, viewModelModule, databaseModule, dataModule)
}
```


Database Module: Initializes Room DB and DAO.

Data Module: Provides BinanceWebSocketService.

Repository Module: Binds MarketRepositoryImpl to MarketRepository.

Use Case Module: Provides all use case classes.

ViewModel Module: Provides MarketViewModel.

