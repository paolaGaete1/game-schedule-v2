package com.example.gameschedule

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gameschedule.navigation.Destino
import com.example.gameschedule.ui.screens.horarios.HorariosScreen
import com.example.gameschedule.ui.screens.inicio.InicioScreen
import com.example.gameschedule.ui.screens.torneos.TorneosScreen

/**
 * Esquema general de la app: Bottom Navigation Bar + NavHost.
 *
 * Las 3 pestañas (Inicio, Mis horarios, Torneos) mantienen su propio estado
 * gracias a `saveState` / `restoreState`, y se evita apilar copias de la misma
 * ruta con `launchSingleTop`.
 */
@Composable
fun GameScheduleApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                Destino.tabs.forEach { destino ->
                    NavigationBarItem(
                        selected = rutaActual == destino.ruta,
                        onClick = {
                            navController.navigate(destino.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destino.icono,
                                contentDescription = destino.titulo
                            )
                        },
                        label = { Text(destino.titulo) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destino.Inicio.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destino.Inicio.ruta) {
                InicioScreen()
            }
            composable(Destino.MisHorarios.ruta) {
                HorariosScreen()
            }
            composable(Destino.Torneos.ruta) {
                TorneosScreen()
            }
        }
    }
}
