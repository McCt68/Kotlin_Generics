
// Program can find a specific item (datatype: String) in a list of strings -
// but it can only search ListOf Strings

fun main() {

    //
    val myListOfItems = listOf<String>("Skak", "Uno", "Backgammon", "Poker", "Matador")

    // create a Finder Object
    val myFinderObject = genericFinder(myListOfItems)

    //
    myFinderObject.findItem("Poker"){
        println("Found Item $it")

    }

}

// Constructor takes a list of strings which is private, so it can only be used within this class
class Finder(private val myList: List<String>){

    // Function with two parameters.
    // First parameter is a element to search for as a String type -
    // Second Parameter is a function, with a parameter of element to find
    // When the last parameter is a function, we can provide a lambda as parameter.
    fun findItem(elementToFind: String, foundItem: (elementToFind: String?) -> Unit){

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