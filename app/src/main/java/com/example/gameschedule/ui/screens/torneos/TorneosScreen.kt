package com.example.gameschedule.ui.screens.torneos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.AppViewModel
import com.example.gameschedule.ui.components.TorneoCard
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario

/**
 * Pestaña "Torneos": listado de torneos; al pulsar una tarjeta se abre su detalle.
 */
@Composable
fun TorneosScreen(
    viewModel: AppViewModel,
    onNuevo: () -> Unit,
    onTorneoClick: (String) -> Unit
) {
    val torneos = viewModel.torneos

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
                text = "Torneos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
            Button(
                onClick = onNuevo,
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = "+ Nuevo", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (torneos.isEmpty()) {
            Text(
                text = "Aún no tienes torneos. Toca \"+ Nuevo\" para crear el primero.",
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
