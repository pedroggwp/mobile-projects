package com.example.mobile_pedrohenrique_2.dao

import com.example.mobile_pedrohenrique_2.model.Exam
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ExamsDAO {

    fun add(exam: Exam) {
        exams.add(exam)
    }

    fun updateEndDate(crachaNumber: Int, endDate: String) {
        val exam = exams.find { it.crachaNumber == crachaNumber }
        if (exam != null) {
            val updatedExam = exam.copy(endDate = endDate)
            val index = exams.indexOf(exam)
            exams[index] = updatedExam
        }
    }

    fun findAll(): List<Exam> {
        return exams.toList()
    }

    companion object {
        private val exams = mutableListOf(
            Exam(
                crachaNumber = 123,
                startDate = formatDateToIso("22/12/1223 00:00:00"),
                endDate = formatDateToIso("12/12/2223 00:00:00")
            ),
            Exam(
                crachaNumber = 223,
                startDate = formatDateToIso("22/01/2203 00:00:00")
            )
        )

        private fun formatDateToIso(dateString: String): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault())
            val instant = Instant.from(formatter.parse(dateString))
            return instant.toString()
        }
    }
}
