package com.example.lecione.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.modelo.Aluno
import kotlinx.android.synthetic.main.item_aluno.view.*

class AlunoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(aluno: Aluno, clickListener: (Aluno) -> Unit) {
        itemView.aluno_nome.text = aluno.nome
        itemView.aluno_telefone.text = "Telefone: ${aluno.telefone}"
        itemView.aluno_endereco.text = "Endereço:  ${aluno.endereco}"
        itemView.setOnClickListener { clickListener(aluno) }
    }

}