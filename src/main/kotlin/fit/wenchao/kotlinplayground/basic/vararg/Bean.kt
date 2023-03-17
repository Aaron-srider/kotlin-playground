package fit.wenchao.kotlinplayground.basic.vararg


class Bean {

}

var a: Int = 2;



fun main() {
    varargFun("hello", " ", "world")

}

fun varargFun(vararg args: String) {
    for (arg in args) {
        println(arg)
    }
}