package com.example.lecione.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Aluno(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo var nome: String,
    @ColumnInfo var telefone: String,
    @ColumnInfo var endereco: String
) : Serializable

fun simulaAlunos(): List<Aluno> {
    return listOf<Aluno>(
        Aluno(0, "Arthur Rodrigues Ferreira", "3515-19684", "Rua Padre José, 115"),
        Aluno(0, "Marcelo Barros ", "3515-34632", "Rua Conceicao, 365"),
        Aluno(0, "Maria Theereza", "3515-63423", "Rua Guilherme Martin, 10"),
        Aluno(0, "Cristina Araujo", "3515-3443", "Rua João Paes de Barros, 2"),
        Aluno(0, "Franciso Bento", "3515-5436", "Rua Joaquim Freire, 996"),
        Aluno(0, "Janaina Pires", "3515-1234", "Rua Osmar Filho, 63"),
        Aluno(0, "Carlos Alberto Costa", "3515-2423", "Rua Alberto Coelho, 227")
    )
}