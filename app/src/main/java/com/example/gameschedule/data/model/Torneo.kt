package com.example.gameschedule.data.model

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 * Un torneo al que se participa.
 *
 * @property id Identificador único del torneo.
 * @property nombre Nombre del torneo (ej. "Copa Comunidad ES").
 * @property juego Juego en el que se compite.
 * @property fecha Día del torneo.
 * @property hora Hora de inicio.
 * @property ubicacion Lugar o plataforma (ej. "Online", "Madrid - Arena").
 * @property notas Comentarios libres (opcional).
 * @property rondas Rondas del torneo (vacío mientras no se configuren).
 */
data class Torneo(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val juego: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val ubicacion: String,
    val notas: String = "",
    val rondas: List<Ronda> = emptyList()
)

/**
 * Ronda dentro de un torneo.
 *
 * @property numero Orden de la ronda (1, 2, 3...).
 * @property nombre Nombre visible (ej. "Cuartos de final", "Semifinales").
 * @property fecha Día en el que se juega la ronda.
 * @property hora Hora de inicio de la ronda.
 */
data class Ronda(
    val numero: Int,
    val nombre: String,
    val fecha: LocalDate,
    val hora: LocalTime
)
