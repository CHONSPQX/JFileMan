package com.github.jfileman.renderer.impl

import org.custommonkey.xmlunit.Diff
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

import spock.lang.Specification

class TestThymleafHTMLRenderer extends Specification
{
   def "test renderer with title" ()
   {
      setup:
         ClassLoaderTemplateResolver resolver=new ClassLoaderTemplateResolver()
         resolver.setPrefix('com/github/jfileman/renderer/impl/')
         resolver.setSuffix('.html')
         resolver.setTemplateMode('HTML5')
         resolver.setCharacterEncoding('utf-8')
         TemplateEngine templateEngine=new TemplateEngine()
         templateEngine.setTemplateResolver(resolver)
         ThymeleafHTMLRenderer renderer=new ThymeleafHTMLRenderer(templateEngine)
      when:
         def result=renderer.render('test1', ['title':'test title'])
      then:
         new Diff("""
<!DOCTYPE html>
<html>
   <head>
     <meta charset="UTF-8" />
     <title>test title</title>
   </head>
   <body>
      
   </body>
</html>
""", result).identical() 
   }
   def "test renderer without title" ()
   {
      setup:
         ClassLoaderTemplateResolver resolver=new ClassLoaderTemplateResolver()
         resolver.setPrefix('com/github/jfileman/renderer/impl/')
         resolver.setSuffix('.html')
         resolver.setTemplateMode('HTML5')
         resolver.setCharacterEncoding('utf-8')
         TemplateEngine templateEngine=new TemplateEngine()
         templateEngine.setTemplateResolver(resolver)
         ThymeleafHTMLRenderer renderer=new ThymeleafHTMLRenderer(templateEngine)
      when:
         def result=renderer.render('test1', [:])
      then:
         new Diff('''
<!DOCTYPE html>
<html>
   <head>
     <meta charset="UTF-8" />
     <title></title>
   </head>
   <body>
      
   </body>
</html>
''', result).identical() 
   }
}
