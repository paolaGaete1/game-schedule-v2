package com.example.gameschedule.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.model.Horario
import com.example.gameschedule.ui.theme.BordeCard
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.theme.RojoBorrar
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario
import com.example.gameschedule.util.formatear
import com.example.gameschedule.util.formatearCorta

/**
 * Tarjeta de un horario de juego.
 *
 * @param onBorrar Si se pasa, muestra el enlace "Borrar" (solo en "Mis horarios").
 */
@Composable
fun HorarioCard(
    horario: Horario,
    modifier: Modifier = Modifier,
    onBorrar: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, BordeCard)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = horario.juego,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextoPrincipal,
                    modifier = Modifier.weight(1f)
                )
                if (onBorrar != null) {
                    Text(
                        text = "Borrar",
                        fontSize = 13.sp,
                        color = RojoBorrar,
                        modifier = Modifier
                            .clickable(onClick = onBorrar)
                            .padding(start = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${horario.fecha.formatearCorta()} · ${horario.hora.formatear()}",
                fontSize = 13.sp,
                color = TextoSecundario
            )

            if (horario.amigos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Con: ${horario.amigos.joinToString(", ")}",
                    fontSize = 13.sp,
                    color = MoradoPrimario
                )
            }

            if (horario.notas.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = horario.notas,
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }
        }
    }
}
