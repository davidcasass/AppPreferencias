package com.example.apppreferencias

sealed class Ruta(val route: String) {
    data object Acceso : Ruta("acceso")
    data object Inicio : Ruta("inicio")
    data object Preferencias : Ruta("preferencias")
}