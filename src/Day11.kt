import java.math.BigInteger

fun main() {
    data class Monkey(
        var items: MutableList<BigInteger> = mutableListOf(),
        var testDivision: Int = 1,
        var operation: (BigInteger) -> BigInteger = { it },
        var targetTrue: Int = -1,
        var targetFalse: Int = -1,
        var process: Int = 0,
    ) {

    }

    fun monkeys(
        input: List<String>,
        multLevels: Int,
    ): Pair<Int, MutableList<Monkey>> {
        var multLevels1 = multLevels
        val monkeys = mutableListOf<Monkey>()
        for (line in input) {
            if (line.startsWith("Monkey")) {
                monkeys.add(Monkey())
            }
            if (line.contains("Starting")) {
                monkeys.last().items =
                    line.trim().replace("Starting items: ", "").split(",").map { it.trim().toLong().toBigInteger() }
                        .toMutableList()
            }
            if (line.contains("Operation")) {
                val split = line.trim().replace("Operation: new = old ", "").split(" ")
                when (split[1]) {
                    "old" -> {
                        monkeys.last().operation = { old -> old.pow(2) }
                    }

                    else -> {
                        val data = split[1].trim().toInt()
                        monkeys.last().operation = when (split[0]) {
                            "+" -> { old -> old.plus(data.toBigInteger()) }
                            "*" -> { old -> old.times(data.toBigInteger()) }
                            else -> { old -> old }
                        }
                    }
                }
            }
            if (line.contains("Test")) {
                val toInt = line.trim().replace("Test: divisible by ", "").trim().toInt()
                monkeys.last().testDivision = toInt
                multLevels1 *= toInt
            }
            if (line.contains("true")) {
                monkeys.last().targetTrue = line.trim().replace("If true: throw to monkey", "").trim().toInt()
            }
            if (line.contains("false")) {
                monkeys.last().targetFalse = line.trim().replace("If false: throw to monkey", "").trim().toInt()
            }
        }
        return multLevels1 to monkeys
    }

    fun part1(input: List<String>): Int {
        fun Monkey.processItem() = if (items.isNotEmpty()) {
            process++
            items.removeFirst().let(operation).let { it.divide(BigInteger.valueOf(3)) }.let {
                it to (if (it.remainder(testDivision.toBigInteger()).toInt() == 0) {
                    targetTrue
                } else targetFalse)
            }

        } else null

        val (_, monkeys) = monkeys(input, 1)
        for (rounds in 1..20) {
            for (monkey in monkeys) {
                var processResult: Pair<BigInteger, Int>?
                do {
                    processResult = monkey.processItem()
                    if (processResult != null) {
                        monkeys[processResult.second].items.add(processResult.first)
                    }
                } while (processResult != null)
            }
        }
        val top2 = monkeys.sortedBy { it.process }.reversed().take(2)
        return top2[0].process * top2[1].process
    }


    fun part2(input: List<String>): Long {
        var multLevels: Int = 1
        fun Monkey.processItems(): List<Pair<BigInteger, Int>> {
            val processPairs = items.map { operation(it) }.map {
                it.rem(multLevels.toBigInteger())
            }.map {
                it to (if (it.remainder(testDivision.toBigInteger()).toInt() == 0) {
                    targetTrue
                } else targetFalse)
            }
            process += items.size
            items.clear()
            return processPairs
        }

        val (resultMult, monkeys) = monkeys(input, multLevels)
        multLevels = resultMult
        for (rounds in 1..10000) {
            for (monkey in monkeys) {
                monkey.processItems().forEach {
                    monkeys[it.second].items.add(it.first)
                }
            }
        }
        val top2 = monkeys.sortedBy { it.process }.reversed().take(2)
        return top2[0].process.toLong() * top2[1].process.toLong()

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 10605)
    check(part2(testInput) == 2713310158L)
    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
