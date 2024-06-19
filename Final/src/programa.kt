import Models.Menu
import java.util.Scanner
fun iniciarExemplos() {
   // GestaoClientes.adicionarCliente("João Silva", "joao@example.com")
    GestaoClientes.listarClientes()

    // Exemplo de adicionar ingresso
    val sessaoId = 1 // ID de uma sessão existente
    val clienteId = 1 // ID do cliente adicionado acima
   // GestaoIngressos.adicionarIngresso(sessaoId, clienteId, "A10", 30.0)*/

    // Exemplo de listar ingressos vendidos para um cliente
    GestaoIngressos.listarIngressos(clienteId)
}
fun menuClientes() {
    println("\n==Menu Clientes ==")
    println("1 - Listar clientes")
    println("0 - Voltar")
    print("Opção escolhida: ")

    val scanner = Scanner(System.`in`)

    when (scanner.nextInt()) {
        1 -> {
            println("\n== Consulta de Clientes ==")
            GestaoClientes.listarClientes()
        }
        0 -> return
        else -> println("Opção inválida!")
    }
}

fun menuFilmes(scanner: Scanner) {
    while (true) {
        println("\n==Menu Filmes ==")
        println("Escolha uma opção:")
        println("1 - Listar filmes")
        println("2 - Registar filme")
        println("3 - Editar filme")
        println("4 - Detalhar filme")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n== Consulta de Filmes ==")
                Administração.listarFilmes()
            }
            2 -> Administração.RegistoFilme(scanner)
            3 -> {
                println("\n== Edição de Filme ==")
                println("Por favor, selecione um filme para editar:")
                Administração.listarFilmes()
                print("ID do filme a ser editado: ")
                val filmeId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.editarFilme(filmeId, scanner)
            }
            4 -> {
                println("\n== Detalhes de Filme ==")
                println("Por favor, selecione um filme para ver detalhes:")
                Administração.listarFilmes()
                print("ID do filme a ser detalhado: ")
                val filmeId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.detalharFilme(filmeId)
            }
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}
fun menuSalas(scanner: Scanner) {
    while (true) {
        println("\n==Menu Salas ==")
        println("Escolha uma opção:")
        println("1 - Consultar salas")
        println("2 - Registar sala")
        println("3 - Editar sala")
        println("4 - Detalhar sala")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n== Consulta de Salas ==")
                Administração.listarSalas()
            }
            2 -> Administração.RegistoSala(scanner)
            3 -> {
                println("\n== Edição de Sala ==")
                println("Por favor, selecione uma sala para editar:")
                Administração.listarSalas()
                print("ID da sala a ser editada: ")
                val salaId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.editarSala(salaId, scanner)
            }
            4 -> {
                println("\n== Detalhes de Sala ==")
                println("Por favor, selecione uma sala para ver detalhes:")
                Administração.listarSalas()
                print("ID da sala a ser detalhada: ")
                val salaId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.detalharSala(salaId)
            }
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}

fun menuSessoes(scanner: Scanner) {
    while (true) {
        println("\n==Menu Sessões ==")
        println("Escolha uma opção:")
        println("1 - Consulta sessões")
        println("2 - Cadastrar sessão")
        println("3 - Editar sessão")
        println("4 - Detalhar sessão")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n== Consulta de Sessões ==")
                Administração.listarSessoes()
            }
            2 -> Administração.RegistoSessao(scanner)
            3 -> {
                println("\n== Edição de Sessão ==")
                println("Por favor, selecione uma sessão para editar:")
                Administração.listarSessoes()
                print("ID da sessão a ser editada: ")
                val sessaoId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.editarSessao(sessaoId, scanner)
            }
            4 -> {
                println("\n== Detalhes de Sessão ==")
                println("Por favor, selecione uma sessão para ver detalhes:")
                Administração.listarSessoes()
                print("ID da sessão a ser detalhada: ")
                val sessaoId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                Administração.detalharSessao(sessaoId)
            }
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}

fun menuFuncionarios(scanner: Scanner) {
    while (true) {
        println("\n==Menu Funcionários ==")
        println("Escolha uma opção:")
        println("1 - Consultar funcionários")
        println("2 - Registar funcionário")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n== Consulta de Funcionários ==")
                GestaoFuncionarios.listarFuncionarios()
            }
            2 -> GestaoFuncionarios.cadastrarFuncionario(scanner)
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}

fun menuTarefas(scanner: Scanner) {
    while (true) {
        println("\n==Menu Tarefas ==")
        println("Escolha uma opção:")
        println("1 - Atribuir tarefa")
        println("2 - Remover tarefa")
        println("3 - Visualizar tarefas")

        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n=== Atribuir Tarefa ===")
                println("Consulta de funcionários:")
                GestaoFuncionarios.listarFuncionarios()
                print("ID do funcionário: ")
                val idFuncionario = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                print("Descrição da tarefa: ")
                val descricao = scanner.nextLine()
                GestaoTarefas.atribuirTarefa(idFuncionario, descricao)
            }
            2 -> {
                println("\n=== Remover Tarefa ===")
                print("Descrição da tarefa a ser removida: ")
                val descricao = scanner.nextLine()
                GestaoTarefas.removerTarefa(descricao)
            }
            3 -> {
                println("\n=== Visualizar Tarefas ===")
                GestaoTarefas.visualizarTarefas()
            }

            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}
fun menuHorarios(scanner: Scanner) {
    while (true) {
        println("\n==Menu Horários ==")
        println("Escolha uma opção:")
        println("1 - Atribuir horário")
        println("2 - Remover horário")
        println("3 - Visualizar horários")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n=== Atribuir Horário ===")
                println("Consulta de funcionários:")
                GestaoFuncionarios.listarFuncionarios()
                print("ID do funcionário: ")
                val idFuncionario = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                print("Horário de trabalho (hh:mm): ")
                val horario = scanner.nextLine()
                GestaoHorarios.atribuirHorario(idFuncionario, horario)
            }
            2 -> {
                println("\n=== Remover Horário ===")
                print("ID do funcionário para remover horário: ")
                val idFuncionario = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                GestaoHorarios.removerHorario(idFuncionario)
            }
            3 -> {
                println("\n=== Visualizar Horários ===")
                GestaoHorarios.visualizarHorarios()
            }
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}

fun menuIngressos(scanner: Scanner) {
    while (true) {
        println("\n==Menu Ingressos ==")
        println("Escolha uma opção:")
        println("1 - Consultar ingressos vendidos para um cliente")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> {
                println("\n=== Consulta de Ingressos por Cliente ===")
                print("Digite o ID do cliente: ")
                val clienteId = scanner.nextInt()
                scanner.nextLine() // Consumir a quebra de linha após nextInt()
                GestaoIngressos.listarIngressos(clienteId)
            }
            0 -> return
            else -> println("Opção inválida!")
        }


        println()

    }





}
fun menuPromocoes(scanner: Scanner) {
    while (true) {
        println("\n==Menu Promoções ==")
        println("Escolha uma opção:")
        println("1 - Adicionar promoção")
        println("2 - Remover promoção")
        println("3 - Alterar promoção")
        println("4 - Visualizar promoções")
        println("0 - Voltar")
        print("Opção escolhida: ")

        when (scanner.nextInt()) {
            1 -> menuAdicionarPromocao(scanner)
            2 -> menuRemoverPromocao(scanner)
            3 -> menuAlterarPromocao(scanner)
            4 -> GestaoPromocoes.visualizarPromocoes()
            0 -> return
            else -> println("Opção inválida!")
        }

        println()
    }
}

fun menuAdicionarPromocao(scanner: Scanner) {
    println("\n==Adicionar Promoção ===")
    print("Nome da promoção: ")
    scanner.nextLine() // Consumir a quebra de linha após nextInt()
    val nome = scanner.nextLine()

    val menus = mutableListOf<Menu>()
    while (true) {
        println("Adicionar Menu:")
        println("1 - Pipocas")
        println("2 - Sumos")
        println("0 - Concluir")
        print("Opção escolhida: ")
        when (scanner.nextInt()) {
            1 -> {
                println("Tamanho da Pipoca:")
                println("1 - Pequena")
                println("2 - Média")
                println("3 - Grande")
                print("Opção escolhida: ")
                val tamanho = when (scanner.nextInt()) {
                    1 -> "Pequena"
                    2 -> "Média"
                    3 -> "Grande"
                    else -> {
                        println("Opção inválida!")
                        continue
                    }
                }
                menus.add(Menu("Pipocas", tamanho))
            }
            2 -> {
                println("Tamanho do Sumo:")
                println("1 - Pequeno")
                println("2 - Médio")
                println("3 - Grande")
                print("Opção escolhida: ")
                val tamanho = when (scanner.nextInt()) {
                    1 -> "Pequeno"
                    2 -> "Médio"
                    3 -> "Grande"
                    else -> {
                        println("Opção inválida!")
                        continue
                    }
                }
                menus.add(Menu("Sumos", tamanho))
            }
            0 -> break
            else -> println("Opção inválida!")
        }
    }

    GestaoPromocoes.adicionarPromocao(nome, menus)
}

fun menuRemoverPromocao(scanner: Scanner) {
    println("\n===Remover Promoção ===")
    print("Nome da promoção: ")
    scanner.nextLine() // Consumir a quebra de linha após nextInt()
    val nome = scanner.nextLine()

    GestaoPromocoes.removerPromocao(nome)
}

fun menuAlterarPromocao(scanner: Scanner) {
    println("\n=== Alterar Promoção ===")
    print("Nome da promoção: ")
    scanner.nextLine() // Consumir a quebra de linha após nextInt()
    val nome = scanner.nextLine()

    val menus = mutableListOf<Menu>()
    while (true) {
        println("Alterar Menu:")
        println("1 - Pipocas")
        println("2 - Sumos")
        println("0 - Concluir")
        print("Opção escolhida: ")
        when (scanner.nextInt()) {
            1 -> {
                println("Novo tamanho da Pipoca:")
                println("1 - Pequena")
                println("2 - Média")
                println("3 - Grande")
                print("Opção escolhida: ")
                val tamanho = when (scanner.nextInt()) {
                    1 -> "Pequena"
                    2 -> "Média"
                    3 -> "Grande"
                    else -> {
                        println("Opção inválida!")
                        continue
                    }
                }
                menus.add(Menu("Pipocas", tamanho))
            }
            2 -> {
                println("Novo tamanho do Sumo:")
                println("1 - Pequeno")
                println("2 - Médio")
                println("3 - Grande")
                print("Opção escolhida: ")
                val tamanho = when (scanner.nextInt()) {
                    1 -> "Pequeno"
                    2 -> "Médio"
                    3 -> "Grande"
                    else -> {
                        println("Opção inválida!")
                        continue
                    }
                }
                menus.add(Menu("Sumos", tamanho))
            }
            0 -> break
            else -> println("Opção inválida!")
        }
    }

    GestaoPromocoes.alterarPromocao(nome, menus)
}
