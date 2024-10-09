package com.example.login.model

data class Usuario(
    val login: String,
    var senha: String,
    var tentativasErradas: Int = 0,
    var bloqueado: Boolean = false
)
