package com.example.lecione.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.Aula

@Dao
interface AulaDao {

    @Insert
    fun salva(aula: Aula): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun edita(aula: Aula): Int

    @Delete
    fun remove(aula: Aula): Int

    @Query("SELECT * FROM Aula WHERE data > :DataAtual ORDER BY data ASC")
    fun todos(DataAtual: Long): LiveData<List<Aula>>

    @Query("SELECT * FROM Aula WHERE data < :DataAtual ORDER BY data DESC")
    fun todosHistorico(DataAtual: Long): LiveData<List<Aula>>

    @Query("SELECT * FROM Aluno WHERE uid = :alunoId LIMIT 1")
    fun getAluno(alunoId: Int): LiveData<Aluno>
}