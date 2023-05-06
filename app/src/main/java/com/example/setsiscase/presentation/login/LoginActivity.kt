package com.example.setsiscase.presentation.login


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.setsiscase.data.source.api.Resource
import com.example.setsiscase.databinding.ActivityLoginBinding
import com.example.setsiscase.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


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

        viewModel.loginResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(
                            it.value.token.accessToken,
                            it.value.token.refreshToken
                        )
                        //viewModel.getLogin(id,pass)
                        Toast.makeText(this@LoginActivity, "Giriş Başarılı", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure ->  {
                    Log.d(TAG,"ERROR")
                }
                else -> {}
            }
        })
    }

    private fun userLogin() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "kullanıcı adı/şifre boş bırakılamaz", Toast.LENGTH_SHORT).show()
            }else {
                if (username.equals(id) && password.equals(pass)){
                    viewModel.getLogin(id,pass)
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "Kullanıcı adı/şifre hatalı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}