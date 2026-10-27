package com.example.gameschedule.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.data.model.Torneo
import com.example.gameschedule.ui.theme.BordeCard
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.theme.RojoBorrar
import com.example.gameschedule.ui.theme.TextoPrincipal
import com.example.gameschedule.ui.theme.TextoSecundario
import com.example.gameschedule.util.formatear
import com.example.gameschedule.util.formatearTorneo

/**
 * Tarjeta de un torneo.
 *
 * @param onClick Al pulsarla abre el detalle del torneo.
 */
@Composable
fun TorneoCard(
    torneo: Torneo,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BordeCard),
        onClick = onClick ?: {}
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            Text(
                text = torneo.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextoPrincipal
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${torneo.juego} · Inicia ${torneo.fecha.formatearTorneo()} · ${torneo.hora.formatear()}",
                fontSize = 13.sp,
                color = TextoSecundario
            )

            if (torneo.ubicacion.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Ubicación",
                        tint = RojoBorrar,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = torneo.ubicacion,
                        fontSize = 13.sp,
                        color = TextoSecundario
                    )
                }
            }

            if (torneo.notas.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = torneo.notas,
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }

            if (torneo.rondas.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "${torneo.rondas.size} rondas",
                    fontSize = 13.sp,
                    color = MoradoPrimario
                )
            }
        }
    }
}
