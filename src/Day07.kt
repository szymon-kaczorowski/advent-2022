data class Directory(
    val name: String,
    val parent: Directory? = null,
    val directories: MutableMap<String, Directory> = mutableMapOf(),
    val files: MutableList<AdventFile> = mutableListOf(),
) {

    override fun toString(): String =
        "name=${name},directories=$directories,files=$files"

    fun size(): Int = files.sumOf { it.size } + directories.values.sumOf {
        it.size()
    }

    fun sizes(): List<Pair<String, Int>> {
        var map = directories.values.map { it.name to it.size() }
        map = map + directories.values.flatMap { it.sizes() }
        return map
    }


}

data class AdventFile(
    val name: String,
    val size: Int,
)

fun readTree(input: List<String>): Directory {
    val root = Directory("/")
    var currentDirectory = root
    for (command in input) {


        if (command.startsWith("$")) {
            if (command.startsWith("$ cd")) {
                val dirName = command.replace("$ cd ", "")
                when (dirName) {
                    "/" -> currentDirectory = root
                    ".." -> currentDirectory = currentDirectory.parent!!
                    else -> currentDirectory = currentDirectory.directories[dirName]!!
                }
            }
        } else {
            if (command.startsWith("dir")) {
                val dirName = command.replace("dir ", "")
                val directory = Directory(dirName, currentDirectory)

                currentDirectory.directories[dirName] = directory
            } else {
                val parts = command.split(" ")
                currentDirectory.files += AdventFile(name = parts[1], size = parts[0].toInt())
            }
        }
    }
    return root
}

fun main() {

    fun part1(input: List<String>): Int {
        val tree = readTree(input)
        val sizes = tree.sizes()
        return sizes.filter { it.second <= 100000 }.sumOf { it.second }.also { println(it) }
    }

    fun part2(input: List<String>): Int {
        val tree = readTree(input)
        val size = tree.size()
        val maxSize = 70000000L
        val currentTaken = maxSize - size
        val toFree = 30000000L - (maxSize - size)
        println("$toFree $size")
        val totalTree = tree.sizes() + listOf("/" to size)
        return totalTree.filter { it.second >= toFree }.map {
            it.second
        }.min()

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
