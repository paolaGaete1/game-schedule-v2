package com.example.gameschedule.ui.screens.nuevotorneo

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
import androidx.compose.ui.unit.dp
import com.example.gameschedule.data.model.Torneo
import com.example.gameschedule.ui.components.CampoFecha
import com.example.gameschedule.ui.components.CampoHora
import com.example.gameschedule.ui.components.CampoTexto
import com.example.gameschedule.ui.theme.GrisDeshabilitado
import com.example.gameschedule.ui.theme.MoradoPrimario
import java.time.LocalDate
import java.time.LocalTime

/**
 * Formulario para crear un torneo.
 *
 * El botón "Crear torneo" está deshabilitado hasta que se indiquen
 * al menos el nombre y el juego.
 */
@Composable
fun NuevoTorneoScreen(
    onCrear: (Torneo) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var juego by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(LocalDate.now()) }
    var hora by remember { mutableStateOf(LocalTime.now()) }
    var ubicacion by remember { mutableStateOf("") }

    val puedeCrear = nombre.isNotBlank() && juego.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        CampoTexto(
            etiqueta = "Nombre del torneo",
            valor = nombre,
            onValorChange = { nombre = it },
            placeholder = "Ej. CopaLocal de Verano"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            etiqueta = "Juego",
            valor = juego,
            onValorChange = { juego = it },
            placeholder = "Ej. Valorant, League of Legends..."
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
            etiqueta = "Ubicación",
            valor = ubicacion,
            onValorChange = { ubicacion = it },
            placeholder = "Ej. Ciber Café Nova o Online"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onCrear(
                    Torneo(
                        nombre = nombre.trim(),
                        juego = juego.trim(),
                        fecha = fecha,
                        hora = hora,
                        ubicacion = ubicacion.trim()
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
            Text(text = "Crear torneo")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
