package com.example.lecione.ui.activity

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lecione.R
import com.example.lecione.modelo.Aula
import com.example.lecione.ui.adapter.AlunosAdapter
import com.example.lecione.ui.dialog.PegarDataDialog
import com.example.lecione.ui.viewmodel.AlunoViewModel
import com.example.lecione.ui.viewmodel.AulaViewModel
import kotlinx.android.synthetic.main.activity_marcar_aula.*
import kotlinx.android.synthetic.main.procurar_aluno.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

const val AULA_PARA_EDITAR = "CONSTANTE PAR EDITA AULA"

class MarcarAulaActivity : AppCompatActivity() {

    private val adapterAlunos by lazy { AlunosAdapter(this) }
    private lateinit var aula: Aula
    private val viewModel: AulaViewModel by viewModel()
    private val alunoViewModel: AlunoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcar_aula)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        aula = intent.getSerializableExtra(AULA_PARA_EDITAR) as Aula? ?: Aula()

        title = if (intent.hasExtra(AULA_PARA_EDITAR)) {
            preencheCampos()
            "Editar Aula"
        } else {
            "Marcar Aula"
        }

        configuraData()
        configuraHora()
        configuraSalvar()
        configuraNomeAluno()
    }

    private fun configuraNomeAluno() {
        marcar_aula_nome.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.procurar_aluno, null, false)
            view.procurar_aluno_lista.adapter = adapterAlunos
            view.procurar_aluno_procurar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let { adapterAlunos.filter(it) }
                    return false
                }

            })
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Selecione o Aluno")
                .setView(view)
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
                .show()

            alunoViewModel.todos().observe(this, androidx.lifecycle.Observer {
                adapterAlunos.atualizaAlunos(it)
            })

            adapterAlunos.clickListener = {
                aula.alunoId = it.uid
                if (marcar_aula_endereco.text.isNullOrEmpty()) {
                    marcar_aula_endereco.setText(it.endereco)
                }
                marcar_aula_nome.setText(it.nome)
                alertDialog.dismiss()
            }
        }
    }

    private fun configuraSalvar() {
        marcar_aula_salvar.setOnClickListener {
            if (formularioValido()) {
                preencheAula()
                if (intent.hasExtra(AULA_PARA_EDITAR)) {
                    editaAula()
                } else {
                    salvarAula()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preencheAula() {
        aula.materia = marcar_aula_assunto.text.toString()
        aula.descricao = marcar_aula_detalhes.text.toString()
        aula.endereco = marcar_aula_endereco.text.toString()
    }

    private fun salvarAula() {
        viewModel.salva(aula).observe(this, androidx.lifecycle.Observer {
            it?.let {
                finish()
            }
        })
    }

    private fun editaAula() {
        viewModel.edita(aula).observe(this, androidx.lifecycle.Observer {
            it?.let {
                finish()
            }
        })
    }

    private fun configuraHora() {
        marcar_aula_hora.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                aula.data.set(Calendar.HOUR_OF_DAY, hour)
                aula.data.set(Calendar.MINUTE, minute)
                marcar_aula_hora.setText("${SimpleDateFormat("HH:mm").format(aula.data.time)} h")
            }
            TimePickerDialog(this, timeSetListener, aula.data.get(Calendar.HOUR_OF_DAY), aula.data.get(Calendar.MINUTE), true).show()
        }
    }

    private fun configuraData() {
        marcar_aula_data.setOnClickListener {
            PegarDataDialog { _, ano, mes, dia ->
                aula.data.set(ano, mes, dia)
                marcar_aula_data.setText(SimpleDateFormat("dd/MM/yyyy").format(aula.data.time))
            }.show(supportFragmentManager, "DATE_PICKER_FRAGMENT")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun formularioValido(): Boolean {
        return marcar_aula_nome.text?.isNotEmpty() == true &&
                marcar_aula_endereco.text?.isNotEmpty() == true &&
                marcar_aula_assunto.text?.isNotEmpty() == true &&
                marcar_aula_data.text?.isNotEmpty() == true &&
                marcar_aula_hora.text?.isNotEmpty() == true
    }

    private fun preencheCampos() {
        viewModel.buscaAluno(aula.alunoId).observe(this, androidx.lifecycle.Observer {
            marcar_aula_nome.setText(it.nome)
        })
        marcar_aula_assunto.setText(aula.materia)
        marcar_aula_detalhes.setText(aula.descricao)
        marcar_aula_endereco.setText(aula.endereco)
        marcar_aula_data.setText(SimpleDateFormat("dd/MM/yyyy").format(aula.data.time))
        marcar_aula_hora.setText("${SimpleDateFormat("HH:mm").format(aula.data.time)} h")
    }
}
