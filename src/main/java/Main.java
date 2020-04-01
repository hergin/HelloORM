import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        // Connection to the sqlite file. This creates a sample.db file in the root folder.
        var connectionSource = new JdbcConnectionSource("jdbc:sqlite:sample.db");

        // Create a table in the Database if not already exists.
        TableUtils.createTableIfNotExists(connectionSource, Book.class);

        // Dao stands for Data Access Object. We use this to access the table.
        Dao<Book, String> bookDao = DaoManager.createDao(connectionSource, Book.class);

        try {
            for (int i = 0; i < 10; i++) {
                bookDao.create(new Book(i, "Book_" + i, "Ed:" + i, 2000 + i));
            }
        } catch (SQLException sqle) {
        }

        var allBooks = bookDao.queryForAll();

        for (var book : allBooks) {
            System.out.println(book);
        }


        Dao<Student, String> studentDao = DaoManager.createDao(connectionSource, Student.class);

        TableUtils.createTableIfNotExists(connectionSource, Student.class);

        // Since student class has no id, it will add the same entries over and over again if you call these.
        studentDao.create(new Student("Huseyin", "Ergin", "01-01-1986"));
        studentDao.create(new Student("SomeOther", "Student", "01-01-1990"));

        // Print how many entries in this table.
        System.out.println(studentDao.countOf());

        // Train wreck!!!
        var customQuery = studentDao.queryBuilder().where().eq("firstName", "Huseyin").prepare();

        var resultingStudents = studentDao.query(customQuery);

        for (var student : resultingStudents) {
            System.out.println(student);
        }


        var allStudents = studentDao.queryForAll();

        for (var student : allStudents) {
            System.out.println(student);
        }

        connectionSource.close();

    }
}
