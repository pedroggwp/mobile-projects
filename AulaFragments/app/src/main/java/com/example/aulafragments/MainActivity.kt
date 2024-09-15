package com.example.aulafragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.aulafragments.databinding.ActivityMainBinding
import com.example.aulafragments.fragments.ChamadasFragment
import com.example.aulafragments.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnMercado: Button
    private lateinit var btnChamadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnMercado = binding.btnMercado
        btnChamadas = binding.btnChamadas

        btnMercado.setOnClickListener {
            val conversasFragment = ConversasFragment()
            val bundle = bundleOf(
                "categoria" to "mercado",
                "usuario" to "Pedro"
            )

            conversasFragment.arguments = bundle

            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentConteudo.id, conversasFragment)
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