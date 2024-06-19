import Models.Cliente
import java.io.File

    object GestaoClientes {
        private val clientesFile = File("Cache/clientes.csv")
        private var clientes = mutableListOf<Cliente>()
        private var proximoId = 1

        init {
            if (clientesFile.exists()) {
                clientes = carregarClientes()
                proximoId = clientes.maxByOrNull { it.id }?.id ?: 0 + 1
            }
        }

        fun adicionarCliente(nome: String, email: String) {
            val cliente = Cliente(proximoId++, nome, email)
            clientes.add(cliente)
            salvarClientes()
        }

        fun listarClientes() {
            println("\n=== Consulta de Clientes ===")
            clientes.forEach { println("${it.id}: ${it.nome} - Email: ${it.email}") }
        }

        private fun salvarClientes() {
            clientesFile.printWriter().use { out ->
                clientes.forEach { cliente ->
                    out.println("${cliente.id},${cliente.nome},${cliente.email}")
                }
            }
        }

        private fun carregarClientes(): MutableList<Cliente> {
            val clientes = mutableListOf<Cliente>()
            clientesFile.forEachLine { line ->
                val dados = line.split(",")
                if (dados.size >= 3) { // Verifica se há pelo menos três campos (id, nome, email)
                    val cliente = Cliente(dados[0].toInt(), dados[1], dados[2])
                    clientes.add(cliente)
                } else {
                    println("Erro ao carregar cliente: formato inválido.")
                }
            }
            return clientes
        }

        fun carregarClientePorId(id: Int): Cliente? {
            return clientes.find { it.id == id }
        }
    }