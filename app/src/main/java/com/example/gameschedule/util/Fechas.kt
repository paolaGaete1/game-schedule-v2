package com.example.gameschedule.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Formateadores de fecha y hora en español, compartidos por todas las pantallas.
 */

private val localeEs = Locale.forLanguageTag("es-ES")

/** "lun, 05 oct" */
private val formatoFechaCorta = DateTimeFormatter.ofPattern("EEE, dd MMM", localeEs)

/** "07 oct 2026" */
private val formatoFechaTorneo = DateTimeFormatter.ofPattern("dd MMM yyyy", localeEs)

/** "7/10/2026" */
private val formatoFechaNumerica = DateTimeFormatter.ofPattern("d/M/yyyy", localeEs)

/** "20:30" */
private val formatoHora = DateTimeFormatter.ofPattern("HH:mm", localeEs)

/** "05-10-2026" (formato de campo de formulario) */
private val formatoFechaCampo = DateTimeFormatter.ofPattern("dd-MM-yyyy", localeEs)

fun LocalDate.formatearCorta(): String = formatoFechaCorta.format(this)

fun LocalDate.formatearTorneo(): String = formatoFechaTorneo.format(this)

fun LocalDate.formatearNumerica(): String = formatoFechaNumerica.format(this)

fun LocalDate.formatearCampo(): String = formatoFechaCampo.format(this)

fun LocalTime.formatear(): String = formatoHora.format(this)
