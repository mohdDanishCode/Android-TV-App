# Android Movie App - Project Structure

This is a multi-module Android application built with Clean Architecture principles, following modern Android development best practices.

## 📁 Project Structure

### Root Level
```
├── buildSrc/                 # Build configuration and dependencies management
├── core/                     # Core modules (shared across the app)
├── core-businesslogic/       # Business logic layer
├── core-datasource/         # Data source implementations
├── core-domain/             # Domain layer (entities, use cases)
├── core-network/            # Network layer (API services, DTOs)
└── stage-app/               # Main application module
```

## 🏗️ Module Details

### 🔧 buildSrc
Contains build configuration and dependency management:
- **gradle/**: Build scripts and configuration
- **commons/**: Common build configurations
- **dependencies/**: Centralized dependency management
- **extensions/**: Gradle extensions

### 🎯 core
Base module containing:
- **manifests/**: Android manifest files
- **kotlin+java/com/danish/common/**: Common utilities and base classes
  - **base/activity/**: Base activity classes
  - **utils/**: Utility classes
  - **viewState/**: View state management
- **res/**: Shared resources (strings, etc.)

### 💼 core-businesslogic
Business logic layer implementing use cases:
- **manifests/**: Module manifest
- **kotlin+java/com/stage/businesslogic/**: Business logic implementation
  - **di/**: Dependency injection modules
  - **domain/**: Domain models
  - **model/**: Business models
  - **viewmodels/movie/**: Movie-related ViewModels
    - `DetailViewModel.kt`
    - `HomeViewModel.kt`
    - `PlayerViewModel.kt`
    - `BaseViewModel`
    - `MainViewModel`

### 💾 core-datasource
Data layer implementation:
- **manifests/**: Module manifest
- **kotlin+java/com/core/coreDatasource/**: Data source implementations
  - **di/**: Dependency injection
  - **repository/**: Repository implementations
    - `BaseRepository.kt`
    - `MovieRepository`

### 🎭 core-domain
Domain layer (pure business logic):
- **manifests/**: Module manifest
- **kotlin+java/com/core/coreDomain/**: Domain entities and contracts
  - **model/**: Domain models

### 🌐 core-network
Network layer for API communication:
- **manifests/**: Module manifest
- **kotlin+java/com/network/core/**: Network implementation
  - **database/**: Local database setup
    - `AppDatabase`
  - **dbo/movie/**: Database objects
    - `MovieDao.kt`
    - `MovieEntity.kt`
  - **di/**: Dependency injection modules
    - `ContextModule`
    - `DatabaseModule`
    - `DispatchersModule.kt`
    - `NetworkModule`
    - `Qualifiers.kt`
  - **dto/movieResponse/**: Data transfer objects
    - `MovieResponse.kt`
  - **network/**: Network services
    - `ApiResponse`
    - `ApiService`
    - `MockApiService`

### 📱 stage-app
Main application module (UI layer):
- **manifests/**: App manifest
- **kotlin+java/com/stage/app/**: Application implementation
  - **presentation/**: UI layer
    - **common/**: Shared UI components
      - `ImageGeneric.kt`
      - `MoviePosterOrVideoPreview.kt`
      - `VideoPlayer.kt`
    - **modules/**: Feature modules
      - **detail/**: Movie detail screen
        - `DetailScreen.kt`
      - **home/**: Home screen with movie list
        - **components/**: Home screen components
          - `CustomHorizontalPagerIndicator`
          - `MovieCard.kt`
          - `MovieRow.kt`
        - `HomeScreen.kt`
      - **player/**: Video player screen
        - `PlayerScreen.kt`
      - **splash/**: Splash screen
        - `SplashScreen.kt`
    - `MainGraph.kt`
  - **ui/theme/**: App theming
    - `Color.kt`
    - `OmfSizeSystem.kt`
    - `Theme.kt`
    - `ThemeManager`
    - `Type.kt`
    - `TypographyManager`
  - `AppNavigator.kt`
  - `MainActivity`
  - `Screen`
  - `AppApplication`

## 🏛️ Architecture Overview

This project follows **Clean Architecture** principles with clear separation of concerns:

### Layers
1. **Presentation Layer** (`stage-app`)
  - UI components (Jetpack Compose)
  - ViewModels
  - Navigation

2. **Domain Layer** (`core-domain`)
  - Business entities
  - Use cases
  - Repository interfaces

3. **Data Layer** (`core-datasource`, `core-network`)
  - Repository implementations
  - Local database (Room)
  - Remote API services
  - Data mapping

4. **Business Logic Layer** (`core-businesslogic`)
  - Managers

## 🛠️ Technologies Used

- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt/Dagger
- **Database**: Room
- **Networking**: Retrofit
- **Asynchronous**: Coroutines & Flow
- **Build System**: Gradle with Kotlin DSL

## 📦 Module Dependencies

```
stage-app
├── core-businesslogic
├── core-domain
├── core-datasource
└── core-network

core-businesslogic
├── core-domain
└── core-datasource

core-datasource
├── core-domain
└── core-network

core-network
└── core-domain
```

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle files
4. Run the app

## 📝 Features

- **Movie Browsing**: Browse through movie collections
- **Movie Details**: View detailed information about movies
- **Video Player**: Play movie trailers/videos
- **Responsive UI**: Modern Material Design with Jetpack Compose
- **Offline Support**: Local caching with Room database

## 🎨 UI Components

The app uses a consistent design system with:
- Custom theme management
- Reusable components
- Responsive layouts
- Material Design 3 principles

---

*This modular architecture ensures scalability, testability, and maintainability while following Android development best practices.*


## Key Features Implemented:

1. **MVVM Architecture**: Clean separation of concerns with ViewModels, Repository, and UI layers
2. **Compose for TV**: Optimized UI components with proper focus handling and TV-specific interactions
3. **ExoPlayer Integration**: Full video playback with custom controls, seek functionality, and media session support
4. **Multi-style Tiles**: Different card layouts and grid arrangements optimized for TV screens
5. **Navigation**: Seamless navigation between home, detail, and player screens
6. **Caching Strategy**: Repository pattern with in-db caching for smooth performance
7. **Focus Management**: Proper TV remote control navigation and focus handling
8. **Error Handling**: Comprehensive error states and loading indicators
