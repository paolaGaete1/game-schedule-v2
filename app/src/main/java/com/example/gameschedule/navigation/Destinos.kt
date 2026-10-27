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

/**
 * Rutas de las pantallas secundarias (formularios y detalle).
 *
 * Estas pantallas se abren desde las pestañas y ocultan la Bottom Navigation Bar.
 */
object Rutas {
    const val NUEVO_HORARIO = "nuevo_horario"
    const val NUEVO_TORNEO = "nuevo_torneo"
    const val TORNEO_DETALLE = "torneo_detalle"

    /** Ruta del detalle de un torneo concreto. */
    fun detalleTorneo(torneoId: String) = "$TORNEO_DETALLE/$torneoId"

    /** Extrae el id de una ruta de detalle, o null si no lo es. */
    fun idDeDetalle(ruta: String?): String? =
        if (ruta != null && ruta.startsWith("$TORNEO_DETALLE/")) {
            ruta.removePrefix("$TORNEO_DETALLE/")
        } else {
            null
        }
}
