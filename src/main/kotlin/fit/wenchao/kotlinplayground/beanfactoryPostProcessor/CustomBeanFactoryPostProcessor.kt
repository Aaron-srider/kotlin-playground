package fit.wenchao.kotlinplayground.beanfactoryPostProcessor;

import fit.wenchao.RecordDaoImpl
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.stereotype.Component


@Component
class CustomBeanFactoryPostProcessor : BeanDefinitionRegistryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
//        TODO("Not yet implemented")
    }

    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        println("========> postProcessBeanDefinitionRegistry --->")

        val beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(
            RecordDaoImpl::class.java
        )
        val beanDefinition: BeanDefinition = beanDefinitionBuilder.rawBeanDefinition

        registry.registerBeanDefinition("recordDaoImpl", beanDefinition)
    }

}
