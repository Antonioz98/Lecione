package com.example.lecione.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lecione.R
import com.example.lecione.modelo.AulaAcao
import com.example.lecione.ui.adapter.HistoricoAdapter
import com.example.lecione.ui.viewmodel.HistoricoViewModel
import kotlinx.android.synthetic.main.activity_historico.*
import org.koin.android.viewmodel.ext.android.viewModel

class HistoricoActivity : AppCompatActivity() {

    private val adapter by lazy { HistoricoAdapter(this) }
    private val viewModel: HistoricoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
        title = "Histórico"
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
        historico_recyclerView.adapter = adapter
        adapter.clickListener = { aula, acao ->
            if (acao == AulaAcao.EXCLUIR) {
                viewModel.remove(aula).observe(this, Observer {
                    atualizaLista()
                })
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
