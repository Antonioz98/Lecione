package com.example.lecione.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.R
import com.example.lecione.modelo.Aula
import com.example.lecione.ui.viewholder.AulaViewHolder

class AulasAdapter(private val context: Context) : RecyclerView.Adapter<AulaViewHolder>() {

    private val aulas = mutableListOf<Aula>()
    lateinit var clickListener: (Aula, Int) -> Unit

    fun atualizaAulas(aulas: List<Aula>) {
        this.aulas.clear()
        this.aulas.addAll(aulas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AulaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_aula, parent, false)
        return AulaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aulas.size
    }

    override fun onBindViewHolder(holder: AulaViewHolder, position: Int) {
        holder.bindView(aulas[position], clickListener)
    }
}