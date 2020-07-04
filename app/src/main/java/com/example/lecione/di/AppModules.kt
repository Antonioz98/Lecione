package com.example.lecione.di

import com.example.lecione.database.AppDatabase
import com.example.lecione.database.dao.AlunoDao
import com.example.lecione.database.dao.AulaDao
import com.example.lecione.repository.AlunoRepository
import com.example.lecione.repository.AulaRepository
import com.example.lecione.repository.HistoricoRepository
import com.example.lecione.ui.viewmodel.AlunoViewModel
import com.example.lecione.ui.viewmodel.AulaViewModel
import com.example.lecione.ui.viewmodel.HistoricoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getInstance(get()) }
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
    viewModel<AulaViewModel> { AulaViewModel(get()) }
    viewModel<HistoricoViewModel> { HistoricoViewModel(get()) }
//    viewModel<DetalhesProdutoViewModel> { (id: Long) -> DetalhesProdutoViewModel(id, get()) }
}