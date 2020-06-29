package com.example.lecione.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.lecione.R
import com.example.lecione.modelo.ARGUMENTO_ALUNO
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.simulaAlunos
import com.example.lecione.ui.viewmodel.AlunoViewModel
import kotlinx.android.synthetic.main.activity_formulario_aluno.*
import org.koin.android.viewmodel.ext.android.viewModel

class FormularioAlunoActivity : AppCompatActivity() {

    private var alunoRecebido: Aluno? = null
    private val viewModel: AlunoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        title = "Formulário Aluno"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        alunoRecebido = intent.getSerializableExtra(ARGUMENTO_ALUNO) as Aluno?
        preencheFormulario()
    }

    private fun preencheFormulario() {
        formulario_aluno_nome.setText(alunoRecebido?.nome)
        formulario_aluno_telefone.setText(alunoRecebido?.telefone)
        formulario_aluno_endereco.setText(alunoRecebido?.endereco)
    }

    private fun deletar() {
        finish()
    }

    private fun salvar() {
        if (formularioValido()) {
            viewModel.salva(Aluno(nome = "Arthur Rodrigues Ferreira", telefone = "3515-19684", endereco = "Rua Padre José, 115"))
            finish()
        } else {
            Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario, menu)
        if (alunoRecebido == null) {
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
