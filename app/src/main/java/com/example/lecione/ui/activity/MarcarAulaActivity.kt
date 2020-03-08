package com.example.lecione.ui.activity

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lecione.R
import com.example.lecione.modelo.Aluno
import com.example.lecione.modelo.simulaAlunos
import com.example.lecione.ui.adapter.AlunosAdapter
import com.example.lecione.ui.dialog.PegarDataDialog
import kotlinx.android.synthetic.main.activity_marcar_aula.*
import kotlinx.android.synthetic.main.procurar_aluno.view.*
import java.text.SimpleDateFormat
import java.util.*

class MarcarAulaActivity : AppCompatActivity() {

    private val dataAula = Calendar.getInstance()
    private val horarioAula = Calendar.getInstance()
    private val adapterAlunos by lazy { AlunosAdapter(this) }
    private lateinit var aluno : Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcar_aula)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        title = "Marcar Aula"

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

            adapterAlunos.atualizaAlunos(simulaAlunos())
            adapterAlunos.clickListener = {
                this.aluno = it
                marcar_aula_nome.setText(it.nome)
                alertDialog.dismiss()
            }
        }
    }

    private fun configuraSalvar() {
        marcar_aula_salvar.setOnClickListener {
            if (formularioValido()) {
                salvarAula()
            } else {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarAula() {
        finish()
    }

    private fun configuraHora() {
        marcar_aula_hora.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                horarioAula.set(Calendar.HOUR_OF_DAY, hour)
                horarioAula.set(Calendar.MINUTE, minute)
                marcar_aula_hora.setText("${SimpleDateFormat("HH:mm").format(horarioAula.time)} h")
            }
            TimePickerDialog(this, timeSetListener, horarioAula.get(Calendar.HOUR_OF_DAY), horarioAula.get(Calendar.MINUTE), true).show()
        }
    }

    private fun configuraData() {
        marcar_aula_data.setOnClickListener {
            PegarDataDialog { _, ano, mes, dia ->
                dataAula.set(ano, mes, dia)
                marcar_aula_data.setText(SimpleDateFormat("dd/MM/yyyy").format(dataAula.time))
            }.show(supportFragmentManager, "DATE_PICKER_FRAGMENT")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun formularioValido(): Boolean {
        return marcar_aula_nome.text?.isNotEmpty() == true &&
                marcar_aula_nome.text?.isNotEmpty() == true &&
                marcar_aula_nome.text?.isNotEmpty() == true &&
                marcar_aula_nome.text?.isNotEmpty() == true &&
                marcar_aula_nome.text?.isNotEmpty() == true
    }
}
