package com.njkgkj.core.mvc.handler;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Created by zhangkaihua on 2017/7/3.
 */
public class AppRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    public RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        RequestMapping methodAnnotation = (RequestMapping) AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createRequestMappingInfo(methodAnnotation, methodCondition);

            RequestMapping typeAnnotation = (RequestMapping) AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createRequestMappingInfo(typeAnnotation, typeCondition).combine(info);
            }
        } else {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createMethodRequestMappingInfo(method, methodCondition);
            if (info != null) {
                RequestMapping typeAnnotation = (RequestMapping) AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
                if (typeAnnotation != null) {
                    RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                    info = createRequestMappingInfo(typeAnnotation, typeCondition).combine(info);
                }
            }
        }
        return info;
    }

    protected RequestMappingInfo createMethodRequestMappingInfo(Method method, RequestCondition<?> customCondition) {
        if (method != null) {
            if (method.getModifiers() != 1) {
                return null;
            }
            String methodUrl = method.getName();
            if (!methodUrl.startsWith("/")) {
                methodUrl = "/" + methodUrl;
                String[] patterns = new String[1];
                patterns[0] = methodUrl;

                RequestMethod[] methods = new RequestMethod[0];
                String[] params = new String[0];
                String[] headers = new String[0];
                String[] consumes = new String[0];
                String[] produces = new String[0];

                return new RequestMappingInfo(new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(), useTrailingSlashMatch(), getFileExtensions()),
                        new RequestMethodsRequestCondition(methods), new ParamsRequestCondition(params), new HeadersRequestCondition(headers), new ConsumesRequestCondition(consumes, headers),
                        new ProducesRequestCondition(produces, headers, getContentNegotiationManager()), customCondition);
            }
        }
        return null;
    }


}
