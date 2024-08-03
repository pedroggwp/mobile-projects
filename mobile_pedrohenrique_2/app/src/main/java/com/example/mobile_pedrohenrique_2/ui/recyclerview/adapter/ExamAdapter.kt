package com.example.mobile_pedrohenrique_2.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_pedrohenrique_2.dao.ExamsDAO
import com.example.mobile_pedrohenrique_2.databinding.ItemExamBinding
import com.example.mobile_pedrohenrique_2.extension.formatIsoDateToDateTimeString
import com.example.mobile_pedrohenrique_2.model.Exam
import java.time.Instant

class ExamAdapter(
    private val context: Context,
    exams: List<Exam>
) : RecyclerView.Adapter<ExamAdapter.ExamViewHolder>() {

    private val exams = exams.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val binding = ItemExamBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ExamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val exam: Exam = exams[position]
        holder.bind(exam)
    }

    override fun getItemCount(): Int = exams.size

    fun updateList(exams: List<Exam>) {
        this.exams.clear()
        this.exams.addAll(exams)
        notifyDataSetChanged()
    }

    inner class ExamViewHolder(private val binding: ItemExamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exam: Exam) {
            val crachaNumber = binding.itemExamCollaboratorNumber
            crachaNumber.text = "Colaborador: " + exam.crachaNumber.toString()

            val startDate = binding.itemExamCollaboratorStartTime
            startDate.text = "Início: " + exam.startDate.formatIsoDateToDateTimeString()

            val endDate = binding.itemExamCollaboratorEndTime

            val button = binding.itemExamButtonEndExam

            if (exam.endDate == null) {
                button.visibility = View.VISIBLE
                endDate.visibility = View.GONE

                button.setOnClickListener {
                    val newEndDate = Instant.now().toString()
                    ExamsDAO().updateEndDate(exam.crachaNumber, newEndDate)

                    exams[adapterPosition] = exam.copy(endDate = newEndDate)
                    notifyItemChanged(adapterPosition)
                }

            } else {
                button.visibility = View.GONE
                endDate.text = "Término: " + exam.endDate.formatIsoDateToDateTimeString()
            }
        }
    }
}



