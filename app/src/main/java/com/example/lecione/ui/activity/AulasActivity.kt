package com.example.lecione.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.example.lecione.R
import com.example.lecione.modelo.AulaAcao
import com.example.lecione.ui.adapter.AulasAdapter
import com.example.lecione.ui.viewmodel.AulaViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_aulas.*
import org.koin.android.viewmodel.ext.android.viewModel

class AulasActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val adapter by lazy { AulasAdapter(this) }
    private val viewModel: AulaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aulas)
        title = "Próximas Aulas"
        configuraDrawer()
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

    private fun configuraDrawer() {
        setSupportActionBar(toolbar)
        ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close).let {
            drawer_layout.addDrawerListener(it)
            it.syncState()
        }
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val activity = when (item.itemId) {
            R.id.nav_marcar_aula -> MarcarAulaActivity::class.java
            R.id.nav_alunos -> AlunosActivity::class.java
            R.id.nav_historico -> HistoricoActivity::class.java
            else -> MarcarAulaActivity::class.java
        }
        item.isCheckable = false
        drawer_layout.closeDrawer(GravityCompat.START)

        val intent = Intent(this, activity)
        startActivity(intent)
        return true
    }
}
