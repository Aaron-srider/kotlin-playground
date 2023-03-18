@file:Suppress("UNUSED_EXPRESSION")

package fit.wenchao.kotlinplayground

import fit.wenchao.kotlinplayground.dao.mapper.UserMapper
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.function.Consumer


@SpringBootApplication
class KotlinPlayGroundApplication(
    @Autowired
    var userMapper: fit.wenchao.kotlinplayground.dao.mapper.UserMapper
)

@Component
class TestSpringBeanFactoryPostProcessorBean {

    var testInjection: TestInjection? = null

}

open class TestInjection

@Component
class TestInjectionA : TestInjection()

@Component
class TestInjectionB : TestInjection()

@Component
class FieldAutoInjectBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        var testInjection = TestInjectionA()
        println(testInjection)
        beanFactory.registerResolvableDependency(TestInjection::class.java, testInjection);
    }
}

fun main(args: Array<String>) {

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
    val appCtx = runApplication<KotlinPlayGroundApplication>(*args)


    val testSpringBeanFactoryPostProcessorBean = appCtx.getBean(
        "testSpringBeanFactoryPostProcessorBean",
        TestSpringBeanFactoryPostProcessorBean::class.java
    )
    println(testSpringBeanFactoryPostProcessorBean.testInjection)

}
