package com.example.gameschedule.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import com.example.gameschedule.ui.theme.BordeCard
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario
import com.example.gameschedule.util.formatear
import com.example.gameschedule.util.formatearCampo
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

/**
 * Campos de formulario compartidos (etiqueta encima + caja con placeholder),
 * tal y como aparecen en los mockups de "Nuevo horario" y "Agregar ronda".
 */

@Composable
private fun Etiqueta(texto: String) {
    Text(
        text = texto,
        fontSize = 13.sp,
        color = TextoSecundario,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun coloresCampo() =
    OutlinedTextFieldDefaults.colors(
    focusedTextColor = TextoPrincipal,
    unfocusedTextColor = TextoPrincipal,
    cursorColor = MoradoPrimario,
    focusedBorderColor = MoradoPrimario,
    unfocusedBorderColor = BordeCard,
    focusedPlaceholderColor = TextoSecundario,
    unfocusedPlaceholderColor = TextoSecundario,
    disabledTextColor = TextoPrincipal,
    disabledBorderColor = BordeCard,
    disabledPlaceholderColor = TextoSecundario,
    disabledLabelColor = TextoSecundario,
    disabledTrailingIconColor = TextoSecundario
)

/** Campo de texto libre con etiqueta superior y placeholder. */
@Composable
fun CampoTexto(
    etiqueta: String?,
    valor: String,
    onValorChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(modifier = modifier) {
        if (etiqueta != null) {
            Etiqueta(etiqueta)
        }
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = singleLine,
            minLines = minLines,
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(10.dp),
            colors = coloresCampo()
        )
    }
}

/** Campo de fecha que abre un DatePicker al pulsarlo. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoFecha(
    etiqueta: String,
    fecha: LocalDate,
    onFechaChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Etiqueta(etiqueta)
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { mostrarDialogo = true }
        ) {
            OutlinedTextField(
                value = fecha.formatearCampo(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Elegir fecha"
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = coloresCampo()
            )
        }
    }

    if (mostrarDialogo) {
        val estado = rememberDatePickerState(
            initialSelectedDateMillis = fecha.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { mostrarDialogo = false },
            confirmButton = {
                TextButton(onClick = {
                    estado.selectedDateMillis?.let { millis ->
                        onFechaChange(
                            Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                        )
                    }
                    mostrarDialogo = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = estado)
        }
    }
}

/** Campo de hora que abre un TimePicker al pulsarlo. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoHora(
    etiqueta: String,
    hora: LocalTime,
    onHoraChange: (LocalTime) -> Unit,
    modifier: Modifier = Modifier
) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Etiqueta(etiqueta)
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { mostrarDialogo = true }
        ) {
            OutlinedTextField(
                value = hora.formatear(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = "Elegir hora"
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = coloresCampo()
            )
        }
    }

    if (mostrarDialogo) {
        val estado = rememberTimePickerState(
            initialHour = hora.hour,
            initialMinute = hora.minute,
            is24Hour = true
        )
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Seleccionar hora") },
            text = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    TimePicker(state = estado)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onHoraChange(LocalTime.of(estado.hour, estado.minute))
                    mostrarDialogo = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) { Text("Cancelar") }
            }
        )
    }
}
