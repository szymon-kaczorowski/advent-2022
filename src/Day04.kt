fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.split(",")
        }.map {
            var firstElf = it[0].split("-")
            var secondElf = it[1].split("-")
            (firstElf[0].toInt()..firstElf[1].toInt()) to (secondElf[0].toInt()..secondElf[1].toInt())
        }.filter {
            println(it)
            (it.first.start <= it.second.start && it.first.endInclusive >= it.second.endInclusive)
                    ||
                    (it.second.start <= it.first.start && it.second.endInclusive >= it.first.endInclusive)
        }.size.also { println(it) }

    }

    fun part2(input: List<String>): Int {
        return input.map {
            it.split(",")
        }.map {
            var firstElf = it[0].split("-")
            var secondElf = it[1].split("-")
            (firstElf[0].toInt()..firstElf[1].toInt()) to (secondElf[0].toInt()..secondElf[1].toInt())
        }.filter {
            println(it)
            (it.first.start <= it.second.start
                    && it.first.endInclusive >= it.second.start)
                    ||
                    (it.second.start <= it.first.start
                            && it.second.endInclusive >= it.first.start)
        }.size.also { println(it) }


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
