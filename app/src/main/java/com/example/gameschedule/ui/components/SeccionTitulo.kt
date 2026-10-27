package com.example.gameschedule.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameschedule.ui.theme.TextoPrincipal

/**
 * Título de sección de las pantallas (ej. "Próximos horarios", "Rondas").
 */
@Composable
fun SeccionTitulo(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = TextoPrincipal,
        modifier = modifier.padding(vertical = 4.dp)
    )
}
