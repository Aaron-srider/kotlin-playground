package fit.wenchao.kotlinplayground.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandlerMethodUtils {

    public static List<HandlerMethod> getHandlerMethod(ApplicationContext applicationContext, String fullClassName) {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodMap = requestMappingHandlerMapping
                .getHandlerMethods();
        List<HandlerMethod> result = new ArrayList<>();
        mappingInfoHandlerMethodMap.forEach((requestMappingInfo, handlerMethod) -> {
            String controllerFullClassName = handlerMethod.getMethod().getDeclaringClass().getName();
            if (controllerFullClassName.equals(fullClassName)) {
                result.add(handlerMethod);
            }
        });
        return result;
    }

    public static <T extends Annotation> T getControllerAnnotation(HandlerMethod handlerMethod,
            Class<T> annotationClass) {
        T annotation = handlerMethod.getBeanType().getAnnotation(annotationClass);
        return annotation;
    }

    public static <T extends Annotation> T getControllerAnnotation(Method method, Class<T> annotationClass) {
        Class<?> declaringClass = method.getDeclaringClass();
        T annotation = declaringClass.getAnnotation(annotationClass);
        return annotation;
    }

    // 只支持解析Get,Post,Put,Delete四种请求方法的url
    public static String getMappingUrlFromHandlerMethod(HandlerMethod handlerMethod) {
        Boolean controllerRequestMappingExists = false;
        StringBuilder mappingUrl = new StringBuilder();

        RequestMapping requestMappingAnno = getControllerAnnotation(handlerMethod, RequestMapping.class);
        if (requestMappingAnno != null && requestMappingAnno.value().length != 0) {
            controllerRequestMappingExists = true;
        }

        String requestMappingFirstUrl = null;
        if (controllerRequestMappingExists) {
            requestMappingFirstUrl = requestMappingAnno.value()[0];
        }

        if (requestMappingFirstUrl != null) {
            mappingUrl.append(requestMappingFirstUrl);
        }

        Method apiMethod = handlerMethod.getMethod();

        String mappingUrlFromMethod = HandlerMethodUtils.doGetMappingUrlFromMethod(apiMethod);
        if (mappingUrlFromMethod != null) {
            mappingUrl.append(mappingUrlFromMethod);
        }
        return mappingUrl.toString();
    }

    public static String getMappingUrlFromMethod(Method method) {
        Boolean controllerRequestMappingExists = false;
        StringBuilder mappingUrl = new StringBuilder();

        RequestMapping requestMappingAnno = getControllerAnnotation(method, RequestMapping.class);
        if (requestMappingAnno != null && requestMappingAnno.value().length != 0) {
            controllerRequestMappingExists = true;
        }

        String requestMappingFirstUrl = null;
        if (controllerRequestMappingExists) {
            requestMappingFirstUrl = requestMappingAnno.value()[0];
        }

        if (requestMappingFirstUrl != null) {
            mappingUrl.append(requestMappingFirstUrl);
        }

        String mappingUrlFromMethod = HandlerMethodUtils.doGetMappingUrlFromMethod(method);
        if (mappingUrlFromMethod != null) {
            mappingUrl.append(mappingUrlFromMethod);
        }
        return mappingUrl.toString();
    }

    public static String doGetMappingUrlFromMethod(Method method) {
        RequestMapping requestMappingAnno = method.getAnnotation(RequestMapping.class);
        if (requestMappingAnno != null && requestMappingAnno.value().length != 0
                && supportMethod(requestMappingAnno.method())) {
            // 只获取第一个url
            return requestMappingAnno.value()[0];
        }

        GetMapping getMappingAnno = method.getAnnotation(GetMapping.class);
        if (getMappingAnno != null && getMappingAnno.value().length != 0) {
            // 只获取第一个url
            return getMappingAnno.value()[0];
        }

        PostMapping postMappingAnno = method.getAnnotation(PostMapping.class);
        if (postMappingAnno != null && postMappingAnno.value().length != 0) {
            // 只获取第一个url
            return postMappingAnno.value()[0];
        }

        PutMapping putMappingAnno = method.getAnnotation(PutMapping.class);
        if (putMappingAnno != null && putMappingAnno.value().length != 0) {
            // 只获取第一个url
            return putMappingAnno.value()[0];
        }

        DeleteMapping deleteMappingAnno = method.getAnnotation(DeleteMapping.class);
        if (deleteMappingAnno != null && deleteMappingAnno.value().length != 0) {
            // 只获取第一个url
            return deleteMappingAnno.value()[0];
        }
        return null;
    }

    private static boolean supportMethod(RequestMethod[] method) {
        if (method.length > 1) {
            return false;
        }
        if (method.length < 1) {
            return false;
        }
        if (method[0].equals(RequestMethod.GET) ||
                method[0].equals(RequestMethod.POST) ||
                method[0].equals(RequestMethod.PUT) ||
                method[0].equals(RequestMethod.DELETE)) {
            return true;
        }
        return false;
    }

    public static String getRequestMethod(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        RequestMapping requestMappingAnno = method.getAnnotation(RequestMapping.class);
        if (requestMappingAnno != null && supportMethod(requestMappingAnno.method())) {
            return requestMappingAnno.method()[0].toString().toLowerCase();
        }

        GetMapping getMappingAnno = method.getAnnotation(GetMapping.class);
        if (getMappingAnno != null) {
            return RequestMethod.GET.toString().toLowerCase();
        }

        PostMapping postMappingAnno = method.getAnnotation(PostMapping.class);
        if (postMappingAnno != null) {
            return RequestMethod.POST.toString().toLowerCase();
        }

        PutMapping putMappingAnno = method.getAnnotation(PutMapping.class);
        if (putMappingAnno != null) {
            return RequestMethod.PUT.toString().toLowerCase();
        }

        DeleteMapping deleteMappingAnno = method.getAnnotation(DeleteMapping.class);
        if (deleteMappingAnno != null) {
            return RequestMethod.DELETE.toString().toLowerCase();
        }

        // 默认使用GET请求
        return RequestMethod.GET.toString().toLowerCase();
    }

    public static String getRequestMethod(Method method) {
        RequestMapping requestMappingAnno = method.getAnnotation(RequestMapping.class);
        if (requestMappingAnno != null && supportMethod(requestMappingAnno.method())) {
            return requestMappingAnno.method()[0].toString().toLowerCase();
        }

        GetMapping getMappingAnno = method.getAnnotation(GetMapping.class);
        if (getMappingAnno != null) {
            return RequestMethod.GET.toString().toLowerCase();
        }

        PostMapping postMappingAnno = method.getAnnotation(PostMapping.class);
        if (postMappingAnno != null) {
            return RequestMethod.POST.toString().toLowerCase();
        }

        PutMapping putMappingAnno = method.getAnnotation(PutMapping.class);
        if (putMappingAnno != null) {
            return RequestMethod.PUT.toString().toLowerCase();
        }

        DeleteMapping deleteMappingAnno = method.getAnnotation(DeleteMapping.class);
        if (deleteMappingAnno != null) {
            return RequestMethod.DELETE.toString().toLowerCase();
        }

        // 默认使用GET请求
        return RequestMethod.GET.toString().toLowerCase();
    }

    public static String getApiTag(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        ApiUniqueTag apiUniqueTagAnno = method.getAnnotation(ApiUniqueTag.class);
        if (apiUniqueTagAnno == null) {
            String name = method.getName();
            return name;
        } else {
            return apiUniqueTagAnno.value();
        }
    }
}
