package com.example.lecione.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lecione.modelo.Aula
import com.example.lecione.repository.AulaRepository

class HistoricoViewModel(private val repository: AulaRepository) : ViewModel() {

    fun todos() = repository.buscaHistorico()

    fun remove(aula: Aula) = repository.remove(aula)

    fun buscaAluno(alunoId: Int) = repository.buscaAluno(alunoId)
}
