
// Program can find a specific item in a list of any data type <T>

fun main() {

    //
    val myListOfItems = listOf("Skak", 55, "Uno", true, "Backgammon", "Poker", 88,  "Matador", false)

    // create a Finder Object
    val myFinderObject = genericFinder(myListOfItems)

    //
    myFinderObject.findItem(88){
        println("Found Item $it")

    }

}

// Constructor takes a list of any type <T> which is private, so it can only be used within this class
class genericFinder<T>(private val myList: List<T>){

    // Function with two parameters.
    // First parameter is a element to search for as any type <T> -
    // Second Parameter is a function, with a parameter of element to find
    // When the last parameter is a function, we can provide a lambda as parameter.
    fun findItem(elementToFind: T, foundItem: (elementToFind: T?) -> Unit){

        // Filtered the items in myList, and search for the item provided as argument elementToFind, when we call
        // the function. It will return a list with only 1 item (the elementToFind)
        // in the list
        val itemFoundList = myList.filter {
            it == elementToFind
        }
        if (itemFoundList.isNullOrEmpty()) foundItem(null) else
            foundItem(itemFoundList.first()) // find the only item that's in the list (the first one)
    }
}