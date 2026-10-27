package com.example.gameschedule.ui.screens.detalletorneo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.AppViewModel
import com.example.gameschedule.data.model.Ronda
import com.example.gameschedule.ui.components.CampoFecha
import com.example.gameschedule.ui.components.CampoHora
import com.example.gameschedule.ui.components.CampoTexto
import com.example.gameschedule.ui.components.SeccionTitulo
import com.example.gameschedule.ui.theme.BordeCard
import com.example.gameschedule.ui.theme.GrisDeshabilitado
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.theme.RojoBorrar
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario
import com.example.gameschedule.util.formatear
import com.example.gameschedule.util.formatearNumerica
import com.example.gameschedule.util.formatearTorneo
import java.time.LocalDate
import java.time.LocalTime

/**
 * Detalle de un torneo: información general, rondas y formulario
 * para agregar nuevas rondas.
 */
@Composable
fun DetalleTorneoScreen(
    torneoId: String,
    viewModel: AppViewModel,
    onNoEncontrado: () -> Unit
) {
    val torneo = viewModel.torneoPorId(torneoId)

    if (torneo == null) {
        LaunchedEffect(Unit) { onNoEncontrado() }
        return
    }

    var nombreRonda by remember { mutableStateOf("") }
    var fechaRonda by remember { mutableStateOf(torneo.fecha) }
    var horaRonda by remember { mutableStateOf(torneo.hora) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = torneo.nombre,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${torneo.juego} · Inicia ${torneo.fecha.formatearTorneo()}, ${torneo.hora.formatear()}",
            fontSize = 14.sp,
            color = TextoSecundario
        )

        if (torneo.ubicacion.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Ubicación",
                    tint = RojoBorrar,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = torneo.ubicacion,
                    fontSize = 14.sp,
                    color = TextoSecundario
                )
            }
        }

        if (torneo.notas.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = torneo.notas,
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        SeccionTitulo(texto = "Rondas")
        Spacer(modifier = Modifier.height(8.dp))

        if (torneo.rondas.isEmpty()) {
            Text(
                text = "Aún no hay rondas. Agrega la primera aquí abajo.",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                torneo.rondas.forEach { ronda ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, BordeCard)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
                            Text(
                                text = ronda.nombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextoPrincipal
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${ronda.fecha.formatearNumerica()}, ${ronda.hora.formatear()}",
                                fontSize = 13.sp,
                                color = TextoSecundario
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SeccionTitulo(texto = "Agregar ronda")
        Spacer(modifier = Modifier.height(8.dp))

        CampoTexto(
            etiqueta = null,
            valor = nombreRonda,
            onValorChange = { nombreRonda = it },
            placeholder = "Ej. Ronda 1 - Octavos"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CampoFecha(
                etiqueta = "Fecha",
                fecha = fechaRonda,
                onFechaChange = { fechaRonda = it },
                modifier = Modifier.weight(1f)
            )
            CampoHora(
                etiqueta = "Hora",
                hora = horaRonda,
                onHoraChange = { horaRonda = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.agregarRonda(
                    torneoId = torneo.id,
                    ronda = Ronda(
                        numero = torneo.rondas.size + 1,
                        nombre = nombreRonda.trim(),
                        fecha = fechaRonda,
                        hora = horaRonda
                    )
                )
                nombreRonda = ""
            },
            enabled = nombreRonda.isNotBlank(),
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
            Text(text = "Agregar ronda")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
