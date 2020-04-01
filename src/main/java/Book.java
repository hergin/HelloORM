import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {

    // We add this annotation (DatabaseField) to make them persist in the database.
    //   if id is set to true, that means it should be unique and not null.
    // You would receive a SQLiteException if you add a book with same id.
    //   full exception is something like this: org.sqlite.SQLiteException: [SQLITE_CONSTRAINT]  Abort due to constraint violation (UNIQUE constraint failed: books.bookId)
    @DatabaseField(id = true)
    int bookId;

    @DatabaseField()
    String title;

    @DatabaseField()
    String edition;

    // If you don't put this annotation, it won't be in the database.
    int year;

    public Book() {
    }

    public Book(int id, String title, String edition, int year) {
        this.bookId = id;
        this.title = title;
        this.edition = edition;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", year=" + year +
                '}';
    }
}
