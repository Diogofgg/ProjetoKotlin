import Models.Menu
import Models.Promocao
import java.io.File

object GestaoPromocoes {
    private val promocoesFile = File("Cache/promocoes.csv")
    private val promocoes = mutableListOf<Promocao>()
    private var proximoId = 1

    init {
        carregarPromocoes()
    }

    fun adicionarPromocao(nome: String, menus: List<Menu>) {
        val promocao = Promocao(nome, menus.toMutableList())
        promocoes.add(promocao)
        salvarPromocoes()
        println("Promoção '$nome' adicionada com sucesso.\n")
    }

    fun removerPromocao(nome: String) {
        val promocao = promocoes.find { it.nome.equals(nome, ignoreCase = true) }
        if (promocao != null) {
            promocoes.remove(promocao)
            salvarPromocoes()
            println("Promoção '$nome' removida com sucesso.\n")
        } else {
            println("Promoção '$nome' não encontrada.\n")
        }
    }

    fun alterarPromocao(nome: String, novosMenus: List<Menu>) {
        val promocao = promocoes.find { it.nome.equals(nome, ignoreCase = true) }
        if (promocao != null) {
            promocao.menus.clear()
            promocao.menus.addAll(novosMenus)
            salvarPromocoes()
            println("Promoção '$nome' alterada com sucesso.\n")
        } else {
            println("Promoção '$nome' não encontrada.\n")
        }
    }

    fun visualizarPromocoes() {
        if (promocoes.isEmpty()) {
            println("Nenhuma promoção registada.\n")
        } else {
            println("\n=== Promoções registadas ===")
            promocoes.forEach { promocao ->
                println("Promoção: ${promocao.nome}")
                println("Menus:")
                promocao.menus.forEach { menu ->
                    println(" - ${menu.tipo.capitalize()} ${menu.tamanho}")
                }
                println()
            }
        }
    }

    private fun carregarPromocoes() {
        if (promocoesFile.exists()) {
            promocoesFile.forEachLine { line ->
                val dados = line.split(",")
                val nome = dados[0]
                val menus = mutableListOf<Menu>()
                for (i in 1 until dados.size step 2) {
                    val tipo = dados[i]
                    val tamanho = dados[i + 1]
                    menus.add(Menu(tipo, tamanho))
                }
                promocoes.add(Promocao(nome, menus))
            }
        }
    }

    private fun salvarPromocoes() {
        promocoesFile.printWriter().use { out ->
            promocoes.forEach { promocao ->
                out.print(promocao.nome)
                promocao.menus.forEach { menu ->
                    out.print(",${menu.tipo},${menu.tamanho}")
                }
                out.println()
            }
        }
    }
}
