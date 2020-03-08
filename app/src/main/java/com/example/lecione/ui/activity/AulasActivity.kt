package com.example.lecione.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.lecione.R
import com.example.lecione.modelo.simulaAulas
import com.example.lecione.ui.adapter.AulasAdapter
import kotlinx.android.synthetic.main.activity_aulas.*

class AulasActivity : AppCompatActivity() {

    private val adapter by lazy { AulasAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aulas)
        title = "Próximas Aulas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        configuiraLista()
    }

    private fun configuiraLista() {
        aulas_recyclerView.adapter = adapter
        adapter.clickListener = { aula, acao ->

        }
        adapter.atualizaAulas(simulaAulas())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
