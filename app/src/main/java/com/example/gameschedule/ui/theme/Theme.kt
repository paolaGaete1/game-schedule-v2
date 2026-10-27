package com.example.gameschedule.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Tema de la app.
 *
 * Por ahora solo tema claro con la paleta básica de los mockups;
 * el tema oscuro y la tipografía personalizada llegarán en otra fase.
 */
private val EsquemaClaro = lightColorScheme(
    primary = MoradoPrimario,
    onPrimary = Color.White,
    primaryContainer = MoradoPrimario,
    onPrimaryContainer = Color.White,
    secondary = MoradoPrimario,
    onSecondary = Color.White,
    background = FondoApp,
    onBackground = TextoPrincipal,
    surface = Color.White,
    onSurface = TextoPrincipal,
    surfaceVariant = Color.White,
    onSurfaceVariant = TextoSecundario,
    outline = BordeCard,
    error = RojoBorrar,
    onError = Color.White
)

@Composable
fun GameScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Solo tema claro por ahora; darkTheme se tendrá en cuenta en una fase posterior.
    MaterialTheme(
        colorScheme = EsquemaClaro,
        content = content
    )
}
