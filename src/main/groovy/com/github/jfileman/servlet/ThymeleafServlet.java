package com.github.jfileman.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.github.jfileman.renderer.HTMLRenderer;
import com.github.jfileman.renderer.impl.ThymeleafHTMLRenderer;

public class ThymeleafServlet extends HttpServlet
{
   private static Logger logger=LoggerFactory.getLogger(ThymeleafServlet.class);
   private static final long serialVersionUID = 9014430175582010882L;
   private TemplateResolver templateResolver;
   private TemplateEngine templateEngine;
   private HTMLRenderer renderer;
   private String contextPath;

   @Override
   public void init() throws ServletException
   {
      logger.debug("Initializing template rendering");
      this.templateResolver=new ServletContextTemplateResolver();
      this.templateResolver.setPrefix("/WEB-INF/views");
      this.templateResolver.setSuffix(".html");
      this.templateResolver.setTemplateMode("HTML5");
      this.templateResolver.setCacheable(true);
      this.templateResolver.setCacheTTLMs(60000L);
      this.templateResolver.setCharacterEncoding("utf-8");
      this.templateEngine=new TemplateEngine();
      this.templateEngine.setTemplateResolver(this.templateResolver);
      this.renderer=new ThymeleafHTMLRenderer(this.templateEngine);
   }
   @Override
   public void init(ServletConfig config) throws ServletException
   {
      logger.debug("Initializing servlet context");
      super.init(config);
      this.contextPath=config.getServletContext().getContextPath();
   }
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      super.doGet(request, response);
      PrintWriter writer=response.getWriter();
      
      writer.println(request.getPathTranslated());
      
      response.setStatus(HttpServletResponse.SC_OK);
      response.setContentType("text/plain");
      writer.flush();
   }
}
