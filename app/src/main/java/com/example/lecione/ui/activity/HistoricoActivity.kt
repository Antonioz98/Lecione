package com.example.lecione.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lecione.R
import com.example.lecione.modelo.simulaAulas
import com.example.lecione.ui.adapter.HistoricoAdapter
import kotlinx.android.synthetic.main.activity_historico.*

class HistoricoActivity : AppCompatActivity() {

    private val adapter by lazy { HistoricoAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
        title = "Histórico"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        configuiraLista()
    }

    private fun configuiraLista() {
        historico_recyclerView.adapter = adapter
        adapter.clickListener = { aula, acao ->

        }
        adapter.atualizaAulas(simulaAulas())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
