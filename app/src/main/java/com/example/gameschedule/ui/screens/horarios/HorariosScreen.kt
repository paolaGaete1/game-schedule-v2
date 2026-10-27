package com.example.gameschedule.ui.screens.horarios

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.AppViewModel
import com.example.gameschedule.ui.components.HorarioCard
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario

/**
 * Pestaña "Mis horarios": listado completo con opción de borrar cada horario.
 */
@Composable
fun HorariosScreen(
    viewModel: AppViewModel,
    onNuevo: () -> Unit
) {
    val horarios = viewModel.horarios

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mis horarios",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
            Button(
                onClick = onNuevo,
                shape = RoundedCornerShape(50),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "+ Nuevo", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (horarios.isEmpty()) {
            Text(
                text = "Aún no tienes horarios. Toca \"+ Nuevo\" para crear el primero.",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                horarios.forEach { horario ->
                    HorarioCard(
                        horario = horario,
                        onBorrar = { viewModel.borrarHorario(horario.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
