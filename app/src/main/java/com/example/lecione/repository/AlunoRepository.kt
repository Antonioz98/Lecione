package com.example.lecione.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lecione.database.dao.AlunoDao
import com.example.lecione.modelo.Aluno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlunoRepository(private val dao: AlunoDao) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    
    fun buscaTodos(): LiveData<List<Aluno>> = dao.todos()

    fun salva(aluno: Aluno): LiveData<Resource<Long>> {
        return MutableLiveData<Resource<Long>>().also { liveDate ->
            scope.launch {
                val idPagamento = dao.salva(aluno)
                liveDate.postValue(Resource(idPagamento))
            }
        }
    }
}
