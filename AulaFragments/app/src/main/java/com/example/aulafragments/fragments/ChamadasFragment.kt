package com.example.aulafragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aulafragments.databinding.FragmentChamadasBinding

class ChamadasFragment : Fragment() {

    private lateinit var binding: FragmentChamadasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChamadasBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}