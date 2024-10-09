package com.example.login.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.login.model.Usuario

class LoginViewModel : ViewModel() {
    private val usuarios = mutableListOf<Usuario>()

    private fun encontrarUsuarioPorLogin(login: String): Usuario? {
        return usuarios.find { it.login == login }
    }

    fun logar(user: Usuario): String {
        val usuario = encontrarUsuarioPorLogin(user.login)

        usuario?.let {
            if (it.bloqueado) {
                return "Usuário bloqueado!"
            }

            if (user.senha == it.senha) {
                it.tentativasErradas = 0
                return "Login realizado com sucesso"
            } else {
                it.tentativasErradas++
                if (it.tentativasErradas >= 3) {
                    it.bloqueado = true
                    return "Usuário bloqueado após 3 tentativas falhas."
                }
                return "Senha incorreta. Tentativas restantes: ${3 - it.tentativasErradas}"
            }
        } ?: run {
            return "Usuário não encontrado."
        }
    }

    fun cadastrar(user: Usuario): String {
        if (encontrarUsuarioPorLogin(user.login) != null) {
            return "Erro: Usuário já cadastrado."
        }

        usuarios.add(user.apply {
            tentativasErradas = 0
            bloqueado = false
        })
        return "Cadastrado com sucesso"
    }

    fun listarUsuarios(): List<Usuario> {
        return usuarios
    }
}
