package com.example.aulafragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aulafragments.databinding.FragmentConversasBinding

class ConversasFragment : Fragment() {

    private lateinit var binding: FragmentConversasBinding
    private lateinit var btnExecutar: Button
    private lateinit var textNome: TextView
    private lateinit var textCategoria: TextView
    private lateinit var editTextNome: EditText

    private var categoria: String? = null
    private var usuario: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoria = arguments?.getString("categoria")
        usuario = arguments?.getString("usuario")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversasBinding.inflate(layoutInflater, container, false)

        btnExecutar = binding.btnExecutar
        textNome = binding.textNome
        editTextNome = binding.editTextNome
        textCategoria = binding.textCategoria

        textCategoria.text = categoria
        textNome.text = usuario

        btnExecutar.setOnClickListener {
            textNome.text = editTextNome.text.toString()
        }

        return binding.root
    }
}