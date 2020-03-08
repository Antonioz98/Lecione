package com.example.lecione.modelo

import java.io.Serializable

class Aluno(var id: String, var nome: String, var telefone: String, var endereco: String): Serializable

fun simulaAlunos(): List<Aluno> {
    return listOf<Aluno>(
        Aluno("2", "Arthur Rodrigues Ferreira", "3515-19684", "Rua nao te interessa"),
        Aluno("2", "Marcelo Barros ", "3515-34632", "Rua Lucas Conceicao"),
        Aluno("2", "Maria Theereza", "3515-63423", "Rua Guilherme Martin"),
        Aluno("2", "Cristina Araujo", "3515-3443", "Rua Joao Paes de Barros"),
        Aluno("2", "Franciso Bento", "3515-5436", "Rua Albestro sd"),
        Aluno("2", "Janaina Pires", "3515-1234", "Rua nao te interessa"),
        Aluno("2", "Carlos Alberto Costa", "3515-2423", "Rua nao te interessa")
    )
}