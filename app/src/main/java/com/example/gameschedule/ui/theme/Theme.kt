package com.example.gameschedule.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Tema de la app con soporte de modo claro y modo oscuro.
 *
 * El esquema se elige con el parámetro [darkTheme], que la UI controla a mano
 * (botón de la barra superior) en lugar de seguir al sistema por defecto.
 */

/** Paleta del modo claro (mockups originales). */
private val EsquemaClaro = lightColorScheme(
    primary = Color(0xFF5B5FE9),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF5B5FE9),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF5B5FE9),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE3E5FD),
    onSecondaryContainer = Color(0xFF1F2594),
    background = Color(0xFFF7F7F9),
    onBackground = Color(0xFF111827),
    surface = Color.White,
    onSurface = Color(0xFF111827),
    surfaceVariant = Color(0xFFD1D5DB),
    onSurfaceVariant = Color(0xFF6B7280),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color.White,
    surfaceContainer = Color.White,
    surfaceContainerHigh = Color(0xFFF0F1F4),
    surfaceContainerHighest = Color(0xFFE9EAEE),
    outline = Color(0xFFD4D4DC),
    outlineVariant = Color(0xFFE7E7EC),
    error = Color(0xFFEF4444),
    onError = Color.White
)

/** Paleta del modo oscuro. */
private val EsquemaOscuro = darkColorScheme(
    primary = Color(0xFF8189F7),
    onPrimary = Color(0xFF0C0E21),
    primaryContainer = Color(0xFF8189F7),
    onPrimaryContainer = Color(0xFF0C0E21),
    secondary = Color(0xFF8189F7),
    onSecondary = Color(0xFF0C0E21),
    secondaryContainer = Color(0xFF323763),
    onSecondaryContainer = Color(0xFFC7CAFF),
    background = Color(0xFF101318),
    onBackground = Color(0xFFE5E7EB),
    surface = Color(0xFF181B22),
    onSurface = Color(0xFFE5E7EB),
    surfaceVariant = Color(0xFF333845),
    onSurfaceVariant = Color(0xFF9CA3AF),
    surfaceContainerLowest = Color(0xFF0C0E12),
    surfaceContainerLow = Color(0xFF14171C),
    surfaceContainer = Color(0xFF181B22),
    surfaceContainerHigh = Color(0xFF1F232B),
    surfaceContainerHighest = Color(0xFF242832),
    outline = Color(0xFF4A5060),
    outlineVariant = Color(0xFF333845),
    error = Color(0xFFF87171),
    onError = Color(0xFF450A0A)
)

/**
 * Aplica el esquema correspondiente según [darkTheme].
 *
 * @param darkTheme true = modo oscuro, false = modo claro.
 *                   Por defecto arranca en claro; la alternancia es manual
 *                   (el estado vive en MainActivity con rememberSaveable).
 */
@Composable
fun GameScheduleTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) EsquemaOscuro else EsquemaClaro,
        content = content
    )
}
