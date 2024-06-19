import Models.Ingresso
import java.io.File
import java.util.Scanner

object GestaoIngressos {
    private val ingressosFile = File("Cache/ingressos.csv")

    // Método para adicionar um ingresso ao arquivo CSV
    fun adicionarIngresso(sessaoId: Int, clienteId: Int, assento: String, preco: Double) {
        val id = obterProximoId()
        val ingresso = Ingresso(id, sessaoId, clienteId, assento, preco)
        gravarIngresso(ingresso)
        println("Ingresso adicionado com sucesso.\n")
    }

    // Método para listar ingressos vendidos para um cliente específico
    fun listarIngressos(clienteId: Int) {
        println("\n=== Consulta de Ingressos Vendidos para o Cliente ===")
        val ingressos = carregarIngressos(clienteId)
        if (ingressos.isEmpty()) {
            println("Nenhum ingresso encontrado para o cliente.")
        } else {
            ingressos.forEach { ingresso ->
                val sessao = Administração.carregarSessaoPorId(ingresso.sessaoId)
                val cliente = GestaoClientes.carregarClientePorId(ingresso.clienteId)
                println("${ingresso.id}: Sessão ${sessao?.horario} - Assento: ${ingresso.assento} - Preço: ${ingresso.preco}€")
            }
        }
    }

    // Método privado para obter o próximo ID para o ingresso
    private fun obterProximoId(): Int {
        return if (ingressosFile.exists()) {
            ingressosFile.readLines().size + 1
        } else {
            1
        }
    }

    // Método privado para gravar um ingresso no arquivo CSV
    private fun gravarIngresso(ingresso: Ingresso) {
        val linha = "${ingresso.id},${ingresso.sessaoId},${ingresso.clienteId},${ingresso.assento},${ingresso.preco}\n"
        ingressosFile.appendText(linha)
    }

    // Método privado para carregar ingressos do arquivo CSV para um cliente específico
    private fun carregarIngressos(clienteId: Int): List<Ingresso> {
        val ingressos = mutableListOf<Ingresso>()
        if (ingressosFile.exists()) {
            Scanner(ingressosFile).use { scanner ->
                while (scanner.hasNextLine()) {
                    val line = scanner.nextLine()
                    val dados = line.split(",")
                    if (dados.size >= 5) {
                        val ingresso = Ingresso(
                            dados[0].toInt(),
                            dados[1].toInt(),
                            dados[2].toInt(),
                            dados[3],
                            dados[4].toDouble()
                        )
                        if (ingresso.clienteId == clienteId) {
                            ingressos.add(ingresso)
                        }
                    }
                }
            }
        }
        return ingressos
    }
}