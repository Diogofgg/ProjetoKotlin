import Models.Funcionario
import java.io.File
import java.util.Scanner
object GestaoFuncionarios {
    private val funcionariosFile = File("Cache/funcionarios.csv")

    fun cadastrarFuncionario(scanner: Scanner) {
        println("\n=== Registo de Funcionário ===")
        print("Nome do funcionário: ")
        scanner.nextLine() // Limpar o buffer
        val nome = scanner.nextLine()
        print("Cargo: ")
        val cargo = scanner.nextLine()

        val id = if (funcionariosFile.exists()) {
            funcionariosFile.readLines().size + 1
        } else {
            1
        }

        funcionariosFile.appendText("$id,$nome,$cargo\n")
        println("Funcionário registada com sucesso.\n")
    }

    fun listarFuncionarios() {
        println("\n=== Consulta de Funcionários ===")
        val funcionarios = carregarFuncionarios()
        funcionarios.forEach { println("${it.id}: ${it.nome} - Cargo: ${it.cargo}") }
    }

    fun carregarFuncionarioPorId(id: Int): Funcionario? {
        val funcionarios = carregarFuncionarios()
        return funcionarios.find { it.id == id }
    }

    private fun carregarFuncionarios(): List<Funcionario> {
        val funcionarios = mutableListOf<Funcionario>()
        if (funcionariosFile.exists()) {
            funcionariosFile.forEachLine {
                val dados = it.split(",")
                val funcionario = Funcionario(dados[0].toInt(), dados[1], dados[2])
                funcionarios.add(funcionario)
            }
        }
        return funcionarios
    }
}

object GestaoTarefas {
    private val tarefasFile = File("Cache/tarefas.csv")

    fun atribuirTarefa(idFuncionario: Int, descricao: String) {
        // Implementar lógica para atribuir uma tarefa a um funcionário
        // Exemplo simples:
        tarefasFile.appendText("$idFuncionario,$descricao\n")
        println("Tarefa atribuída com sucesso.\n")
    }

    fun removerTarefa(descricao: String) {
        // Implementar lógica para remover uma tarefa pelo seu identificador
        // Exemplo simples:
        val tempFile = File.createTempFile("temp", null)
        tarefasFile.forEachLine {
            if (!it.contains(descricao)) {
                tempFile.appendText("$it\n")
            }
        }
        tempFile.renameTo(tarefasFile)
        println("Tarefa removida com sucesso.\n")
    }

    fun visualizarTarefas() {
        println("\n=== Consulta de Tarefas ===")
        tarefasFile.forEachLine {
            val dados = it.split(",")
            println("Funcionário ${dados[0]}: ${dados[1]}")
        }
    }

    fun alterarTarefa(descricaoAntiga: String, descricaoNova: String) {
        // Caminho do arquivo de tarefas
        val tarefasFile = File("Cache/tarefas.txt")

        // Arquivo temporário para armazenar o resultado da modificação
        val tempFile = File.createTempFile("temp", null)

        // Itera por cada linha do arquivo original
        tarefasFile.forEachLine { linha ->
            val dados = linha.split(",")

            // Verifica se a linha tem pelo menos dois elementos e se a descrição corresponde à descrição antiga
            if (dados.size > 1 && dados[1] == descricaoAntiga) {
                // Adiciona a nova descrição ao arquivo temporário
                tempFile.appendText("${dados[0]},$descricaoNova\n")
            } else {
                // Adiciona a linha original ao arquivo temporário
                tempFile.appendText("$linha\n")
            }
        }

        // Substitui o arquivo original pelo arquivo temporário
        if (tarefasFile.delete()) {
            if (!tempFile.renameTo(tarefasFile)) {
                println("Erro ao renomear o arquivo temporário.")
            } else {
                println("Tarefa alterada com sucesso.")
            }
        } else {
            println("Erro ao deletar o arquivo original.")
        }
    }
}

object GestaoHorarios {
    private val horariosFile = File("Cache/horarios.csv")

    fun atribuirHorario(idFuncionario: Int, horario: String) {
        // Implementar lógica para atribuir um horário a um funcionário
        // Exemplo simples:
        horariosFile.appendText("$idFuncionario,$horario\n")
        println("Horário atribuído com sucesso.\n")
    }

    fun removerHorario(idFuncionario: Int) {
        // Implementar lógica para remover o horário de um funcionário pelo seu identificador
        // Exemplo simples:
        val tempFile = File.createTempFile("temp", null)
        horariosFile.forEachLine {
            val dados = it.split(",")
            if (dados[0].toInt() != idFuncionario) {
                tempFile.appendText("$it\n")
            }
        }
        tempFile.renameTo(horariosFile)
        println("Horário removido com sucesso.\n")
    }

    fun visualizarHorarios() {
        println("\n=== Consulta de Horários ===")
        horariosFile.forEachLine {
            val dados = it.split(",")
            println("Funcionário ${dados[0]}: ${dados[1]}")
        }
    }

    fun alterarHorario(idFuncionario: Int, novoHorario: String) {
        // Implementar lógica para alterar o horário de um funcionário
        // Exemplo simples:
        val tempFile = File.createTempFile("temp", null)
        horariosFile.forEachLine {
            val dados = it.split(",")
            if (dados[0].toInt() == idFuncionario) {
                tempFile.appendText("$idFuncionario,$novoHorario\n")
            } else {
                tempFile.appendText("$it\n")
            }
        }
        tempFile.renameTo(horariosFile)
        println("Horário alterado com sucesso.\n")
    }
}