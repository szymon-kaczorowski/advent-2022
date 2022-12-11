fun main() {

    fun part1(input: List<String>): String {
        var i = 0
        while (input[i].isNotBlank()) {
            i++
        }
        var totalStacks = input[i - 1].last().toString().toInt()


        var crane = (1..totalStacks).map { mutableListOf<Char>() }.toMutableList()
        for (j in 0.until(i)) {
            var stacks = input[j]

            for (k in 0.until(totalStacks)) {
                if (stacks.length > k * 4 + 1) {
                    val c = stacks[k * 4 + 1]
                    if (c.isWhitespace()) {
                    } else {
                        if (!c.isDigit()) {
                            crane[k].add(0, c)
                            println(crane)
                        }
                    }
                }
            }
        }
        for (command in (i + 1).until(input.size)) {
            var commandText = input[command]
            var commandPieces = commandText.split(" ")
            var amount = commandPieces[1].toInt()
            var from = commandPieces[3].toInt()
            var into = commandPieces[5].toInt()
            for (calc in 1..amount) {
                crane[into - 1].add(crane[from - 1].last())
                crane[from - 1].removeLast()
            }
        }
        return crane.map {
            it.last()
        }.joinToString(separator = "") { it.toString() }

    }

    fun part2(input: List<String>): String {
        var i = 0
        while (input[i].isNotBlank()) {
            i++
        }
        println(input[i - 1])
        var totalStacks = input[i - 1].last().toString().toInt()


        var crane = (1..totalStacks).map { mutableListOf<Char>() }.toMutableList()
        for (j in 0.until(i)) {
            var stacks = input[j]

            for (k in 0.until(totalStacks)) {
                if (stacks.length > k * 4 + 1) {
                    val c = stacks[k * 4 + 1]
                    if (c.isWhitespace()) {
                    } else {
                        if (!c.isDigit()) {
                            crane[k].add(0, c)
                        }
                    }
                }
            }
        }
        for (command in (i + 1).until(input.size)) {
            var commandText = input[command]
            var commandPieces = commandText.split(" ")
            var amount = commandPieces[1].toInt()
            var from = commandPieces[3].toInt()
            var into = commandPieces[5].toInt()
            var temp = mutableListOf<Char>()
            for (calc in 1..amount) {
                temp += crane[from - 1].removeLast()
            }
            crane[into-1].addAll(temp.reversed())
        }
        return crane.map {
            it.last()
        }.joinToString(separator = "") { it.toString() }.also { println(it) }


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
