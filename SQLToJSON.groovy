@Grab('mysql:mysql-connector-java:5.1.25')
@GrabConfig(systemClassLoader = true)

import groovy.sql.Sql
import java.sql.SQLRecoverableException
import groovy.json.*
Sql sql = null;
def resultset
try
{
   sql = Sql.newInstance('jdbc:mysql://localhost:3306/groovytests', 'root', 'apollo', 'com.mysql.jdbc.Driver') 
   resultset = sql.rows('select  * from monsters')
}
catch (SQLRecoverableException recoverableEx)
{
   println "SQLRecoverableException: ${recoverableEx}"
   println "\tError Code: ${recoverableEx.getErrorCode()}"
   println "\tSQL Status Code: ${recoverableEx.getSQLState()}"
   System.exit(-1)
}

println new JsonBuilder(resultset).toString()