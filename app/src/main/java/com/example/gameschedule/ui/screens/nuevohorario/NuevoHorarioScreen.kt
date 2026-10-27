package com.example.gameschedule.ui.screens.nuevohorario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gameschedule.data.model.Horario
import com.example.gameschedule.ui.components.CampoFecha
import com.example.gameschedule.ui.components.CampoHora
import com.example.gameschedule.ui.components.CampoTexto
import com.example.gameschedule.ui.theme.GrisDeshabilitado
import com.example.gameschedule.ui.theme.MoradoPrimario
import java.time.LocalDate
import java.time.LocalTime

/**
 * Formulario para crear un horario de juego.
 *
 * El botón "Crear horario" está deshabilitado hasta que se indique el juego
 * (fecha y hora vienen precargadas con la hora actual).
 */
@Composable
fun NuevoHorarioScreen(
    onCrear: (Horario) -> Unit
) {
    var juego by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(LocalDate.now()) }
    var hora by remember { mutableStateOf(LocalTime.now()) }
    var amigos by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    val puedeCrear = juego.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        CampoTexto(
            etiqueta = "Juego",
            valor = juego,
            onValorChange = { juego = it },
            placeholder = "Ej. Valorant, Ajedrez, Smash..."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CampoFecha(
                etiqueta = "Fecha",
                fecha = fecha,
                onFechaChange = { fecha = it },
                modifier = Modifier.weight(1f)
            )
            CampoHora(
                etiqueta = "Hora",
                hora = hora,
                onHoraChange = { hora = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            etiqueta = "Amigos",
            valor = amigos,
            onValorChange = { amigos = it },
            placeholder = "Ej. Ana, Luis"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            etiqueta = "Notas",
            valor = notas,
            onValorChange = { notas = it },
            placeholder = "Opcional",
            singleLine = false,
            minLines = 4,
            maxLines = 6
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onCrear(
                    Horario(
                        juego = juego.trim(),
                        fecha = fecha,
                        hora = hora,
                        amigos = amigos
                            .split(",")
                            .map { it.trim() }
                            .filter { it.isNotEmpty() },
                        notas = notas.trim()
                    )
                )
            },
            enabled = puedeCrear,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MoradoPrimario,
                disabledContainerColor = GrisDeshabilitado,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Crear horario")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
