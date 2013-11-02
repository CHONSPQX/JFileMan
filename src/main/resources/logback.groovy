import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

def logFileHome="logs/"

if(System.properties['jfileman.logging.home']) //override container specific logging
{
   logFileHome=System.properties['jfileman.logging.home'] + '/' + logFileHome
}
if(System.properties['jetty.home']) //put logging in Jetty's logs directory
{
   logFileHome=System.properties['jetty.home'] + '/' + logFileHome
}
else if(System.properties['catalina.home']) //put logging in Tomcat's logs directory
{
   logFileHome=System.properties['catalina.home'] + '/' + logFileHome
}

logger('org.springframework', INFO)
logger('org.thymeleaf', ERROR)

appender('FILE', RollingFileAppender) {
   file=logFileHome + '/JFileMan.log'
   append=true
   encoder(PatternLayoutEncoder) {
      pattern='%-6p %c {%d} %t-&gt; %m%n'
   }
   rollingPolicy(TimeBasedRollingPolicy) {
      FileNamePattern=logFileHome + '/JFileMan.%d{yyyy-MM-dd}.log'
   }
}
appender('STDOUT', ConsoleAppender) {
   encoder(PatternLayoutEncoder) {
     pattern = '%-6p %c %m%n'
   }
}

root(DEBUG, ['STDOUT'])