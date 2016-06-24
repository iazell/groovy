@Grab('mysql:mysql-connector-java:5.1.25')
@GrabConfig(systemClassLoader = true)

import groovy.sql.Sql
import java.sql.SQLRecoverableException
import groovy.xml.MarkupBuilder

Sql sql = null;
try
{
   sql = Sql.newInstance('jdbc:mysql://localhost:3306/groovytests', 'root', 'apollo', 'com.mysql.jdbc.Driver') 
}
catch (SQLRecoverableException recoverableEx)
{
   println "SQLRecoverableException: ${recoverableEx}"
   println "\tError Code: ${recoverableEx.getErrorCode()}"
   println "\tSQL Status Code: ${recoverableEx.getSQLState()}"
   System.exit(-1)
}

def xml = new MarkupBuilder()
xml.Monsters
{
   sql.eachRow("select * from monsters") { row ->
      Characters
      {
         name(row.name)
         age(row.age)
      }
   }
}