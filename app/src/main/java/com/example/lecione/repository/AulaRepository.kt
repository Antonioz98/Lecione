package com.example.lecione.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lecione.database.dao.AulaDao
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.Aula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class AulaRepository(private val dao: AulaDao) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun buscaTodos(): LiveData<List<Aula>> = dao.todos(Calendar.getInstance().timeInMillis)

    fun buscaAluno(alunoId: Int): LiveData<Aluno> = dao.getAluno(alunoId)

    fun salva(aula: Aula): LiveData<Resource<Long>> {
        return MutableLiveData<Resource<Long>>().also { liveDate ->
            scope.launch {
                val idPagamento = dao.salva(aula)
                liveDate.postValue(Resource(idPagamento))
            }
        }
    }

    fun edita(aula: Aula): LiveData<Resource<Int>> {
        return MutableLiveData<Resource<Int>>().also { liveDate ->
            scope.launch {
                val editado = dao.edita(aula)
                liveDate.postValue(Resource(editado))
            }
        }
    }

    fun remove(aula: Aula): LiveData<Resource<Int>> {
        return MutableLiveData<Resource<Int>>().also { liveDate ->
            scope.launch {
                val deletado = dao.remove(aula)
                liveDate.postValue(Resource(deletado))
            }
        }
    }

    fun buscaHistorico(): LiveData<List<Aula>> = dao.todosHistorico(Calendar.getInstance().timeInMillis)
}
