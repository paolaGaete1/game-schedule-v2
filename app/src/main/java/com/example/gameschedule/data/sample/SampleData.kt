package com.example.gameschedule.data.sample

import com.example.gameschedule.data.model.Horario
import com.example.gameschedule.data.model.Ronda
import com.example.gameschedule.data.model.Torneo
import java.time.LocalDate
import java.time.LocalTime

/**
 * Datos de ejemplo que se cargan al arrancar la app.
 *
 * Son provisionales: se sustituirán por los datos reales (Room) en una fase posterior.
 */
object SampleData {

    val horarios: List<Horario> = listOf(
        Horario(
            juego = "Valorant",
            fecha = LocalDate.of(2026, 10, 5),
            hora = LocalTime.of(20, 30),
            amigos = listOf("Ana", "Luis"),
            notas = "ranked hasta las 23h"
        ),
        Horario(
            juego = "League of Legends",
            fecha = LocalDate.of(2026, 10, 6),
            hora = LocalTime.of(19, 0),
            amigos = listOf("Marco")
        ),
        Horario(
            juego = "Rocket League",
            fecha = LocalDate.of(2026, 10, 8),
            hora = LocalTime.of(18, 0),
            notas = "partida doble con Nico"
        ),
        Horario(
            juego = "Overwatch 2",
            fecha = LocalDate.of(2026, 10, 10),
            hora = LocalTime.of(21, 30),
            amigos = listOf("Sofía", "Diego", "Nico")
        )
    )

    val torneos: List<Torneo> = listOf(
        Torneo(
            nombre = "CopaLocal de Verano",
            juego = "Valorant",
            fecha = LocalDate.of(2026, 10, 7),
            hora = LocalTime.of(17, 0),
            ubicacion = "Ciber Café Nova, Plaza Mayor",
            notas = "Bring your own mouse",
            rondas = listOf(
                Ronda(
                    numero = 1,
                    nombre = "Cuartos de final",
                    fecha = LocalDate.of(2026, 10, 7),
                    hora = LocalTime.of(17, 0)
                ),
                Ronda(
                    numero = 2,
                    nombre = "Semifinales",
                    fecha = LocalDate.of(2026, 10, 7),
                    hora = LocalTime.of(19, 0)
                ),
                Ronda(
                    numero = 3,
                    nombre = "Final",
                    fecha = LocalDate.of(2026, 10, 7),
                    hora = LocalTime.of(21, 0)
                )
            )
        ),
        Torneo(
            nombre = "Torneo Amistoso MLBB",
            juego = "Mobile Legends",
            fecha = LocalDate.of(2026, 10, 14),
            hora = LocalTime.of(16, 0),
            ubicacion = "Online",
            rondas = listOf(
                Ronda(
                    numero = 1,
                    nombre = "Ronda 1",
                    fecha = LocalDate.of(2026, 10, 14),
                    hora = LocalTime.of(16, 0)
                ),
                Ronda(
                    numero = 2,
                    nombre = "Final",
                    fecha = LocalDate.of(2026, 10, 14),
                    hora = LocalTime.of(18, 0)
                )
            )
        )
    )
}
