package com.example.lecione.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lecione.modelo.Aluno
import com.example.lecione.repository.AlunoRepository

class AlunoViewModel(private val alunoRepository: AlunoRepository) : ViewModel() {

    fun salva(aluno: Aluno) = alunoRepository.salva(aluno)

    fun todos() = alunoRepository.buscaTodos()
}