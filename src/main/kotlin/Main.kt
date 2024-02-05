import util.DatabaseManager
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

fun main(args: Array<String>) {

    val manager: DatabaseManager = DatabaseManager()

    val createTableRequest = """
        create table if not exists account (
            id serial primary key,
            email varchar(256),
            address varchar(256)); 
        """.trimIndent();

    val insertRequest = """
        insert into account (email, address)
        values ('test0@hse.ru', 'random str.'),
        ('test1@hse.ru', 'random str.'),
        ('test2@hse.ru', 'random2 str.'),
        ('test3@hse.ru', 'random3 str.');
        """.trimIndent()

    val selectRequest = """
        select * from account;
        """.trimIndent()

    val dropTableRequest = """
        DROP TABLE IF EXISTS account;
        """.trimIndent()


    // >_<
//    val selectByAddressRequest = """
//        select * from account
//        where address = '%s'
//        """.trimIndent().format("random str.")


    val selectByAddressRequest = """
        select * from account
        where address = ?
        """.trimIndent()


    //println(manager.performExecute(createTableRequest))
    //println(manager.performExecuteUpdate(insertRequest));
    //val result = manager.performExecuteQuery(selectRequest)
    val result = manager.performExecuteQuery(selectByAddressRequest)
    while (result?.next() == true) {
        println("${result.getLong("id")}  ${result.getString("email")}   ${result.getString("address")}")
    }




}