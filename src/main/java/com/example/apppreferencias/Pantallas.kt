package com.example.apppreferencias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PantallaAcceso(
    nombreActual: String,
    onEntrar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Acceso",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Usuario actual guardado: $nombreActual",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onEntrar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}

@Composable
fun PantallaInicio(
    nombreUsuario: String,
    onIrAPreferencias: () -> Unit,
    onCerrarSesion: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hola, $nombreUsuario",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Pantalla principal de la aplicación.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(28.dp))
        Button(
            onClick = onIrAPreferencias,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Preferencias")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = onCerrarSesion,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a Acceso")
        }
    }
}

@Composable
fun PantallaPreferencias(
    nombreInicial: String,
    contrasenaInicial: String,
    onGuardar: (String, String) -> Unit,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember(nombreInicial) { mutableStateOf(nombreInicial) }
    var contrasena by remember(contrasenaInicial) { mutableStateOf(contrasenaInicial) }
    var guardadoOk by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Preferencias",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                guardadoOk = false
            },
            label = { Text("Nombre de usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                guardadoOk = false
            },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onGuardar(nombre, contrasena)
                guardadoOk = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a inicio")
        }

        if (guardadoOk) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Preferencias guardadas en DataStore",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}