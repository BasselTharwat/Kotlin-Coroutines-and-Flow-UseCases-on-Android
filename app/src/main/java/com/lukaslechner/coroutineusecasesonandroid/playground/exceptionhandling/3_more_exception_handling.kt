import kotlinx.coroutines.*

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        launch {
            println("starting coroutine 1")
            delay(100)
            throw RuntimeException()
        }

        launch {
            println("starting coroutine 2")
            delay(200)
            println("coroutine 2 finished")
        }
    }

    delay(5000) // Keep the program running to see output
}
