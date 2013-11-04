package com.github.jfileman.aspects.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.jfileman.aspects.exception.AspectProcessingException;

@Aspect
@Component
public class FileLoggingService
{
   private static Logger logger=LoggerFactory.getLogger(FileLoggingService.class);
   
   @Around("com.github.jfileman.service.FileService.listFiles()")
   public Object doListFiles(ProceedingJoinPoint joinPoint)
   {
      try
      {
         for(Object arg : joinPoint.getArgs())
         {
            logger.debug("calling method {} with {}", joinPoint.getSignature().getName(), arg);
         }
         
         Object result=joinPoint.proceed();
         
         logger.debug("Result of {} is {}", joinPoint.getSignature().getName(), result);
         
         return result;
      }
      catch(Throwable e)
      {
         logger.error("Unable to proceed with operation", e);
         throw new AspectProcessingException("Error occurred during method processing: " + joinPoint.getSignature().getName(), e);
      }
   }
}
