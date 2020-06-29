package com.example.lecione.di

import androidx.room.Room
import com.example.lecione.database.AppDatabase
import com.example.lecione.database.dao.AlunoDao
import com.example.lecione.database.dao.AulaDao
import com.example.lecione.repository.AlunoRepository
import com.example.lecione.repository.AulaRepository
import com.example.lecione.repository.HistoricoRepository
import com.example.lecione.ui.viewmodel.AlunoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "lecione.db"

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
    single<AulaDao> { get<AppDatabase>().aulaDao() }
    single<AlunoDao> { get<AppDatabase>().alunoDao() }
    single<AulaRepository> { AulaRepository(get()) }
    single<AlunoRepository> { AlunoRepository(get()) }
    single<HistoricoRepository> { HistoricoRepository() }
}

val viewModelModule = module {
    viewModel<AlunoViewModel> { AlunoViewModel(get()) }
//    viewModel<DetalhesProdutoViewModel> { (id: Long) -> DetalhesProdutoViewModel(id, get()) }
}