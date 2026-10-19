# game-schedule-v2

App Android (Kotlin + Jetpack Compose) para gestionar horarios de juegos y torneos.
Version dos de mi app para horarios y torneos

## Estructura

```
app/src/main/java/com/example/gameschedule/
├── MainActivity.kt                  # Punto de entrada
├── GameScheduleApp.kt               # Scaffold + Bottom Nav + NavHost
├── navigation/
│   └── Destinos.kt                  # Rutas/pestañas: Inicio, Mis horarios, Torneos
├── data/model/
│   ├── Horario.kt                   # Horario (juego, fecha, hora, amigos, notas)
│   └── Torneo.kt                    # Torneo + Ronda (nombre, juego, fecha/hora, ubicación, rondas)
├── ui/screens/
│   ├── inicio/InicioScreen.kt       # Placeholder
│   ├── horarios/HorariosScreen.kt   # Placeholder
│   └── torneos/TorneosScreen.kt     # Placeholder
└── ui/theme/
    └── Theme.kt                     # MaterialTheme por defecto (sin estilos finales)
```

## Stack

- AGP 9.4.1 + Gradle 9.6.0 (built-in Kotlin 2.2.10)
- Compose BOM 2026.09.00, Material 3, Navigation Compose
- minSdk 26 / compileSdk 37 (`java.time` nativo)

## Build

```powershell
.\gradlew assembleDebug
```
