package com.example.lecione.ui.viewholder

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.modelo.Aula
import com.example.lecione.modelo.AulaAcao
import kotlinx.android.synthetic.main.item_aula.view.*

class AulaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(aula: Aula, clickListener: (Aula, AulaAcao) -> Unit, preencheNomeAluno: (Int, TextView) -> Unit) {
        preencheNomeAluno(aula.alunoId, itemView.aula_nome)
        itemView.aula_materia.text = aula.materia
        itemView.aula_endereco.text = aula.endereco
        itemView.aula_horario.text = aula.dataFormatada()
        bindDetalhe(aula)
        itemView.aula_desmarcar.setOnClickListener {
            clickListener(aula, AulaAcao.EXCLUIR)
        }
        itemView.aula_editar.setOnClickListener {
            clickListener(aula, AulaAcao.EDITAR)
        }
    }

    private fun bindDetalhe(aula: Aula) {
        itemView.aula_detalhe.apply {
            if (aula.descricao.isEmpty()) {
                visibility = GONE
            } else {
                visibility = VISIBLE
                text = aula.descricao
            }
        }
    }
}