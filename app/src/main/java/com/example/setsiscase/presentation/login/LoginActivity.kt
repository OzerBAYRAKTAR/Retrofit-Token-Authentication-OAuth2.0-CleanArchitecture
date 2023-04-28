package com.example.setsiscase.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.databinding.ActivityLoginBinding
import com.example.setsiscase.presentation.MainActivity
import com.example.setsiscase.util.Resource
import com.example.setsiscase.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel:LoginViewModel
    private var id="testuser"
    private var pass="123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)

        userLogin()
    }
    private fun userLogin() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "kullanıcı adı/şifre boş bırakılamaz", Toast.LENGTH_SHORT).show()
            }else {
                if (username.equals(id) && password.equals(pass)){
                    viewModel.getLogin()
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Toast.makeText(this, "Kullanıcı adı/şifre hatalı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}