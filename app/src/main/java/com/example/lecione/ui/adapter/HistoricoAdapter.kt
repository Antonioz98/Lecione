package com.example.lecione.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lecione.R
import com.example.lecione.modelo.Aula
import com.example.lecione.ui.viewholder.HistoricoViewHolder

class HistoricoAdapter(private val context: Context) : RecyclerView.Adapter<HistoricoViewHolder>() {

    private val aulas = mutableListOf<Aula>()
    lateinit var clickListener: (Aula, Int) -> Unit

    fun atualizaAulas(aulas: List<Aula>) {
        this.aulas.clear()
        this.aulas.addAll(aulas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_historico, parent, false)
        return HistoricoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aulas.size
    }

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        holder.bindView(aulas[position], clickListener)
    }
}