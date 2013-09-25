package com.github.jfileman.renderer.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.github.jfileman.renderer.HTMLRenderer;
import com.github.jfileman.renderer.RendererException;

public class ThymeleafHTMLRenderer implements HTMLRenderer
{
   private TemplateEngine templateEngine;
   
   public ThymeleafHTMLRenderer(TemplateEngine templateEngine)
   {
      this.templateEngine=templateEngine;
   }
   @Override
   public String render(String template, Map<String, ?> context) throws RendererException
   {
      ByteArrayOutputStream out=new ByteArrayOutputStream();
      
      render(template, context, out);
      
      return out.toString();
   }
   @Override
   public void render(String template, Map<String, ?> context, OutputStream out) throws RendererException
   {
      try
      {
         OutputStreamWriter writer=new OutputStreamWriter(out);
         Context ctx=new Context(Locale.getDefault(), context);
         
         templateEngine.process(template, ctx, writer);
         
         writer.flush();
      }
      catch(IOException e)
      {
         throw new RendererException(e);
      }
   }
}
