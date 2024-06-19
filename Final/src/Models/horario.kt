package Models

data class Horario(
    val id: Int,
    val horaInicio: String,
    val horaFim: String,
    val funcionario: String  // Exemplo simples com nome do funcionário atribuído ao horário
)
