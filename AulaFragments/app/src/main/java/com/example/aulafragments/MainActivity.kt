package com.example.aulafragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aulafragments.databinding.ActivityMainBinding
import com.example.aulafragments.fragments.ChamadasFragment
import com.example.aulafragments.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnConversas: Button
    private lateinit var btnChamadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnConversas = binding.btnConversas
        btnChamadas = binding.btnChamadas

        btnConversas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentConteudo.id, ConversasFragment())
                .commit()
        }

        btnChamadas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentConteudo.id, ChamadasFragment())
//                .remove(conversasFragment)
                .commit()
        }
    }

}