package com.example.lecione.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity
class Aula() : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo
    var data: Calendar = Calendar.getInstance()

    @ColumnInfo
    var materia: String = String()

    @ColumnInfo
    var descricao: String = String()

    @ColumnInfo
    var endereco: String = String()

    @ForeignKey(entity = Aluno::class, parentColumns = ["uid"], childColumns = ["alunoId"], onUpdate = CASCADE, onDelete = CASCADE)
    @ColumnInfo
    var alunoId: Int = 0

    fun dataFormatada(): String {
        val utcFormat = SimpleDateFormat("dd/MM/yyyy' 'HH:mm")
        return utcFormat.format(data.time)
    }
}