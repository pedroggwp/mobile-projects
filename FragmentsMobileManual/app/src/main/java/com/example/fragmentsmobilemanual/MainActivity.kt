package com.example.fragmentsmobilemanual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private var chamadaFragment: ChamadaFragment = ChamadaFragment()
    private var conversaFragment: ConversaFragment = ConversaFragment()
    private var contatoFragment: ContatoFragment = ContatoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_chamada).setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameconteudo, chamadaFragment).commit()
        }

        findViewById<Button>(R.id.bt_conversa).setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameconteudo, conversaFragment).commit()
        }

        findViewById<Button>(R.id.bt_contato).setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameconteudo, contatoFragment).commit()
        }
    }
}