import java.util.Scanner

fun main() {
    iniciarExemplos() // Inicializa exemplos de dados

    val scanner = Scanner(System.`in`)

    println("Bem-vindo ao sistema de administração de cinema!\n")

    while (true) {
        println("Escolha uma opção:")
        println("1 - Filmes")
        println("2 - Salas")
        println("3 - Sessões")
        println("4 - Funcionários")
        println("5 - Tarefas")
        println("6 - Horários")
        println("7 - Ingressos")
        println("8 - Clientes")
        println("9 - Promoções")
        println("0 - Sair")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> menuFilmes(scanner)
            2 -> menuSalas(scanner)
            3 -> menuSessoes(scanner)
            4 -> menuFuncionarios(scanner)
            5 -> menuTarefas(scanner)
            6 -> menuHorarios(scanner)
            7 -> menuIngressos(scanner)
            8 -> menuClientes()
            9 -> menuPromocoes(scanner) // Adiciona a opção para acessar o menu de promoções
            0 -> {
                println("Saindo...")
                return
            }
            else -> println("Opção inválida!")
        }

        println()
    }
}

