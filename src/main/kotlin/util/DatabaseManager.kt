package util

import java.net.URL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class DatabaseManager {
    companion object {
        private const val URL_KEY: String = "db.url"

        // Example of bad practice !
        // private val URL: String = "jdbc:postgresql://localhost:5432/kpo_sem"
        // password
        // username
        // >_<

    }

    // How to get Connection from Drive Manager
    // pass URL and credentials (if needed)
    private fun getConnection() = DriverManager.getConnection(PropertiesUtil.get(URL_KEY))

    // Used for Create/Drop requests
    fun performExecute(sql: String): Boolean {
        val c = getConnection()
        try {
            c.use {
                val query = c.prepareStatement(sql)
                return query.execute()
            }

        } catch (e: Exception) {
            c.close()

            println(e.message)
            return false
        }
    }

    // Used for Select
    fun performExecuteQuery(sql: String): ResultSet? {
        val c = getConnection()
        try {
            c.use {
                val query = c.prepareStatement(sql)

                // Example of using parameters
                //query.setString(1,"random str.");
                return query.executeQuery()
            }

        } catch (e: Exception) {
            c.close()

            println(e.message)
            return null
        }
    }

    // Used for Update
    fun performExecuteUpdate(sql: String): Int {

        val c = getConnection()
        try {
            c.use {
                val query = c.prepareStatement(sql)
                return query.executeUpdate()
            }

        } catch (e: Exception) {
            c.close()

            println(e.message)
            return 1
        }
    }

}