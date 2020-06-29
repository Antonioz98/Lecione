package com.example.lecione.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Aula(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
//    @ColumnInfo var aluno: Aluno,
    @ColumnInfo var horario: String,
    @ColumnInfo var data: String,
    @ColumnInfo var materia: String,
    @ColumnInfo var descricao: String,
    @ColumnInfo var endereco: String
)

fun simulaAulas(): List<Aula> {
    return listOf<Aula>(
        Aula(0, "08:00", "25/07/2020", "Espanhol", "Continuar Materia", "Escola de idiomas"),
        Aula(0, "10:00", "25/07/2020", "Inglês", "Verbo To be", "Escola de idiomas"),
        Aula(0, "13:00", "25/07/2020", "Espanhol", "Continuar Materia", "Escola de idiomas"),
        Aula(0, "15:00", "25/07/2020", "Inglês", "Continuar Materia", "Escola de idiomas"),
        Aula(0, "17:00", "25/07/2020", "Inglês", "Intensivão pré-prova", "Rua Joaquim Freire, 996"),
        Aula(0, "18:00", "25/07/2020", "Inglês", "Intensivão pré-prova", "Rua Joaquim Freire, 996"),
        Aula(0, "08:00", "26/07/2020", "Espanhol", "Continuar Materia", "Escola de idiomas")
    )
}