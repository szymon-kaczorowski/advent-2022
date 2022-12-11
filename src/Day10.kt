import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val signal = mutableListOf<Int>()
        signal.add(1)
        input.forEach {
            signal.add(signal.last())

            if (it != "noop") {
                val toInt = it.split(" ")[1].toInt()
                signal.add(signal.last() + toInt);
            }

        }
        val signaltimesIndex = signal.mapIndexed { index, i ->
            i * (index+1)
        }
        val indexes = listOf(20, 60, 100, 140, 180, 220)
        return indexes.map { signaltimesIndex[it-1] }.map {
            it
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val signal = mutableListOf<Int>()
        signal.add(1)
        input.forEach {
            signal.add(signal.last())

            if (it != "noop") {
                val toInt = it.split(" ")[1].toInt()
                signal.add(signal.last() + toInt);
            }

        }
        val mutableList = (0..5).mapIndexed {
            index, value ->
            (1..40).mapIndexed {
                lineIndex, innerValue ->
                val spriteMid = signal[index * 40 + lineIndex]
                if (abs(lineIndex-spriteMid)<=1) print("#") else print(".")
            }.toMutableList()
            println()
        }.toMutableList()


        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    check(part2(testInput) == 0)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
