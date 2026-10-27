package com.example.gameschedule.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gameschedule.data.model.Horario
import com.example.gameschedule.data.model.Ronda
import com.example.gameschedule.data.model.Torneo
import com.example.gameschedule.data.sample.SampleData

/**
 * Estado en memoria de la app: horarios y torneos.
 *
 * Se inicializa con [SampleData] y permite crear/borrar mientras la app está viva.
 * La persistencia real (Room) llegará en una fase posterior sin tocar la UI.
 */
class AppViewModel : ViewModel() {

    private val _horarios = mutableStateListOf<Horario>().apply { addAll(SampleData.horarios) }
    private val _torneos = mutableStateListOf<Torneo>().apply { addAll(SampleData.torneos) }

    /** Horarios ordenados por fecha y hora (los próximos primero). */
    val horarios: List<Horario>
        get() = _horarios.sortedWith(compareBy({ it.fecha }, { it.hora }))

    /** Torneos ordenados por fecha y hora de inicio. */
    val torneos: List<Torneo>
        get() = _torneos.sortedWith(compareBy({ it.fecha }, { it.hora }))

    fun agregarHorario(horario: Horario) {
        _horarios.add(horario)
    }

    fun borrarHorario(id: String) {
        _horarios.removeAll { it.id == id }
    }

    fun agregarTorneo(torneo: Torneo) {
        _torneos.add(torneo)
    }

    fun agregarRonda(torneoId: String, ronda: Ronda) {
        val indice = _torneos.indexOfFirst { it.id == torneoId }
        if (indice == -1) return
        val torneo = _torneos[indice]
        val nuevaRonda = ronda.copy(numero = torneo.rondas.size + 1)
        _torneos[indice] = torneo.copy(rondas = torneo.rondas + nuevaRonda)
    }

    fun torneoPorId(id: String): Torneo? = _torneos.find { it.id == id }
}
