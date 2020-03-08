package com.example.lecione.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.modelo.Aula
import kotlinx.android.synthetic.main.item_historico.view.*

class HistoricoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(aula: Aula, clickListener: (Aula, Int) -> Unit) {
        itemView.historico_nome.text = aula.aluno.nome
        itemView.historico_materia.text = aula.materia
        itemView.historico_horario.text = "${aula.data} - ${aula.horario}"
    }
}