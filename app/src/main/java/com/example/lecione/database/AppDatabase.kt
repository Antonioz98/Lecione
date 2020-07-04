package com.example.lecione.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lecione.database.converter.ConversorCalendar
import com.example.lecione.database.dao.AlunoDao
import com.example.lecione.database.dao.AulaDao
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.Aula

private const val NOME_BANCO_DE_DADOS = "lecione.db"

@Database(version = 1, entities = [Aluno::class, Aula::class], exportSchema = false)
@TypeConverters(ConversorCalendar::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            ).build()
        }
    }

    abstract fun alunoDao(): AlunoDao

    abstract fun aulaDao(): AulaDao
}