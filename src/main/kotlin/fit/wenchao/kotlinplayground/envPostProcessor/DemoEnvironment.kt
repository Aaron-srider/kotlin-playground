package fit.wenchao.kotlinplayground.envPostProcessor;

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import java.util.Collections

class DemoEnvironment : EnvironmentPostProcessor {
    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication?) {
        val name = "applicationConfig: [classpath:/application.yml]"
        val propertySources = environment.propertySources;

        // get application.yml config source from propertySources
        val propertySource: MapPropertySource? = propertySources[name] as MapPropertySource?

        // get internal values map
        val sourcePropertiesMap: Map<String, Any>? = propertySource?.source


        // sourcePropertiesMap is a java.util.Collections.unmodifiedMap, we must copy all properties to a mutableMap
        var changedPropertiesMap: MutableMap<String, Any?> = HashMap()
        sourcePropertiesMap?.forEach { k, v ->
            changedPropertiesMap[k]=v
        }

        // change property value( change server port to 8022)
        changedPropertiesMap["server.port"] = 8022

        // update property source
        propertySources.replace(name, MapPropertySource(name, changedPropertiesMap))

    }

}
