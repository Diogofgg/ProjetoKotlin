import Models.Filme
import Models.Sala
import Models.Sessao
import java.io.File
import java.util.Scanner
object Administração {

    fun carregarSessoes(): List<Sessao> {
        val sessoesFile = File("Cache/sessoes.csv")
        val sessoes = mutableListOf<Sessao>()
        if (sessoesFile.exists()) {
            sessoesFile.forEachLine {
                val dados = it.split(",")
                val sessao = Sessao(dados[0].toInt(), dados[1].toInt(), dados[2].toInt(), dados[3], dados[4].toDouble())
                sessoes.add(sessao)
            }
        }
        return sessoes
    }

    fun carregarFilmes(): List<Filme> {
        val filmesFile = File("Cache/filmes.csv")
        val filmes = mutableListOf<Filme>()

        if (filmesFile.exists()) {
            filmesFile.forEachLine { line ->
                val dados = line.split(",")
                if (dados.size >= 4) { // Verifica se há pelo menos quatro campos (id, título, diretor, duração)
                    try {
                        val filme = Filme(
                            id = dados[0].toInt(),
                            titulo = dados[1],
                            diretor = dados[2],
                            duracao = dados[3].toInt()
                        )
                        filmes.add(filme)
                    } catch (e: NumberFormatException) {
                        println("Erro ao converter dados do filme: $line")
                    }
                } else {
                    println("Erro ao carregar filme: formato inválido.")
                }
            }
        }
        return filmes
    }

    fun carregarSalas(): List<Sala> {
        val salasFile = File("Cache/salas.csv")
        val salas = mutableListOf<Sala>()
        if (salasFile.exists()) {
            salasFile.forEachLine {
                val dados = it.split(",")
                val sala = Sala(dados[0].toInt(), dados[1].toInt(), dados[2].toInt())
                salas.add(sala)
            }
        }
        return salas
    }

    fun carregarFilmePorId(filmeId: Int): Filme? {
        val filmes = carregarFilmes()
        return filmes.find { it.id == filmeId }
    }

    fun carregarSalaPorId(salaId: Int): Sala? {
        val salas = carregarSalas()
        return salas.find { it.id == salaId }
    }

    fun carregarSessaoPorId(sessaoId: Int): Sessao? {
        val sessoes = carregarSessoes()
        return sessoes.find { it.id == sessaoId }
    }

    fun RegistoFilme(scanner: Scanner) {
        println("\n=== Registo do Filme ===")

        // Consumir a quebra de linha pendente
        scanner.nextLine()

        print("Título do filme: ")
        val titulo = scanner.nextLine()
        print("Diretor: ")
        val diretor = scanner.nextLine()
        print("Duração em minutos: ")
        val duracao = scanner.nextInt()
        scanner.nextLine() // Consumir a quebra de linha deixada pelo nextInt()

        val filmesFile = File("Cache/filmes.csv")
        val id = if (filmesFile.exists()) {
            filmesFile.readLines().size + 1
        } else {
            1
        }

        filmesFile.appendText("$id,$titulo,$diretor,$duracao\n")
        println("Filme Registada com sucesso.\n")
    }

    fun RegistoSala(scanner: Scanner) {
        println("\n=== Registo da Sala de Cinema ===")
        print("Número da sala: ")
        val numero = scanner.nextInt()
        print("Capacidade da sala: ")
        val capacidade = scanner.nextInt()
        scanner.nextLine() // Consumir a quebra de linha deixada pelo nextInt()

        val salasFile = File("Cache/salas.csv")
        val id = if (salasFile.exists()) {
            salasFile.readLines().size + 1
        } else {
            1
        }

        salasFile.appendText("$id,$numero,$capacidade\n")
        println("Sala Registada com sucesso.\n")
    }

    fun RegistoSessao(scanner: Scanner) {
        println("\n=== Registo da Sessão ===")
        println("Escolha um filme:")
        val filmes = carregarFilmes()
        filmes.forEach { println("${it.id}: ${it.titulo}") }
        print("ID do filme escolhido: ")
        val filmeId = scanner.nextInt()

        println("Escolha uma sala:")
        val salas = carregarSalas()
        salas.forEach { println("${it.id}: Sala ${it.numero} - Capacidade ${it.capacidade}") }
        print("ID da sala escolhida: ")
        val salaId = scanner.nextInt()

        scanner.nextLine() // Consumir a quebra de linha deixada pelo nextInt()
        print("Horário da sessão (hh:mm): ")
        val horario = scanner.nextLine()
        print("Preço do ingresso: ")
        val preco = scanner.nextDouble()

        val sessoesFile = File("Cache/sessoes.csv")
        val id = if (sessoesFile.exists()) {
            sessoesFile.readLines().size + 1
        } else {
            1
        }

        sessoesFile.appendText("$id,$filmeId,$salaId,$horario,$preco\n")
        println("Sessão cadastrada com sucesso.\n")
    }

    fun listarFilmes() {
        println("\n=== Listagem de Filmes ===")
        val filmes = carregarFilmes()
        filmes.forEach { println("${it.id}: ${it.titulo} (${it.duracao} min)") }
    }

    fun listarSalas() {
        println("\n=== Listagem de Salas ===")
        val salas = carregarSalas()
        salas.forEach { println("${it.id}: Sala ${it.numero} - Capacidade ${it.capacidade}") }
    }

    fun listarSessoes() {
        println("\n=== Listagem de Sessões ===")
        val sessoes = carregarSessoes()
        sessoes.forEach {
            val filme = carregarFilmePorId(it.filmeId)
            val sala = carregarSalaPorId(it.salaId)
            println("${it.id}: ${filme?.titulo} - Sala ${sala?.numero} - Horário: ${it.horario} - Preço: ${it.preco}€")
        }
    }

    fun detalharFilme(filmeId: Int) {
        val filme = carregarFilmePorId(filmeId)
        if (filme != null) {
            println("\nDetalhes do Filme:")
            println("Título: ${filme.titulo}")
            println("Duração: ${filme.duracao} minutos")
        } else {
            println("Filme não encontrado.")
        }
    }

    fun detalharSala(salaId: Int) {
        val sala = carregarSalaPorId(salaId)
        if (sala != null) {
            println("\nDetalhes da Sala de Cinema:")
            println("Número: ${sala.numero}")
            println("Capacidade: ${sala.capacidade} lugares")
        } else {
            println("Sala não encontrada.")
        }
    }

    fun detalharSessao(sessaoId: Int) {
        val sessao = carregarSessaoPorId(sessaoId)
        if (sessao != null) {
            val filme = carregarFilmePorId(sessao.filmeId)
            val sala = carregarSalaPorId(sessao.salaId)
            println("\nDetalhes da Sessão:")
            println("Filme: ${filme?.titulo}")
            println("Sala: ${sala?.numero}")
            println("Horário: ${sessao.horario}")
            println("Preço do ingresso: ${sessao.preco}€")
        } else {
            println("Sessão não encontrada.")
        }
    }
    fun editarFilme(filmeId: Int, scanner: Scanner) {
        val filme = carregarFilmePorId(filmeId)
        if (filme != null) {
            println("\n=== Edição de Filme ===")
            println("Filme atual: ${filme.titulo}")

            print("Novo título (ou ENTER para manter o mesmo): ")
            val novoTitulo = scanner.nextLine()
            val titulo = if (novoTitulo.isNotBlank()) novoTitulo else filme.titulo

            print("Novo diretor (ou ENTER para manter o mesmo): ")
            val novoDiretor = scanner.nextLine()
            val diretor = if (novoDiretor.isNotBlank()) novoDiretor else filme.diretor

            print("Nova duração em minutos (ou 0 para manter a mesma): ")
            val novaDuracaoStr = scanner.nextLine()
            val duracao = if (novaDuracaoStr.isNotBlank()) novaDuracaoStr.toInt() else filme.duracao

            val filmesFile = File("Cache/filmes.csv")
            val linhas = filmesFile.readLines()
            val novasLinhas = linhas.map {
                val dados = it.split(",")
                if (dados[0].toInt() == filmeId) {
                    "$filmeId,$titulo,$diretor,$duracao"
                } else {
                    it
                }
            }
            filmesFile.writeText(novasLinhas.joinToString("\n"))

            println("Filme editado com sucesso.\n")
        } else {
            println("Filme não encontrado.")
        }
    }

    fun editarSala(salaId: Int, scanner: Scanner) {
        val sala = carregarSalaPorId(salaId)
        if (sala != null) {
            println("\n=== Edição de Sala de Cinema ===")
            println("Sala atual: Sala ${sala.numero}")

            print("Novo número da sala (ou ENTER para manter o mesmo): ")
            val novoNumeroStr = scanner.nextLine()
            val numero = if (novoNumeroStr.isNotBlank()) novoNumeroStr.toInt() else sala.numero

            print("Nova capacidade da sala (ou ENTER para manter a mesma): ")
            val novaCapacidadeStr = scanner.nextLine()
            val capacidade = if (novaCapacidadeStr.isNotBlank()) novaCapacidadeStr.toInt() else sala.capacidade

            val salasFile = File("Cache/salas.csv")
            val linhas = salasFile.readLines()
            val novasLinhas = linhas.map {
                val dados = it.split(",")
                if (dados[0].toInt() == salaId) {
                    "$salaId,$numero,$capacidade"
                } else {
                    it
                }
            }
            salasFile.writeText(novasLinhas.joinToString("\n"))

            println("Sala editada com sucesso.\n")
        } else {
            println("Sala não encontrada.")
        }
    }

    fun editarSessao(sessaoId: Int, scanner: Scanner) {
        val sessao = carregarSessaoPorId(sessaoId)
        if (sessao != null) {
            println("\n=== Edição de Sessão ===")
            println("Sessão atual:")
            detalharSessao(sessaoId)

            print("Novo ID do filme (ou ENTER para manter o mesmo): ")
            val novoFilmeIdStr = scanner.nextLine()
            val filmeId = if (novoFilmeIdStr.isNotBlank()) novoFilmeIdStr.toInt() else sessao.filmeId

            print("Novo ID da sala (ou ENTER para manter o mesmo): ")
            val novoSalaIdStr = scanner.nextLine()
            val salaId = if (novoSalaIdStr.isNotBlank()) novoSalaIdStr.toInt() else sessao.salaId

            print("Novo horário da sessão (hh:mm) (ou ENTER para manter o mesmo): ")
            val novoHorario = scanner.nextLine()
            val horario = if (novoHorario.isNotBlank()) novoHorario else sessao.horario

            print("Novo preço do ingresso (ou 0.0 para manter o mesmo): ")
            val novoPrecoStr = scanner.nextLine()
            val preco = if (novoPrecoStr.isNotBlank()) novoPrecoStr.toDouble() else sessao.preco

            val sessoesFile = File("Cache/sessoes.csv")
            val linhas = sessoesFile.readLines()
            val novasLinhas = linhas.map {
                val dados = it.split(",")
                if (dados[0].toInt() == sessaoId) {
                    "$sessaoId,$filmeId,$salaId,$horario,$preco"
                } else {
                    it
                }
            }
            sessoesFile.writeText(novasLinhas.joinToString("\n"))

            println("Sessão editada com sucesso.\n")
        } else {
            println("Sessão não encontrada.")
        }
    }
}
// Outras funções (listarFilmes, listarSalas, etc.) permanecem sem alterações significativas
