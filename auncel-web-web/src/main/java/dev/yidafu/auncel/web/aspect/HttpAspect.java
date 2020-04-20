package dev.yidafu.auncel.web.aspect;


import dev.yidafu.auncel.web.controller.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    public static Log logger = LogFactory.getLog(UserController.class);

    @Before("execution(public * dev.yidafu.auncel.web.controller.*.*(..))")
    public void log(JoinPoint joinPoint) {
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("==> " + request.getMethod() + " : " + request.getRequestURL());
        logger.info("==> class: " + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName()
                + "(" + joinPoint.getArgs() + ")"
        );
    }
}
