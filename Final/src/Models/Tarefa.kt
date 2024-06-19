package Models

data class Tarefa(
    val id: Int,
    val descricao: String,
    val responsavel: String,
    val status: StatusTarefa
)

enum class StatusTarefa {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDA
}
