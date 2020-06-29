package com.example.lecione.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.modelo.Aula
import kotlinx.android.synthetic.main.item_aula.view.*

class AulaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(aula: Aula, clickListener: (Aula, Int) -> Unit) {
//        itemView.aula_nome.text = aula.aluno.nome
        itemView.aula_materia.text = aula.materia
        itemView.aula_endereco.text = aula.endereco
        itemView.aula_horario.text = "${aula.data} - ${aula.horario}"
        itemView.aula_detalhe.text = aula.descricao
    }
}