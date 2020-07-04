package com.example.lecione.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.modelo.Aula
import com.example.lecione.modelo.AulaAcao
import kotlinx.android.synthetic.main.item_historico.view.*

class HistoricoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(aula: Aula, clickListener: (Aula, AulaAcao) -> Unit, preencheNomeAluno: (Int, TextView) -> Unit) {
        preencheNomeAluno(aula.alunoId, itemView.historico_nome)
        itemView.historico_materia.text = aula.materia
        itemView.historico_horario.text = aula.dataFormatada()
        itemView.historico_excluir.setOnClickListener {
            clickListener(aula, AulaAcao.EXCLUIR)
        }
    }
}