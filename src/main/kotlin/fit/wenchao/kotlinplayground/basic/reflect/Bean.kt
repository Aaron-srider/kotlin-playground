package fit.wenchao.kotlinplayground.basic.reflect

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberFunctions

class Bean{

    var age: Int = 0

    var name: String= ""

    var addresses: List<String> = ArrayList();

    fun report() {
        println(toString())
    }

    override fun toString(): String {
        return "Bean(age=$age, name='$name', addresses=$addresses)"
    }

}



fun main() {





    var bean = Bean();


    for (member in bean::class.members) {
        println(member.call(bean))
    }

    println("members")
    val members = Bean::class.members
    for (member in members) {
        println(member)

    }

    println("declaredFunctions")
    val declaredFunctions = Bean::class.declaredFunctions
    for (declaredFunction in declaredFunctions) {
        println(declaredFunction)
        // If this callable requires a this instance or an extension receiver parameter, they come first in the list in
        // that order.
        println(declaredFunction.parameters.size)
        if(declaredFunction.parameters.size == 1) {
            println("class not arg function: " + declaredFunction.name + " below is call result")
            declaredFunction.call(bean)
        }
    }

    println("declaredMemberFunctions")
    val declaredMemberFunctions = Bean::class.declaredMemberFunctions
    for (declaredMemberFunction in declaredMemberFunctions) {
        println(declaredMemberFunction)
        // If this callable requires a this instance or an extension receiver parameter, they come first in the list in
        // that order.
        println(declaredMemberFunction.parameters.size)
        if(declaredMemberFunction.parameters.size == 1) {
            println("class not arg function: " + declaredMemberFunction.name + " below is call result")
            declaredMemberFunction.call(bean)
        }
    }


    println(bean)
}

