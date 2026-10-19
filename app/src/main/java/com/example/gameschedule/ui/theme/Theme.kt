package com.example.gameschedule.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Tema base de la app.
 *
 * Por ahora solo aplica MaterialTheme con sus valores por defecto:
 * sin colores ni tipografías personalizados (se definirán en una fase posterior).
 */
@Composable
fun GameScheduleTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}
