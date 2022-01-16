

// Need to clean this code up to new video with sealed class
fun main() {

    // Creating instance of NewRepository ( remember we can only have this one instance of a object)
    // calling method .startFetch on our new instance
    NewRepository.startFetch()

    // Calling getNewResult
    getNewResult(result = NewRepository.getCurrentState())

    Thread.sleep(2_000)  // wait for 2 second

    NewRepository.finishedFetch()
    getNewResult(result = NewRepository.getCurrentState())

    Thread.sleep(2_000)

    NewRepository.error()
    getNewResult(result = NewRepository.getCurrentState())

}

// object class, we can only have 1 instance of this.
// here we are assuming this data come from somewhere like a database, or api or similar
object NewRepository {
    private var loadNewState: NewResult = NewResult.IDLE
    private var dataFetched: String? = null

    // Methods for each case
    fun startFetch(){
        loadNewState = NewResult.LOADING
        dataFetched = "data"
    }
    fun finishedFetch(){
        loadNewState = NewResult.SUCCESS
        dataFetched = null
    }

    fun error(){
        loadNewState = NewResult.ERROR
        dataFetched = null
    }

    // methods to return the current state
    fun getCurrentState(): NewResult {
        return loadNewState
    }

}

// Enumerate means to list things one by one.
// The idea here is to get some kind of state for the object we create of class result -
// so result could, be success, failure, or error -
// and then we can do something with the object depending on its state
enum class NewResult {
    SUCCESS,
    ERROR,
    IDLE,
    LOADING
}

fun getNewResult(result: NewResult){
    return when(result) {
        NewResult.SUCCESS -> println("Success")
        NewResult.ERROR -> println("Error")
        NewResult.IDLE -> println("Idle")
        NewResult.LOADING -> println("Loading...")
    }
}

