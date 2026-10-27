package com.example.gameschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.gameschedule.ui.theme.GameScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Estado del tema en memoria: se mantiene mientras la app esté abierta
            // (rememberSaveable lo conserva también al rotar la pantalla).
            var modoOscuro by rememberSaveable { mutableStateOf(false) }

            GameScheduleTheme(darkTheme = modoOscuro) {
                GameScheduleApp(
                    modoOscuro = modoOscuro,
                    onAlternarTema = { modoOscuro = !modoOscuro }
                )
            }
        }
    }
}
