Real-Time Market Data App 💹
A high-performance Android application that streams live cryptocurrency data from the Binance Exchange. Built with Jetpack Compose, Ktor WebSockets, and Clean Architecture to demonstrate modern Android development best practices.

🏗 Architecture & Design Patterns
This project implements Clean Architecture with a strict separation of concerns across three distinct layers. This ensures the code is highly testable, independent of UI frameworks, and easy to maintain.

1. Data Layer (com.kcoders.real_timemarketdataapp.data)
The data layer is responsible for the implementation of data sources.

WebSocket (/websocket): Utilizes Ktor with the OkHttp engine to maintain a persistent bi-directional connection with stream.binance.com. It manages connection states (Connecting, Connected, Disconnected).

Local (/local): Uses Room Database to provide an offline-first experience. It saves trade history and includes a trim() logic to keep the database size optimized.

Repository Impl: The MarketRepositoryImpl acts as a mediator, coordinating the WebSocket stream and saving data into the local DAO.

2. Domain Layer (com.kcoders.real_timemarketdataapp.domain)
The "Heart" of the application. It contains the business rules and is written in pure Kotlin.

Model: Simple data classes like Trade.kt that are used throughout the app.

Repository Interface: Defines the contract that the Data layer must follow (Dependency Inversion).

Use Cases (/usecase): Encapsulates specific business logic. Instead of a bloated ViewModel, each action (Streaming, Saving, Observing) has its own dedicated class.

3. Presentation Layer (com.kcoders.real_timemarketdataapp.ui)
Handles the UI and user interaction.

ViewModel: The MarketViewModel manages UI state using StateFlow. It transforms raw trade data into UI-ready states, such as calculating price direction (Green/Red color logic).

Screens (/screens):

MarketWatch: Displays the primary live price with real-time animations.

Detail: Shows a structured list of recent market history.

ConnectionStatus: A diagnostic dashboard showing WebSocket health and "Last Update" latency.
