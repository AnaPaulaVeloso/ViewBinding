package com.example.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.ActivityLoginBinding
import com.example.login.model.Usuario
import com.example.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEntrar.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val senha = binding.edtSenha.text.toString()


            val retorno = viewModel.logar(login, senha)
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show()
        }

        binding.btnCadastrar.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val senha = binding.edtSenha.text.toString()
            
            val user = Usuario(login, senha)
            val retorno = viewModel.cadastrar(user)
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show()
        }
    }
}
