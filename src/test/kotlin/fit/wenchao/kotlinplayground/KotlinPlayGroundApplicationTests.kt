package fit.wenchao.kotlinplayground

import fit.wenchao.kotlinplayground.dao.mapper.UserMapper
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.function.Consumer


@SpringBootTest
class KotlinPlayGroundApplicationTests{

    @Autowired
    lateinit var userMapper: UserMapper

    @Test
    fun contextLoads() {
        println("----- selectAll method test ------")
        val userList: List<UserPO> = userMapper.selectList(null)
        println(userList.size)
        userList.forEach(Consumer { x: UserPO? -> println(x) })
    }
}




