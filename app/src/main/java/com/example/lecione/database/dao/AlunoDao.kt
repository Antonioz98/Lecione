package com.example.lecione.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lecione.modelo.Aluno

@Dao
interface AlunoDao {

    @Insert
    fun salva(aluno: Aluno) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun edita(aluno: Aluno): Int

    @Delete
    fun remove(aluno: Aluno): Int

    @Query("SELECT * FROM Aluno")
    fun todos(): LiveData<List<Aluno>>

    @Query("DELETE FROM Aula WHERE alunoId = :alunoId")
    fun alunoExcluido(alunoId: Int)
}