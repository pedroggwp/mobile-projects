package com.example.aulaactivityfragment

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetalhesActivity : AppCompatActivity() {

    private lateinit var buttonFechar: Button
    private lateinit var textFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        buttonFechar = findViewById(R.id.button_fechar)
        textFilme = findViewById(R.id.text_filme)

        val bundle = intent.extras // todos os parametros

        if (bundle != null) {
//            val filme = bundle.getString("filme")
//            val classificacao = bundle.getInt("classificacao")
//            val avaliacoes = bundle.getDouble("avaliacoes")
//
//            val resultado = "filme:  $filme - class. $classificacao aval. $avaliacoes"


            val filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("filme", Filme::class.java)
                // bundle.getSerializable("filme", Filme::class.java)
            } else {
                bundle.getParcelable("filme")
                //bundle.getSerializable("filme") as Filme
            }

            textFilme.text = "${filme?.nome} - ${filme?.distribuidor}"
        }

        buttonFechar.setOnClickListener {
            finish()
        }
    }
}