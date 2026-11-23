package com.example.apppreferencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemaApp {
                val context = LocalContext.current
                val preferenciasManager = remember { PreferenciasManager(context) }
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                val nombreUsuario by preferenciasManager.nombreFlow.collectAsState(initial = "Invitado")
                val contrasenaUsuario by preferenciasManager.contrasenaFlow.collectAsState(initial = "")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Ruta.Acceso.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Ruta.Acceso.route) {
                            PantallaAcceso(
                                nombreActual = nombreUsuario,
                                onEntrar = {
                                    navController.navigate(Ruta.Inicio.route)
                                }
                            )
                        }
                        composable(Ruta.Inicio.route) {
                            PantallaInicio(
                                nombreUsuario = nombreUsuario,
                                onIrAPreferencias = {
                                    navController.navigate(Ruta.Preferencias.route)
                                },
                                onCerrarSesion = {
                                    navController.popBackStack(Ruta.Acceso.route, inclusive = false)
                                }
                            )
                        }
                        composable(Ruta.Preferencias.route) {
                            PantallaPreferencias(
                                nombreInicial = nombreUsuario,
                                contrasenaInicial = contrasenaUsuario,
                                onGuardar = { nuevoNombre, nuevaContrasena ->
                                    scope.launch {
                                        preferenciasManager.guardarPreferencias(nuevoNombre, nuevaContrasena)
                                    }
                                },
                                onVolver = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
