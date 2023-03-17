package fit.wenchao.kotlinplayground.basic.staticSuger

import kotlin.reflect.full.staticFunctions

class Bean {
    fun hello() {
        println("hello")
    }
}

fun main() {
    var staticFunctions = Bean::class.staticFunctions
    println(staticFunctions.size)

    for (staticFunction in staticFunctions) {
        println(staticFunction.name)
        staticFunction.call()
    }

}

fun staticFunc() {
    println("I am static func: ")
}