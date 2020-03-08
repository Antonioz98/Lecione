package com.example.lecione.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.lecione.R
import com.example.lecione.modelo.ARGUMENTO_ALUNO
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.simulaAlunos
import com.example.lecione.ui.adapter.AlunosAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_alunos.*

class AlunosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val adapter by lazy { AlunosAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos)
        configuraDrawer()
        configuraListaDeAlunos()
        configuraFAB()
    }

    private fun configuraFAB() {
        alunos_fab.setOnClickListener { _ ->
            abreFormulario(null)
        }
    }

    private fun configuraListaDeAlunos() {
        alunos_recyclerView.adapter = adapter
        adapter.clickListener = {
            abreFormulario(it)
        }
        adapter.atualizaAlunos(simulaAlunos())
    }

    private fun abreFormulario(aluno: Aluno?) {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        intent.putExtra(ARGUMENTO_ALUNO, aluno)
        startActivity(intent)
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
            R.id.nav_proximas_aulas -> AulasActivity::class.java
            R.id.nav_historico -> HistoricoActivity::class.java
            else -> MarcarAulaActivity::class.java
        }
        item.isChecked = false
        drawer_layout.closeDrawer(GravityCompat.START)

        val intent = Intent(this, activity)
        startActivity(intent)
        return true
    }
}
