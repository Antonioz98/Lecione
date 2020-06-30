package com.example.lecione.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lecione.modelo.Aluno
import com.example.lecione.repository.AlunoRepository

class AlunoViewModel(private val alunoRepository: AlunoRepository) : ViewModel() {

    fun salva(aluno: Aluno) = alunoRepository.salva(aluno)

    fun edita(aluno: Aluno) = alunoRepository.edita(aluno)

    fun todos() = alunoRepository.buscaTodos()

    fun remove(aluno: Aluno) = alunoRepository.remove(aluno)
}