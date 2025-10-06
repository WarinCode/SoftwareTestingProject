import org.example.Library.Book;
import org.example.Library.Library;
import org.example.Library.Loan;
import org.example.Library.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class LibraryTest {
    private Library library = null;
    private Book book1 = null;
    private Book book2 = null;
    private Member member = null;

    @Before
    public void setUp(){
        library = new Library("library1");
        book1 = new Book("book1", "John");
        book2 = new Book("book2","Jane");
        member = new Member("Borrowed");
    }

    @Test
    @DisplayName("ทดสอบ method การเพิ่มหนังสือ")
    public void addBook() {
        library.addBook(book1);
        library.addBook(book2);
        assertEquals(2,library.getBooks().size());
    }

    @Test
    @DisplayName("ทดสอบ method การได้รับหนังสือ")
    public void getBooks() {
        assertEquals(0,library.getBooks().size());
    }

    @Test
    @DisplayName("ทดสอบ method การยืมหนังสือ")
    public void borrowBook() {
        //เพื่มหนังสือ
        library.addBook(book1);
        library.addBook(book2);
        //ยืม book1
        member.borrowBook(book1);
        //ยืม book2
        assertNotNull(library.borrowBook("book2",member));
    }

    @Test
    public void returnBook() {
        //เพื่มหนังสือ
        library.addBook(book1);
        library.addBook(book2);
        //ยืม book1
        library.borrowBook("book1",member);
        //คืน book1
        library.returnBook(book1,member);
        // เช็ค จำนวนหนังสือ
        assertEquals(2,library.getAvailableBookCount());

    }

    @Test
    public void getAvailableBookCount() {
        assertEquals(0,library.getAvailableBookCount());
    }
}