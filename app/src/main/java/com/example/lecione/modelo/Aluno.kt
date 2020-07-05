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