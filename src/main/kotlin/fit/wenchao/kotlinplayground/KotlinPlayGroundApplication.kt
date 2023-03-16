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
    print()
    runApplication<KotlinPlayGroundApplication>(*args)
}

fun print() {
    println("hello")
}
