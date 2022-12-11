fun main() {

    fun part1(input: List<String>): Int {
        val subList = mutableListOf<Char>()
        var result = 0
        input.first().toCharArray().forEachIndexed { index, char ->

            subList += char
            if (subList.size > 4) {
                subList.removeFirst()
            }
            if (subList.size == 4) {
                if (subList.toSet().size == 4)
                    if (result == 0) result = index + 1
            }

        }
        return result
    }

    fun part2(input: List<String>): Int {
        val subList = mutableListOf<Char>()
        var result = 0
        input.first().toCharArray().forEachIndexed { index, char ->

            subList += char
            if (subList.size > 14) {
                subList.removeFirst()
            }
            if (subList.size == 14) {
                if (subList.toSet().size == 14)
                    if (result == 0) result = index + 1
            }

        }
        return result


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    //check(part2(testInput) == 0)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
