package com.example.gameschedule.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Destinos (pestañas) de la navegación principal.
 *
 * Cada pestaña de la Bottom Navigation Bar corresponde a un [Destino] con:
 * - [ruta]: identificador de la ruta en el NavHost.
 * - [titulo]: etiqueta visible en la barra.
 * - [icono]: icono de la pestaña.
 */
sealed class Destino(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector
) {
    data object Inicio : Destino(
        ruta = "inicio",
        titulo = "Inicio",
        icono = Icons.Filled.Home
    )

    data object MisHorarios : Destino(
        ruta = "horarios",
        titulo = "Mis horarios",
        icono = Icons.Filled.CalendarMonth
    )

    data object Torneos : Destino(
        ruta = "torneos",
        titulo = "Torneos",
        icono = Icons.Filled.EmojiEvents
    )

    companion object {
        /** Orden de las pestañas en la barra inferior. */
        val tabs: List<Destino> = listOf(Inicio, MisHorarios, Torneos)
    }
}
