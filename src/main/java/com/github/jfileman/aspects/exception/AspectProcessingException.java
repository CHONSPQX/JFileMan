package com.github.jfileman.aspects.exception;

import com.github.jfileman.exception.JFileManException;

public class AspectProcessingException extends JFileManException
{
   private static final long serialVersionUID=5922475190813116208L;
   
   public AspectProcessingException(String message)
   {
      super(message);
   }
   public AspectProcessingException(Throwable cause)
   {
      super(cause);
   }
   public AspectProcessingException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
