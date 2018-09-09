package com.andreitop.newco.aspect;


import com.andreitop.newco.dto.TripDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Before("com.andreitop.newco.aspect.PointcutContainer.repositoryFind()")
    public void beforeRepoFind(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
    }


    @AfterReturning("com.andreitop.newco.aspect.PointcutContainer.repositoryUpdate()")
    public void afterReturningRepoSave(JoinPoint joinPoint) {
        TripDto tripDto = (TripDto) (joinPoint.getArgs()[0]);
        logger.info("ASPECT HERE: " + tripDto + " UPDATED");
    }

    @Around("com.andreitop.newco.aspect.PointcutContainer.serviceMethods()")
    public Object aroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("ASPECT HERE: " + joinPoint.getSignature().getName() + " BEFORE");

        Object result = joinPoint.proceed();

        logger.info("ASPECT HERE: " + joinPoint.getSignature().getName() + " AFTER");

        return result;
    }
}
