package com.example.lecione.modelo

class Aula(var id: String, var aluno: Aluno, var horario: String, var data: String, var materia: String, var descricao: String, var endereco: String)

fun simulaAulas(): List<Aula> {
    return listOf<Aula>(
        Aula("2", simulaAlunos()[0], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[1], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[2], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[3], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[4], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[5], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins"),
        Aula("2", simulaAlunos()[6], "13:00", "25/02/2020", "Matematica", "Continuar Materia", "Escola Martins")
    )
}