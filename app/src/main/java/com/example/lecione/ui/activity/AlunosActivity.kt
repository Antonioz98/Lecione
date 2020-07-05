package com.example.lecione.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lecione.R
import com.example.lecione.modelo.ARGUMENTO_ALUNO
import com.example.lecione.modelo.Aluno
import com.example.lecione.ui.adapter.AlunosAdapter
import com.example.lecione.ui.viewmodel.AlunoViewModel
import kotlinx.android.synthetic.main.activity_alunos.*
import org.koin.android.viewmodel.ext.android.viewModel

class AlunosActivity : AppCompatActivity() {

    private val adapter by lazy { AlunosAdapter(this) }
    private val viewModel: AlunoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos)
        title = getString(R.string.title_activity_alunos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        configuraListaDeAlunos()
        configuraFAB()
    }

    override fun onResume() {
        super.onResume()
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        viewModel.todos().observe(this, Observer {
            adapter.atualizaAlunos(it)
        })
    }

    private fun configuraFAB() {
        alunos_fab.setOnClickListener { _ ->
            abreFormulario(null)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun configuraListaDeAlunos() {
        alunos_recyclerView.adapter = adapter
        adapter.clickListener = {
            abreFormulario(it)
        }
    }

    private fun abreFormulario(aluno: Aluno?) {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        intent.putExtra(ARGUMENTO_ALUNO, aluno)
        startActivity(intent)
    }
}
