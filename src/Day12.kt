fun main() {
    data class Node(
        val letter: Char,
        var visited: Boolean = false,
        var shortest: Int = Int.MAX_VALUE,
    )

    fun canMove(current: Char, next: Char): Boolean {
        if (current == 'S' && next == 'a') return true
        if (current == 'z' && next == 'E') return true
        if (next != 'E' && next - current <= 1) return true
        return false
    }

    fun part1(input: List<String>): Int {
        val graph = input.map {
            it.toCharArray().map { Node(it) }.toMutableList()
        }.toMutableList()
        var start: Pair<Int, Int> = -10 to -10
        var end: Pair<Int, Int> = -10 to -10
        graph.forEachIndexed { row, nodes ->
            nodes.forEachIndexed() { column, node ->
                if (node.letter == 'S') {
                    start = row to column
                }
                if (node.letter == 'E') {
                    end = row to column
                }
            }
        }

        val queue = mutableListOf<Pair<Int, Int>>()
        graph[start.first][start.second].shortest = 0
        queue.add(start)
        while (queue.isNotEmpty()) {

            val (row, column) = queue.removeFirst()

            val current = graph[row][column]
            if (!current.visited) {
                current.visited = true
                if (row - 1 in graph.indices) {
                    val potentialNext = graph[row - 1][column]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row - 1) to column)
                        }
                    }
                }
                if (row + 1 in graph.indices) {
                    val potentialNext = graph[row + 1][column]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row + 1) to column)
                        }
                    }
                }
                if (column + 1 in graph[row].indices) {
                    val potentialNext = graph[row][column + 1]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row) to column + 1)
                        }
                    }
                }
                if (column - 1 in graph[row].indices) {
                    val potentialNext = graph[row][column - 1]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row) to column - 1)
                        }
                    }
                }
            }
        }
        return graph[end.first][end.second].shortest.also { println(it) }
    }

    fun part2(input: List<String>): Int {
        val graph = input.map {
            it.toCharArray().map { Node(it) }.toMutableList()
        }.toMutableList()
        var starts = mutableListOf<Pair<Int, Int>>()
        var end: Pair<Int, Int> = -10 to -10
        graph.forEachIndexed { row, nodes ->
            nodes.forEachIndexed() { column, node ->
                if (node.letter == 'a') {
                    starts.add(row to column)
                }
                if (node.letter == 'E') {
                    end = row to column
                }
            }
        }

        val queue = mutableListOf<Pair<Int, Int>>()
        for (start in starts) {
            graph[start.first][start.second].shortest = 0
            queue.add(start)
        }
        while (queue.isNotEmpty()) {

            val (row, column) = queue.removeFirst()

            val current = graph[row][column]
            if (!current.visited) {
                current.visited = true
                if (row - 1 in graph.indices) {
                    val potentialNext = graph[row - 1][column]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row - 1) to column)
                        }
                    }
                }
                if (row + 1 in graph.indices) {
                    val potentialNext = graph[row + 1][column]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row + 1) to column)
                        }
                    }
                }
                if (column + 1 in graph[row].indices) {
                    val potentialNext = graph[row][column + 1]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row) to column + 1)
                        }
                    }
                }
                if (column - 1 in graph[row].indices) {
                    val potentialNext = graph[row][column - 1]
                    if (!potentialNext.visited) {
                        val letter = potentialNext.letter
                        if (canMove(current.letter, letter)) {
                            potentialNext.shortest = minOf(potentialNext.shortest, current.shortest + 1)
                            queue.add((row) to column - 1)
                        }
                    }
                }
            }
        }
        return graph[end.first][end.second].shortest.also { println(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
