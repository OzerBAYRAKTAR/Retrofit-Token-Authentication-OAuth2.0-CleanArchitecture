package com.example.setsiscase.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.setsiscase.R
import com.example.setsiscase.databinding.ActivityMainBinding
import com.example.setsiscase.presentation.cart.CartFragment
import com.example.setsiscase.presentation.category.CategoryFragment
import com.example.setsiscase.presentation.home.HomeFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backPressed()

    binding.bottomNavigation.setOnItemSelectedListener {
        when (it.itemId) {

            R.id.homeFragment -> replaceFragment(HomeFragment())
            R.id.categoryFragment -> replaceFragment(CategoryFragment())
            R.id.cartFragment -> replaceFragment(CartFragment())
        }
        true
    }
    }
    fun replaceFragment(fragment: Fragment) {
    val fragmentManager=supportFragmentManager
    val fragmentTransaction=fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
    fragmentTransaction.commit()
}
    fun backPressed() {
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                Snackbar.make(binding.root,"Çıkış yapmak istediğinize emin misiniz?",Snackbar.LENGTH_LONG)
                    .setAction("Evet"){
                        finish()
                    }.show()
            }
        }
        this.onBackPressedDispatcher.addCallback(
            this,callback
        )
    }


}