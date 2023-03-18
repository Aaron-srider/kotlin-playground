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
    lateinit var userMapper: fit.wenchao.kotlinplayground.dao.mapper.UserMapper

    @Test
    fun contextLoads() {
        println("----- selectAll method test ------")
        val userList: List<fit.wenchao.kotlinplayground.dao.po.UserPO> = userMapper.selectList(null)
        println(userList.size)
        userList.forEach(Consumer { x: fit.wenchao.kotlinplayground.dao.po.UserPO? -> println(x) })
    }
}




