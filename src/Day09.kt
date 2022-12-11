import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main() {
    data class Coords(
        val column: Int,
        val row: Int,
    ) {

        fun distance(other: Coords): Int {
            return max(abs(column - other.column), abs(row - other.row))
        }
    }


    fun part1(input: List<String>): Int {

        data class Visits(
            val head: Int = 0,
            val tail: Int = 0,
        )

        operator fun Visits?.plus(other: Visits): Visits {
            return this?.let {
                it.copy(it.head + other.head, it.tail + other.tail)
            } ?: other
        }

        val startpath = mutableMapOf<Coords, Visits>()
        var coords = Coords(0, 0)
        var tailCoords = Coords(0, 0)
        startpath[coords] = Visits(1, 1)
        for (line in input) {
            val line = line.split(" ")
            val command = line[0]
            val amount = line[1].toInt()
            when (command) {
                "U" -> {
                    var target = coords.copy(column = coords.column + amount)
                    for (i in 1..amount) {
                        coords = coords.copy(column = coords.column + 1)
                        startpath[coords] = startpath[coords] + Visits(head = 1)
                        if (coords.distance(tailCoords) >= 2) {
                            if (tailCoords.row == coords.row) {
                                tailCoords = tailCoords.copy(column = tailCoords.column + 1)
                            } else {
                                tailCoords = tailCoords.copy(column = tailCoords.column + 1, row = coords.row)
                            }
                            startpath[tailCoords] = startpath[tailCoords] + Visits(tail = 1)
                        }
                    }
                }

                "L" -> {
                    var target = coords.copy(row = coords.row - amount)
                    for (i in 1..amount) {
                        coords = coords.copy(row = coords.row - 1)
                        startpath[coords] = startpath[coords] + Visits(head = 1)
                        if (coords.distance(tailCoords) >= 2) {
                            if (tailCoords.column == coords.column) {
                                tailCoords = tailCoords.copy(row = tailCoords.row - 1)
                            } else {
                                tailCoords = tailCoords.copy(column = coords.column, row = tailCoords.row - 1)
                            }
                            startpath[tailCoords] = startpath[tailCoords] + Visits(tail = 1)
                        }
                    }
                }

                "R" -> {
                    var target = coords.copy(row = coords.row + amount)
                    for (i in 1..amount) {
                        coords = coords.copy(row = coords.row + 1)
                        startpath[coords] = startpath[coords] + Visits(head = 1)
                        if (coords.distance(tailCoords) >= 2) {
                            if (tailCoords.column == coords.column) {
                                tailCoords = tailCoords.copy(row = tailCoords.row + 1)
                            } else {
                                tailCoords = tailCoords.copy(column = coords.column, row = tailCoords.row + 1)
                            }
                            startpath[tailCoords] = startpath[tailCoords] + Visits(tail = 1)
                        }
                    }
                }

                "D" -> {
                    var target = coords.copy(column = coords.column - amount)
                    for (i in 1..amount) {
                        coords = coords.copy(column = coords.column - 1)
                        startpath[coords] = startpath[coords] + Visits(head = 1)
                        if (coords.distance(tailCoords) >= 2) {
                            if (tailCoords.row == coords.row) {
                                tailCoords = tailCoords.copy(column = tailCoords.column - 1)
                            } else {
                                tailCoords = tailCoords.copy(column = tailCoords.column - 1, row = coords.row)
                            }
                            startpath[tailCoords] = startpath[tailCoords] + Visits(tail = 1)
                        }
                    }
                }
            }
        }


        return startpath.values.filter { it.tail > 0 }.count()
    }

    fun moveTail(coords: MutableList<Coords>, tail: Int) {
        val previousKnot = coords[tail - 1]
        val current = coords[tail]
        val difference = Coords(column = previousKnot.column - current.column, row = previousKnot.row - current.row)

            coords[tail] = coords[tail].copy(
                column = current.column + difference.column.sign,
                row = current.row + difference.row.sign
            )

    }

    fun part2(input: List<String>): Int {

        data class Visits(
            val head: Int = 0,
            val tail: Int = 0,
        )

        operator fun Visits?.plus(other: Visits): Visits {
            return this?.let {
                it.copy(it.head + other.head, it.tail + other.tail)
            } ?: other
        }

        val startpath = mutableMapOf<Coords, Visits>()
        var coords = (1..10).map { Coords(0, 0) }.toMutableList()

        startpath[coords[9]] = Visits(1, 1)
        for (line in input) {
            val line = line.split(" ")
            val command = line[0]
            val amount = line[1].toInt()
            when (command) {
                "U" -> {

                    for (i in 1..amount) {
                        coords[0] = coords[0].copy(column = coords[0].column + 1)
                        for (tail in 1..9) {

                            if (coords[tail - 1].distance(coords[tail]) >= 2) {
                                moveTail(coords, tail)
                            }
                        }
                        startpath[coords[9]] = startpath[coords[9]] + Visits(tail = 1)
                    }
                }

                "L" -> {
                    for (i in 1..amount) {
                        coords[0] = coords[0].copy(row = coords[0].row - 1)
                        for (tail in 1..9) {

                            if (coords[tail - 1].distance(coords[tail]) >= 2) {
                                moveTail(coords, tail)
                            }
                        }
                        startpath[coords[9]] = startpath[coords[9]] + Visits(tail = 1)
                    }
                }

                "R" -> {
                    for (i in 1..amount) {
                        coords[0] = coords[0].copy(row = coords[0].row + 1)
                        for (tail in 1..9) {

                            if (coords[tail - 1].distance(coords[tail]) >= 2) {
                                moveTail(coords, tail)
                            }
                        }
                        startpath[coords[9]] = startpath[coords[9]] + Visits(tail = 1)
                    }
                }

                "D" -> {
                    for (i in 1..amount) {
                        coords[0] = coords[0].copy(column = coords[0].column - 1)
                        for (tail in 1..9) {

                            if (coords[tail - 1].distance(coords[tail]) >= 2) {
                                moveTail(coords, tail)
                            }
                        }
                        startpath[coords[9]] = startpath[coords[9]] + Visits(tail = 1)
                    }
                }
            }
            println(startpath)
        }


        return startpath.values.filter { it.tail > 0 }.count().also { println(it) }

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
