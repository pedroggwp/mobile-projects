package com.jamiltondamasceno.aulacomponentesinterfaceviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jamiltondamasceno.aulacomponentesinterfaceviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var btnClique: Button
    //private lateinit var binding: ActivityMainBinding
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root )

        with(binding){

            btnClique?.setOnClickListener {  }

        }


        /*btnClique = findViewById(R.id.btn_clique)
        btnClique.setOnClickListener {
            Toast.makeText(this, "Olá", Toast.LENGTH_SHORT).show()
        }*/

    }
}