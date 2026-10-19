package com.example.gameschedule.data.model

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 * Una sesión de juego programada.
 *
 * @property id Identificador único del horario.
 * @property juego Nombre del juego (ej. "Valorant").
 * @property fecha Día de la sesión.
 * @property hora Hora de inicio.
 * @property amigos Personas que van a jugar.
 * @property notas Comentarios libres (opcional).
 */
data class Horario(
    val id: String = UUID.randomUUID().toString(),
    val juego: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val amigos: List<String> = emptyList(),
    val notas: String = ""
)
