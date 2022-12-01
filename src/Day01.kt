fun main() {
    fun part1(input: List<String>): Int {
        var max = 0;
        var current = 0;
        for (line in input) {
            if (line.isEmpty()) current = 0 else
                current += line.toInt()

            if (current > max) max = current
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val maxSet = mutableSetOf(0)
        var current = 0;
        for (line in input) {
            if (line.isEmpty()) {
                if (maxSet.any { it<current }){
                    maxSet+=current
                    if (maxSet.size>3){
                        maxSet.remove(maxSet.min())

                    }
                }
                current = 0
            } else
                current += line.toInt()


        }
        return maxSet.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
