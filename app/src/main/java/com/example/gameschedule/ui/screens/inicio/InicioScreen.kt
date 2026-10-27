package com.example.gameschedule.ui.screens.inicio

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.AppViewModel
import com.example.gameschedule.ui.components.HorarioCard
import com.example.gameschedule.ui.components.SeccionTitulo
import com.example.gameschedule.ui.components.TorneoCard
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario

/**
 * Pestaña "Inicio": acceso rápido a crear horarios/torneos y
 * listas de los próximos horarios y torneos.
 */
@Composable
fun InicioScreen(
    viewModel: AppViewModel,
    onNuevoHorario: () -> Unit,
    onNuevoTorneo: () -> Unit,
    onTorneoClick: (String) -> Unit
) {
    val horarios = viewModel.horarios
    val torneos = viewModel.torneos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Horario de Juegos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Organiza tus horarios de juego y torneos",
            fontSize = 14.sp,
            color = TextoSecundario
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = onNuevoHorario,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "+ Horario")
            }
            OutlinedButton(
                onClick = onNuevoTorneo,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, MoradoPrimario),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MoradoPrimario)
            ) {
                Text(text = "+ Torneo")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SeccionTitulo(texto = "Próximos horarios")
        Spacer(modifier = Modifier.height(8.dp))
        if (horarios.isEmpty()) {
            Text(
                text = "Aún no tienes horarios. Crea el primero con \"+ Horario\".",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                horarios.forEach { horario ->
                    HorarioCard(horario = horario)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SeccionTitulo(texto = "Próximos torneos")
        Spacer(modifier = Modifier.height(8.dp))
        if (torneos.isEmpty()) {
            Text(
                text = "Aún no tienes torneos. Crea el primero con \"+ Torneo\".",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                torneos.forEach { torneo ->
                    TorneoCard(
                        torneo = torneo,
                        onClick = { onTorneoClick(torneo.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
