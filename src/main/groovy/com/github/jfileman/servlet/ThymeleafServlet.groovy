package com.github.jfileman.servlet;

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.MediaType

import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.WebContext
import org.thymeleaf.exceptions.TemplateInputException
import org.thymeleaf.templateresolver.ServletContextTemplateResolver
import org.thymeleaf.templateresolver.TemplateResolver
import org.xml.sax.SAXParseException;

class ThymeleafServlet extends HttpServlet
{
   private static Logger logger=LoggerFactory.getLogger(ThymeleafServlet)
   private static final long serialVersionUID=9014430175582010882L
   private TemplateResolver templateResolver
   private TemplateEngine templateEngine
   private String contextPath

   @Override
   void init() throws ServletException
   {
      logger.debug('Initializing template rendering')
      this.templateResolver=new ServletContextTemplateResolver()
      this.templateResolver.setPrefix('/WEB-INF/views/')
      this.templateResolver.setSuffix('.html')
      this.templateResolver.setTemplateMode('HTML5')
      this.templateResolver.setCharacterEncoding('utf-8')
      this.templateEngine=new TemplateEngine()
      this.templateEngine.setTemplateResolver(this.templateResolver)
   }
   @Override
   void init(ServletConfig config) throws ServletException
   {
      logger.debug('Initializing servlet context')
      super.init(config)
      this.contextPath=config.getServletContext().getContextPath()
   }
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      PrintWriter writer=response.getWriter()
      String templatePath=FilenameUtils.normalize(StringUtils.substringAfterLast(request.getPathTranslated(), contextPath))
      
      try
      {
         if(templatePath == '/')
         {
            templatePath+='index'
         }
         if(templatePath.startsWith('/'))
         {
            templatePath=templatePath.replaceFirst('/', '')
         }
         
         templateEngine.process(templatePath, new WebContext(request, response, servletContext, Locale.getDefault(), ['title':'JFileMan Login']), writer)
         
         response.setStatus(HttpServletResponse.SC_OK)
         response.setContentType(MediaType.TEXT_HTML)
      }
      catch(TemplateInputException e)
      {
         if(e.getCause() && e.getCause() == SAXParseException)
         {
            logger.error('Error occured loading template', e)
            writer.println('Internal server error occurred')
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.setContentType(MediaType.TEXT_PLAIN)
         }
         else
         {
            logger.debug('Requested template {} not found', templatePath)
            writer.printf('page not found %s\n', templatePath)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            response.setContentType(MediaType.TEXT_PLAIN)
         }
      }
      catch(e)
      {
         logger.error('Error occured loading template', e)
         writer.println('Internal server error occurred')
         response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
         response.setContentType(MediaType.TEXT_PLAIN)
      }
      finally
      {
         writer.flush()
      }
   }
}
