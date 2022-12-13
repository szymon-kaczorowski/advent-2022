fun main() {
    data class ListOrInt(var children: MutableList<ListOrInt> = mutableListOf(), var value: Int? = null) :
        Comparable<ListOrInt> {

        var parent: ListOrInt? = null
        override fun compareTo(other: ListOrInt): Int {
            if (this.value != null) {
                if (other.value != null) {
                    return value!! - other.value!!
                } else
                    return ListOrInt(children = mutableListOf(this)).compareTo(other)
            } else {
                if (other.value != null) {
                    return this.compareTo(ListOrInt(children = mutableListOf(other)))
                } else {

                    for (i in 0..maxOf(this.children.lastIndex, other.children.lastIndex)) {
                        if (i > this.children.lastIndex) return -1
                        if (i > other.children.lastIndex) return 1
                        val compareTo = this.children[i].compareTo(other.children[i])
                        if (compareTo != 0) {
                            return compareTo
                        }
                    }
                    return 0

                }
            }
        }
    }

    fun String.parse(): ListOrInt {
        val asChars = this.toCharArray()

        var currentElement = ListOrInt()
        val root = currentElement

        asChars.forEach { char ->
            if (char == '[') {
                currentElement.children.add(ListOrInt())
                val parent = currentElement
                currentElement = currentElement.children.last()
                currentElement.parent = parent
            }
            if (char == ']') {
                currentElement = currentElement.parent!!
            }
            if (char.isDigit()) {
                if (currentElement.value != null) {
                    currentElement.value = currentElement.value!! * 10 + char.digitToInt()
                } else {
                    currentElement.value = char.digitToInt()
                }
            }
            if (char == ',') {
                currentElement.parent!!.children.add(ListOrInt())
                val parent = currentElement.parent!!
                currentElement = parent.children.last()
                currentElement.parent = parent
            }

        }
        return root

    }


    fun part1(input: List<String>): Int {
        var max = 0;
        var index = 0
        for (signal in input.indices step 3) {
            index++
            val left = input[signal].parse()
            val right = input[signal + 1].parse()
            if (left < right) {
                max += index
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val first = "[2]".parse()
        val second = "[6]".parse()
        var mult = 1
        val newInput = input + listOf("[2]", "[6]")
        val map = newInput.filterNot { it.isBlank() }.map { it.parse() }.sorted()
        map.forEachIndexed { index, list ->
            if (list == first || list == second) {
                mult *= index + 1
            }
        }

        return mult
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 140)

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}
