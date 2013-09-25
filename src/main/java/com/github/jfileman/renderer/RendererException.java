package com.github.jfileman.renderer;

import com.github.jfileman.exception.JFileManException;

public class RendererException extends JFileManException
{
   private static final long serialVersionUID = 2611282838239931588L;

   public RendererException(String message)
   {
      super(message);
   }
   public RendererException(Throwable cause)
   {
      super(cause);
   }
   public RendererException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
