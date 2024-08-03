package com.example.mobile_pedrohenrique_2.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_pedrohenrique_2.R
import com.example.mobile_pedrohenrique_2.databinding.ActivityListExamsBinding
import com.example.mobile_pedrohenrique_2.model.Exam
import com.example.mobile_pedrohenrique_2.dao.ExamsDAO
import com.example.mobile_pedrohenrique_2.ui.recyclerview.adapter.ExamAdapter
import com.google.android.material.textfield.TextInputEditText
import java.time.Instant

class ListExamsActivity : AppCompatActivity() {

    private val dao = ExamsDAO()
    private val adapter = ExamAdapter(this, dao.findAll())

    private val binding by lazy {
        ActivityListExamsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(dao.findAll())
    }

    private fun configRecyclerView() {
        val recyclerView = binding.activityListExamsRecyclerview
        recyclerView.adapter = adapter
    }

    private fun configFab() {
        val fab = binding.activityListExamsFloatingActionButton

        fab.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exam, null)

            val numCrachaInput = dialogView.findViewById<TextInputEditText>(R.id.dialog_exam_numcracha)

            AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("SALVAR") {_, _ ->
                    val numCracha = numCrachaInput.text.toString().toInt()

                    dao.add(Exam(
                        crachaNumber = numCracha,
                        startDate = Instant.now().toString()
                    ))

                    adapter.updateList(dao.findAll())

                }
                .setNegativeButton("CANCELAR") {_, _ ->

                }
                .show()
        }
    }

}