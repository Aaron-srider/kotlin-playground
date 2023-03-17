@file:Suppress("UNUSED_EXPRESSION")

package fit.wenchao.kotlinplayground

import fit.wenchao.kotlinplayground.dao.mapper.UserMapper
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.function.Consumer


@SpringBootApplication
class KotlinPlayGroundApplication(
    @Autowired
    var userMapper: UserMapper
)

fun main(args: Array<String>) {

    val kFunction0 = ::print

//    print()
//    var user = UserPO()
//    println(user.toString())
//    val fields = UserPO::class.members
//    println(fields.size)
//
//    for (field in fields) {
//        println(field)
//    }
//
//    val javaClass = user.javaClass
//
//    val declaredField = javaClass.getDeclaredField("id")
//    println(declaredField)
//    println(field)
    runApplication<KotlinPlayGroundApplication>(*args)
}

fun print() {
    println("hello")
}

fun  print2() {

}
