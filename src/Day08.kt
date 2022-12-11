fun main() {
    data class Tree(
        val height: Int,
        var visible: Boolean? = null,
    ) {

        override fun toString(): String {
            return "$height${if (visible == true) "*" else "o"}"
        }
    }

    fun part1(input: List<String>): Int {
        fun isVisible(forest: MutableList<List<Tree>>, column: Int, row: Int): Boolean {
            val tree = forest[column][row]

            if (column == 0 || column == forest.lastIndex) {
                tree.visible = true
                return true
            }
            if (row == 0 || row == forest[column].lastIndex) {
                tree.visible = true
                return true
            }
            var rigtVisible = true
            for (right in column + 1..forest.lastIndex) {
                if (forest[right][row].height >= tree.height) {
                    rigtVisible = false
                }
            }
            var leftVisible = true
            for (left in 0..column - 1) {
                if (forest[left][row].height >= tree.height) {
                    leftVisible = false
                }
            }
            var topVisible = true
            for (top in 0..row - 1) {
                if (forest[column][top].height >= tree.height) {
                    topVisible = false
                }
            }
            var bottomVisible = true
            for (bottom in row + 1..forest[column].lastIndex) {
                if (forest[column][bottom].height >= tree.height) {
                    bottomVisible = false
                }
            }
            if (topVisible || bottomVisible || leftVisible || rigtVisible) {
                tree.visible = true
                return true
            }
            tree.visible = false
            return false
        }

        val forest = mutableListOf<List<Tree>>()
        var visible = 0
        for (index in input.indices) {
            forest += input[index].toCharArray().toList().map { Tree(it.toString().toInt()) }
        }
        for (horizontal in forest.indices) {
            for (vertical in forest[horizontal].indices) {
                if (isVisible(forest, horizontal, vertical)) visible++
            }
        }
        println(visible)
        println(forest)
        return visible
    }

    fun part2(input: List<String>): Int {
        fun isVisible(forest: MutableList<List<Tree>>, column: Int, row: Int): Int {
            val tree = forest[column][row]

            if (column == 0 || column == forest.lastIndex) {
                return 0
            }
            if (row == 0 || row == forest[column].lastIndex) {
                return 0
            }
            var rigtVisible = 0
            for (right in column + 1..forest.lastIndex) {
                rigtVisible++
                if (forest[right][row].height >= tree.height) {
                    break;
                }

            }
            var leftVisible = 0
            for (left in 0..column - 1) {
                if (forest[left][row].height >= tree.height) {
                    leftVisible = 1
                } else {
                    leftVisible++
                }
            }
            var topVisible = 0
            for (top in 0..row - 1) {
                if (forest[column][top].height >= tree.height) {
                    topVisible = 1
                } else {
                    topVisible++
                }
            }
            var bottomVisible = 0
            for (bottom in row + 1..forest[column].lastIndex) {
                bottomVisible++
                if (forest[column][bottom].height >= tree.height) {
                    break
                }
            }

            val result = bottomVisible * topVisible * leftVisible * rigtVisible
            println("$result $column $row $bottomVisible * $topVisible * $leftVisible * $rigtVisible")
            return result
        }

        val forest = mutableListOf<List<Tree>>()
        var visible = 0
        for (index in input.indices) {
            forest += input[index].toCharArray().toList().map { Tree(it.toString().toInt()) }
        }
        for (horizontal in forest.indices) {
            for (vertical in forest[horizontal].indices) {
                visible = maxOf(visible, isVisible(forest, horizontal, vertical))
            }
        }
        println(visible)
        return visible

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
