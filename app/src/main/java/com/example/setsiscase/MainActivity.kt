package com.example.setsiscase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.setsiscase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
}