fun Char.charValue(): Int = when {
    isUpperCase() -> this - 'A' + 27
    isLowerCase() -> this - 'a' + 1
    else -> 0
}

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (ransack in input) {
            val first = ransack.substring(0, ransack.length / 2)
            val second = ransack.substring(ransack.length / 2)

            sum += second.toCharArray().toSet().intersect(first.toCharArray().toSet()).sumOf { it.charValue() }

        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (index in input.indices step 3) {
            val first = input[index]
            val second = input[index + 1]
            val third = input[index + 2]
            sum += second.toSet().intersect(first.toSet()).intersect(third.toSet()).sumOf { it.charValue() }

        }
        return sum

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
