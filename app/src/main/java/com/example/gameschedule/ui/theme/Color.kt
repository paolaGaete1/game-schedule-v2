package com.example.gameschedule.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Paleta semántica de la app.
 *
 * Estas propiedades son @Composable y leen el [MaterialTheme.colorScheme] activo,
 * por lo que cambian automáticamente entre modo claro y modo oscuro.
 * No se deben usar fuera de funciones compositivas.
 */

/** Color principal (botones rellenos, acentos de texto, pestaña activa). */
val MoradoPrimario: Color
    @Composable get() = MaterialTheme.colorScheme.primary

/** Acciones destructivas ("Borrar") y pin de ubicación. */
val RojoBorrar: Color
    @Composable get() = MaterialTheme.colorScheme.error

/** Texto principal (títulos y contenido sobre fondo). */
val TextoPrincipal: Color
    @Composable get() = MaterialTheme.colorScheme.onBackground

/** Texto secundario (fechas, notas, placeholders). */
val TextoSecundario: Color
    @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant

/** Borde suave de tarjetas y campos de formulario. */
val BordeCard: Color
    @Composable get() = MaterialTheme.colorScheme.outlineVariant

/** Fondo de los botones deshabilitados ("Crear horario", "Agregar ronda"). */
val GrisDeshabilitado: Color
    @Composable get() = MaterialTheme.colorScheme.surfaceVariant
