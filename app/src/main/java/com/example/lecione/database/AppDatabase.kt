package com.example.lecione.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lecione.database.dao.AlunoDao
import com.example.lecione.database.dao.AulaDao
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.Aula

@Database(
    version = 1,
    entities = [Aluno::class, Aula::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alunoDao(): AlunoDao
    abstract fun aulaDao(): AulaDao
}