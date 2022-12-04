private const val SCISSORS_RESP = "Z"

private const val ROCK_RESP = "X"


private const val PAPER__RESP = "Y"

private const val PAPER = "B"

private const val ROCK = "A"

private const val SCISSORS = "C"


private const val WIN = "Z"

private const val LOSE = "X"

private const val DRAW = "Y"

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (single in input) {
            val split = single.split(" ")
            val first = split[0]
            val response = split[1]
            val pair = first to response
            sum += when (pair) {
                PAPER to PAPER__RESP -> 3 + 2
                ROCK to PAPER__RESP -> 6 + 2
                SCISSORS to PAPER__RESP -> 0 + 2
                PAPER to ROCK_RESP -> 0 + 1
                ROCK to ROCK_RESP -> 3 + 1
                SCISSORS to ROCK_RESP -> 6 + 1
                PAPER to SCISSORS_RESP -> 6 + 3
                ROCK to SCISSORS_RESP -> 0 + 3
                SCISSORS to SCISSORS_RESP -> 3 + 3
                else -> 0
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (single in input) {
            val split = single.split(" ")
            val first = split[0]
            val response = split[1]
            val pair = first to response
            sum += when (pair) {
                PAPER to LOSE -> 0 + 1
                ROCK to LOSE -> 0 + 3
                SCISSORS to LOSE -> 0 + 2
                PAPER to DRAW -> 3 + 2
                ROCK to DRAW -> 3 + 1
                SCISSORS to DRAW -> 3 + 3
                PAPER to WIN -> 6 + 3
                ROCK to WIN -> 6 + 2
                SCISSORS to WIN -> 6 + 1
                else -> 0
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
