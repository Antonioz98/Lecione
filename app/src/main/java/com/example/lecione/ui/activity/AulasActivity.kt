package com.example.lecione.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lecione.R
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.AulaAcao
import com.example.lecione.ui.adapter.AulasAdapter
import com.example.lecione.ui.viewmodel.AulaViewModel
import kotlinx.android.synthetic.main.activity_aulas.*
import org.koin.android.viewmodel.ext.android.viewModel

class AulasActivity : AppCompatActivity() {

    private val adapter by lazy { AulasAdapter(this) }
    private val viewModel: AulaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aulas)
        title = "Próximas Aulas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        configuiraLista()
        atualizaLista()
    }

    private fun atualizaLista() {
        viewModel.todos().observe(this, Observer {
            adapter.atualizaAulas(it)
        })
    }

    private fun configuiraLista() {
        aulas_recyclerView.adapter = adapter
        adapter.clickListener = { aula, acao ->
            when (acao) {
                AulaAcao.EDITAR -> {
                    val intent = Intent(this, MarcarAulaActivity::class.java)
                    intent.putExtra(AULA_PARA_EDITAR, aula)
                    startActivity(intent)
                }
                AulaAcao.EXCLUIR -> {
                    viewModel.remove(aula).observe(this, Observer {
                        atualizaLista()
                    })
                }
            }
        }
        adapter.preencheNomeAluno = { id, textViwe ->
            viewModel.buscaAluno(id).observe(this, Observer {
                textViwe.text = it.nome
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
