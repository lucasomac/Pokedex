# Pokedex - AI Agent Guide

## Architecture Overview

This Android app implements **Clean Architecture** with clear separation of concerns:

```
UI Layer (Fragments/ViewModels) 
  ↓ Domain Layer (UseCases/Repositories interface)
  ↓ Data Layer (Repository implementation/DataSources)
  ↓ Framework Layer (Retrofit, Koin DI, Database)
```

**Key principle**: Data flows from infrastructure → domain → UI. Always preserve this boundary.

### Multi-Module Structure
- **`:app`** - Main application (UI, business logic)
- **`:attributes`** - Reusable Jetpack Compose design components library

Always check if shared UI components belong in `:attributes` before adding to `:app`.

## Directory Structure & Patterns

### Core Layers (in `/app/src/main/java/br/com/lucolimac/pokedex/`)

- **`core/`**: Shared utilities
  - `Extensions.kt` - Kotlin extension functions (e.g., `Int.metricalConversion()`)

- **`domain/`**: Pure Kotlin, no Android dependencies
  - `entity/` - Data models (Pokedex, Pokemon)
  - `repository/` - Repository interfaces (contracts)
  - `usecase/` - Business logic (PokedexUseCase, PokemonUseCase)
  - `util/` - Constants and utility classes (e.g., `Constants.kt`, `Result.kt`)
  - `data/` - Pagination sources (PokedexPagingSource)

- **`data/`**: Android-aware data layer
  - `model/response/` - API response DTOs (auto-mapped by Retrofit)
  - `repository/` - Repository implementations (PokedexRepositoryImpl)
  - `source/` - Data access abstractions (PokedexDataSource interface)
  - `PokemonApi.kt` - Retrofit and OkHttp client factories

- **`framework/`**: Infrastructure concerns
  - `di/PokedexModule.kt` - **All Koin DI configuration** (single source of truth)
  - `data/source/` - Concrete data source implementations

- **`ui/`**: Android presentations
  - `presentation/viewmodel/` - ViewModels with StateFlow
  - `presentation/fragment/` - Fragment implementations
  - `presentation/activity/` - Single MainActivity with Navigation Component
  - `adapter/` - RecyclerView adapters (PokemonListAdapter)
  - `component/` - Shared UI components (Separator, BubblePokemonTypeAdapter)

**Application Setup**: `PokedexApplication.kt` initializes Koin on startup

## Dependency Injection (Koin)

**Single DI module**: `framework/di/PokedexModule.kt`

```kotlin
// Pattern: Use DSL builders with interface binding
factoryOf(::PokedexRepositoryImpl) { bind<PokedexRepository>() }
viewModelOf(::PokedexViewModel)  // Koin auto-wires dependencies
factory { Dispatchers.IO }       // Provide coroutine context
```

**Injection in UI**:
```kotlin
// ViewModels
private val viewModel: PokedexViewModel by viewModel()

// Regular dependencies
private val adapter: PokemonListAdapter by inject { parametersOf(this) }
```

**When adding new dependencies**: Update `PokedexModule.kt`, don't create separate DI files.

## Networking & API

- **API Base**: https://pokeapi.co/ 
- **Client Config**: `PokemonApi.kt` (OkHttp with request logging at `POKEMON-LOG` tag)
- **Error Handling**: Repositories wrap API exceptions; flow provides empty/error states

**Pattern for new API endpoints**:
1. Add Retrofit service interface (inject in PokedexDataSource)
2. Create DataSource interface and implementation
3. Add Repository interface and implementation
4. Create UseCase wrapping the repository
5. Inject in ViewModel/Fragment

## State Management & Flow

**Pattern**: ViewModel → StateFlow → Fragment observes via `collectAsStateIn`

```kotlin
// ViewModel
private val _pokemonList = MutableStateFlow(getListPokemon())
val pokemonList = _pokemonList.asStateFlow()

// Fragment
binding.viewModel = this@PokedexFragment.viewModel  // Data binding
```

**Paging**: Uses `androidx.paging.Pager` with custom `PagingSource`:
- Page size: `DEFAULT_SIZE_CONTENT_PAGE` (from Constants)
- Start index: `START_PAGE_INDEX`
- Caching via `cachedIn(viewModelScope)`

## Navigation

- **Framework**: Jetpack Navigation Component with Safe Args
- **Graph**: `res/navigation/nav_graph.xml`
- **Pattern**: Use directions (auto-generated)
  ```kotlin
  findNavController().navigate(
    PokedexFragmentDirections.actionPokedexToPokemonFragment(pokemonName)
  )
  ```

## Testing & Coverage

- **Unit tests**: `src/test/` with JUnit, MockK, Coroutines Test
- **Instrumentation**: `src/androidTest/` with Espresso
- **Coverage tool**: JaCoCo with 15% minimum threshold
- **Excluded from coverage**: UI, models, DI modules, generated code

**Run coverage**: `./gradlew testDebugUnitTestCoverage` (generates HTML report)

**Architecture tests**: ArchUnit verifies layering rules at compile time

## Build & Gradle Configuration

- **Gradle version**: 9.5.1 (uses settings.gradle plugins)
- **AGP (Android Gradle Plugin)**: 9.2.1
- **JDK**: Java 21 (sourceCompatibility/targetCompatibility)
- **Kotlin**: 2.4.0 with Compose compiler plugin
- **Min/Compile/Target SDK**: 26/37/37

**Key build configurations**:
- Data binding: Enabled for XML layouts
- View binding: Enabled for type-safe view access
- Compose: Enabled with BOM for version alignment

**Repositories**: Google, Maven Central, JitPack (custom libraries)

## Important Dependencies & Versions Management

All versions in `gradle/libs.versions.toml` (TOML catalog):

- **Retrofit**: 3.0.0 (+ Gson converter)
- **OkHttp**: 5.3.2 (+ logging interceptor)
- **Navigation**: 2.9.8 (+ Safe Args Gradle plugin)
- **Koin**: 4.2.1 (DI framework)
- **Paging**: 3.5.0
- **Coroutines**: 1.11.0
- **Jetpack Compose**: BOM 2026.05.01
- **Glide**: 5.0.7 (image loading)

**Always use version refs from TOML, not hardcoded versions.**

## Specific Conventions

1. **Package naming**: `br.com.lucolimac.pokedex.{layer}.{feature}`
2. **Class visibility**: Classes are `internal` to prevent accidental external dependency
3. **Constants location**: `domain/util/Constants.kt` (e.g., `DEFAULT_SIZE_CONTENT_PAGE`, `START_PAGE_INDEX`, `POKE_API_HOST`)
4. **Utility extensions**: `core/Extensions.kt` for Kotlin extension functions
5. **String resources**: All hardcoded strings in `res/values/strings.xml`
6. **Fragment inheritance**: Use `PokedexGenericFragment<Binding>` for consistent lifecycle handling
7. **Callback pattern**: UI clicks implement interfaces (e.g., `PokedexOnClickListener`) instead of local listeners

## Common Development Tasks

### Adding a New Screen
1. Create Fragment in `ui/presentation/fragment/`
2. Create ViewModel in `ui/presentation/viewmodel/`
3. Create UseCase interface/implementation in `domain/usecase/`
4. Create DataSource interface/implementation if new data source needed
5. Add all to `PokedexModule.kt`
6. Register in Navigation graph and AndroidManifest

### Adding API Call
1. Create/update Retrofit service interface
2. Create DataSource interface wrapping the service
3. Implement DataSource in `framework/data/source/`
4. Create Repository interface and implementation
5. Create UseCase
6. Inject in ViewModel via DI module

### Running Tests with Coverage
```bash
# Unit tests with JaCoCo report
./gradlew testDebugUnitTestCoverage

# Verify coverage threshold (15% minimum)
./gradlew testDebugUnitTestCoverageVerification

# Run instrumentation tests
./gradlew connectedAndroidTest
```

## Edge Cases & Gotchas

- **Paging initialization**: PagingSource is created lazily per request; initial load happens automatically
- **Flow cancellation**: Fragments using Flow must cancel in `onDestroyView()` (handled by viewModelScope auto-cancel)
- **Data binding**: Requires kapt plugin and ViewBinding enabled; don't mix with findViewById
- **Koin scopes**: Factories create new instances; use `single` only for singletons (currently all factories)
- **ProGuard**: Minification disabled in debug; release mode can break reflection (check proguard-rules.pro)

## External API Integration Points

- **PokéAPI**: Base URL in constants; responses map to domain entities via Retrofit Gson converter
- **Safe Args plugin**: auto-generates direction classes; rebuild if navigation changes not recognized

