package com.example.lecione.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.lecione.R
import com.example.lecione.modelo.ARGUMENTO_ALUNO
import com.example.lecione.modelo.Aluno
import com.example.lecione.ui.viewmodel.AlunoViewModel
import kotlinx.android.synthetic.main.activity_formulario_aluno.*
import org.koin.android.viewmodel.ext.android.viewModel

class FormularioAlunoActivity : AppCompatActivity() {

    private var aluno: Aluno? = null
    private val viewModel: AlunoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        title = "Formulário Aluno"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        aluno = intent.getSerializableExtra(ARGUMENTO_ALUNO) as Aluno?
        preencheFormulario()
    }

    private fun preencheFormulario() {
        formulario_aluno_nome.setText(aluno?.nome)
        formulario_aluno_telefone.setText(aluno?.telefone)
        formulario_aluno_endereco.setText(aluno?.endereco)
    }

    private fun deletar() {
        aluno?.let {
            viewModel.remove(it).observe(this, Observer {
                it?.let {
                    finish()
                }
            })
        }
    }

    private fun salvar() {
        preencheAluno()
        if (formularioValido()) {
            if (aluno?.uid != 0) {
                viewModel.edita(aluno!!).observe(this, Observer {
                    it?.let {
                        finish()
                    }
                })
            } else {
                viewModel.salva(aluno!!).observe(this, Observer {
                    it?.let {
                        finish()
                    }
                })
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun preencheAluno() {
        if (aluno == null) {
            aluno = Aluno(
                nome = formulario_aluno_nome.text.toString(),
                endereco = formulario_aluno_endereco.text.toString(),
                telefone = formulario_aluno_telefone.text.toString()
            )
        } else {
            aluno?.nome = formulario_aluno_nome.text.toString()
            aluno?.telefone = formulario_aluno_telefone.text.toString()
            aluno?.endereco = formulario_aluno_endereco.text.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario, menu)
        if (aluno?.uid == null) {
            menu?.findItem(R.id.menu_formulario_deletar)?.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_formulario_deletar -> deletar()

            R.id.menu_formulario_salvar -> salvar()

            else -> finish()
        }
        return true
    }

    private fun formularioValido(): Boolean {
        return formulario_aluno_endereco.text?.isNotEmpty() == true &&
                formulario_aluno_telefone.text?.isNotEmpty() == true &&
                formulario_aluno_nome.text?.isNotEmpty() == true
    }
}
