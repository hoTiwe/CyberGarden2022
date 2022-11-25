package com.example.oggetto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.oggetto.databinding.ActivityMainBinding
import com.example.oggetto.fragments.Fragment1
import com.example.oggetto.fragments.Fragment2

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fragmentBtn1.setOnClickListener{
            replaceFragment(Fragment1())

        }

        binding.fragmentBtn2.setOnClickListener{
            replaceFragment(Fragment2())

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmenConteiner, fragment)
        fragmentTransaction.commit()

    }
}