package com.example.aulafragments

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        Log.i("ciclo_vida", "Activity onCreate")


//        val fragmentManager = supportFragmentManager.beginTransaction()
//
//        // Diversas alterações em fragments
//        fragmentManager.add(binding.fragmentConteudo.id, ConversasFragment())
//
//        fragmentManager.commit()

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

    override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo_vida", "Activity onDestroy")
    }
}