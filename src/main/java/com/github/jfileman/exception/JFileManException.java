package com.github.jfileman.exception;

public class JFileManException extends RuntimeException
{
   private static final long serialVersionUID = -8751515514936296243L;

   public JFileManException(String message)
   {
      super(message);
   }
   public JFileManException(Throwable cause)
   {
      super(cause);
   }
   public JFileManException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
