import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray

fun main() {

    operator fun JsonElement.compareTo(other: JsonElement): Int {
        if (this is JsonPrimitive) {
            if (other is JsonPrimitive) {
                return content.toInt() - other.content.toInt()
            } else
                return JsonArray(listOf(this)).compareTo(other)
        } else {
            if (other is JsonPrimitive) {
                return this.compareTo(JsonArray(listOf(other)))
            } else {
                val children = this.jsonArray
                val otherChildren = other.jsonArray
                for (i in 0..maxOf(children.lastIndex, otherChildren.lastIndex)) {
                    if (i > children.lastIndex) return -1
                    if (i > otherChildren.lastIndex) return 1
                    val compareTo = children[i].compareTo(otherChildren[i])
                    if (compareTo != 0) {
                        return compareTo
                    }
                }
                return 0

            }
        }
    }

    fun String.parse() = Json.parseToJsonElement(this)


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
        val map = newInput.filterNot { it.isBlank() }.map { it.parse() }.sortedWith(
            comparator = { one, two ->
                one.compareTo(two)
            }
        )
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
