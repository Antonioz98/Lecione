package com.example.lecione.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.R
import com.example.lecione.modelo.Aluno
import com.example.lecione.ui.viewholder.AlunoViewHolder
import java.util.*

class AlunosAdapter(private val context: Context) : RecyclerView.Adapter<AlunoViewHolder>() {

    private val alunos = mutableListOf<Aluno>()
    private var arraylist: List<Aluno> = listOf()
    lateinit var clickListener: (Aluno) -> Unit

    fun atualizaAlunos(alunos: List<Aluno>) {
        this.alunos.clear()
        this.alunos.addAll(alunos)
        this.arraylist = alunos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        holder.bindView(alunos[position], clickListener)
    }

    fun filter(filtrar: String) {
        val charText = filtrar.toLowerCase(Locale.getDefault())
        alunos.clear()
        if (charText.isEmpty()) {
            alunos.addAll(arraylist)
        } else {
            for (wp in arraylist) {
                when {
                    wp.nome.toLowerCase(Locale.getDefault()).contains(charText) -> alunos.add(wp)
                    wp.endereco.toLowerCase(Locale.getDefault()).contains(charText) -> alunos.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}