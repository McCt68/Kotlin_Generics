
fun main() {

    // Creating instance of Repository ( remember we can only have this one instance of a object)
    // calling method .startFetch on our new instance
    Repository.startFetch()

    // Calling getResult
    getResult(result = Repository.getCurrentState())

    Thread.sleep(2_000)  // wait for 2 second

    Repository.finishedFetch()
    getResult(result = Repository.getCurrentState())

    Thread.sleep(2_000)

    Repository.error()
    getResult(result = Repository.getCurrentState())

}

// object class, we can only have 1 instance of this.
// here we are assuming this data come from somewhere like a database, or api or similar
object Repository {
    private var loadState: Result = Result.IDLE
    private var dataFetched: String? = null

    // Methods for each case
    fun startFetch(){
        loadState = Result.LOADING
        dataFetched = "data"
    }
    fun finishedFetch(){
        loadState = Result.SUCCESS
        dataFetched = null
    }

    fun error(){
        loadState = Result.ERROR
        dataFetched = null
    }

    fun failure(){
        loadState = Result.FAILURE
        dataFetched = null
    }

    // methods to return the current state
    fun getCurrentState(): Result {
        return loadState
    }

}

// Enumerate means to list things one by one.
// The idea here is to get some kind of state for the object we create of class result -
// so result could, be success, failure, or error -
// and then we can do something with the object depending on its state
enum class Result {
    SUCCESS,
    FAILURE,
    ERROR,
    IDLE,
    LOADING
}

fun getResult(result: Result){
    return when(result) {
        Result.SUCCESS -> println("Success")
        Result.FAILURE -> println("Failure")
        Result.ERROR -> println("Error")
        Result.IDLE -> println("Idle")
        Result.LOADING -> println("Loading")
    }
}

