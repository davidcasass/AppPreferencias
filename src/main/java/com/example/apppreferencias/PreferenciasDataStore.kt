package com.example.apppreferencias

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencias_usuario")

class PreferenciasManager(private val context: Context) {

    companion object {
        val CLAVE_NOMBRE = stringPreferencesKey("nombre_usuario")
        val CLAVE_CONTRASENA = stringPreferencesKey("contrasena_usuario")
    }

    val nombreFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[CLAVE_NOMBRE] ?: "Invitado"
    }

    val contrasenaFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[CLAVE_CONTRASENA] ?: ""
    }

    suspend fun guardarPreferencias(nombre: String, contrasena: String) {
        context.dataStore.edit { prefs ->
            prefs[CLAVE_NOMBRE] = nombre.trim().ifEmpty { "Invitado" }
            prefs[CLAVE_CONTRASENA] = contrasena
        }
    }
}