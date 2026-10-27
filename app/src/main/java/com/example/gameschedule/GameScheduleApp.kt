package com.example.gameschedule

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gameschedule.data.AppViewModel
import com.example.gameschedule.navigation.Destino
import com.example.gameschedule.navigation.Rutas
import com.example.gameschedule.ui.theme.MoradoPrimario
import com.example.gameschedule.ui.screens.detalletorneo.DetalleTorneoScreen
import com.example.gameschedule.ui.screens.horarios.HorariosScreen
import com.example.gameschedule.ui.screens.inicio.InicioScreen
import com.example.gameschedule.ui.screens.nuevohorario.NuevoHorarioScreen
import com.example.gameschedule.ui.screens.nuevotorneo.NuevoTorneoScreen
import com.example.gameschedule.ui.screens.torneos.TorneosScreen

/**
 * Esquema general de la app: TopAppBar + Bottom Navigation Bar + NavHost.
 *
 * - Las 3 pestañas (Inicio, Mis horarios, Torneos) mantienen su estado con
 *   `saveState` / `restoreState` y no apilan copias gracias a `launchSingleTop`.
 * - Las rutas secundarias (formularios y detalle) ocultan la barra inferior
 *   y muestran una TopAppBar centrada con botón "Atrás".
 * - La TopAppBar de las pestañas incluye un botón para alternar entre
 *   modo claro y modo oscuro ([modoOscuro] / [onAlternarTema]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScheduleApp(
    modoOscuro: Boolean,
    onAlternarTema: () -> Unit,
    appViewModel: AppViewModel = viewModel()
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    val esPestaña = Destino.tabs.any { it.ruta == rutaActual }
    val idTorneoDetalle = Rutas.idDeDetalle(rutaActual)
    val esSecundaria = rutaActual == Rutas.NUEVO_HORARIO ||
            rutaActual == Rutas.NUEVO_TORNEO ||
            idTorneoDetalle != null

    val tituloTopBar = when {
        rutaActual == Destino.Inicio.ruta -> Destino.Inicio.titulo
        rutaActual == Destino.MisHorarios.ruta -> Destino.MisHorarios.titulo
        rutaActual == Destino.Torneos.ruta -> Destino.Torneos.titulo
        rutaActual == Rutas.NUEVO_HORARIO -> "Nuevo horario"
        rutaActual == Rutas.NUEVO_TORNEO -> "Nuevo torneo"
        idTorneoDetalle != null -> "Torneo"
        else -> ""
    }

    fun irA(ruta: String) {
        navController.navigate(ruta)
    }

    Scaffold(
        topBar = {
            when {
                esPestaña -> TopAppBar(
                    title = {
                        Text(
                            text = tituloTopBar,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    actions = {
                        IconButton(onClick = onAlternarTema) {
                            Icon(
                                imageVector = if (modoOscuro) {
                                    Icons.Filled.LightMode
                                } else {
                                    Icons.Filled.DarkMode
                                },
                                contentDescription = if (modoOscuro) {
                                    "Cambiar a modo claro"
                                } else {
                                    "Cambiar a modo oscuro"
                                },
                                tint = MoradoPrimario
                            )
                        }
                    }
                )

                esSecundaria -> CenterAlignedTopAppBar(
                    title = { Text(text = tituloTopBar, fontWeight = FontWeight.Medium) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (esPestaña) {
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destino.Inicio.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destino.Inicio.ruta) {
                InicioScreen(
                    viewModel = appViewModel,
                    onNuevoHorario = { irA(Rutas.NUEVO_HORARIO) },
                    onNuevoTorneo = { irA(Rutas.NUEVO_TORNEO) },
                    onTorneoClick = { irA(Rutas.detalleTorneo(it)) }
                )
            }
            composable(Destino.MisHorarios.ruta) {
                HorariosScreen(
                    viewModel = appViewModel,
                    onNuevo = { irA(Rutas.NUEVO_HORARIO) }
                )
            }
            composable(Destino.Torneos.ruta) {
                TorneosScreen(
                    viewModel = appViewModel,
                    onNuevo = { irA(Rutas.NUEVO_TORNEO) },
                    onTorneoClick = { irA(Rutas.detalleTorneo(it)) }
                )
            }
            composable(Rutas.NUEVO_HORARIO) {
                NuevoHorarioScreen(
                    onCrear = { horario ->
                        appViewModel.agregarHorario(horario)
                        navController.popBackStack()
                    }
                )
            }
            composable(Rutas.NUEVO_TORNEO) {
                NuevoTorneoScreen(
                    onCrear = { torneo ->
                        appViewModel.agregarTorneo(torneo)
                        navController.popBackStack()
                    }
                )
            }
            composable("${Rutas.TORNEO_DETALLE}/{torneoId}") { entry ->
                val torneoId = entry.arguments?.getString("torneoId")
                if (torneoId == null) {
                    navController.popBackStack()
                } else {
                    DetalleTorneoScreen(
                        torneoId = torneoId,
                        viewModel = appViewModel,
                        onNoEncontrado = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
