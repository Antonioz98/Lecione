package com.example.lecione.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lecione.modelo.Aula
import com.example.lecione.repository.AulaRepository

class AulaViewModel(private val repository: AulaRepository) : ViewModel() {

    fun salva(aula: Aula) = repository.salva(aula)

    fun edita(aula: Aula) = repository.edita(aula)

    fun todos() = repository.buscaTodos()

    fun remove(aula: Aula) = repository.remove(aula)

    fun buscaAluno(alunoId: Int) = repository.buscaAluno(alunoId)
}