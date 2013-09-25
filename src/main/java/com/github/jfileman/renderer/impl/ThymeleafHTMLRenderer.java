package com.github.jfileman.renderer.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

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
      StringWriter writer=new StringWriter();
      
      render(template, context, writer);
      
      return writer.toString();
   }
   @Override
   public void render(String template, Map<String, ?> context, OutputStream out) throws RendererException
   {
      try
      {
         OutputStreamWriter writer=new OutputStreamWriter(out);
         
         render(template, context, writer);
         
         writer.flush();
      }
      catch(IOException e)
      {
         throw new RendererException(e);
      }
   }
   @Override
   public void render(String template, Map<String, ?> context, Writer writer) throws RendererException
   {
      templateEngine.process(template, new Context(Locale.getDefault(), context), writer);
   }
}
