import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.example.Library.Member;
import org.example.Library.Book;
import static org.junit.Assert.*;

public class MemberTest {
    private Member member1 = null;
    private Member member2 = null;
    private List<Book> books = null;

    @Before
    public void setUp(){
        this.member1 = new Member("John");
        this.member2 = new Member("Jane");
        this.books = Arrays.asList(new Book("book1", "A"),
                new Book("book2", "B"),
                new Book("book3", "C"));
    }

    @After
    public void tearDown(){
        this.member1 = null;
        this.member2 = null;
    }

    @Test
    @DisplayName("ทดสอบ method การดึงชื่อ member")
    public void getName() {
        assertEquals("John", this.member1.getName());
        assertNotEquals("Jack", this.member2.getName());
    }

    @Test
    @DisplayName("ทดสอบ method การดึงหนังสือที่ยืมมาทั้งหมด")
    public void getBorrowedBooks() {
        assertEquals(0, this.member1.getBorrowedBooks().size());

        for(int i = 0; i < books.size(); i++){
            this.member2.borrowBook(books.get(i));
        }

        assertEquals(3, this.member2.getBorrowedBooks().size());
        assertArrayEquals(this.books.toArray(), this.member2.getBorrowedBooks().toArray());
        assertThrows(IllegalArgumentException.class, () -> {
           this.member1.borrowBook(null);
        });
    }

    @Test
    @DisplayName("ทดสอบ method การขอยืมหนังสือ")
    public void borrowBook() {
        this.member1.borrowBook(this.books.get(0));
        this.member2.borrowBook(this.books.get(1));
        this.member2.borrowBook(this.books.get(2));
        assertEquals(1, this.member1.getBorrowedBooks().size());
        assertNotEquals(0, this.member2.getBorrowedBooks().size());

        try {
            this.member1.borrowBook(this.books.get(1));
        } catch(IllegalStateException e){
            assertEquals("Book already borrowed", e.getMessage());
        }

        try {
            this.member2.borrowBook(null);
        } catch(IllegalArgumentException e){
            assertEquals("Book cannot be null", e.getMessage());
        }
    }

    @Test
    @DisplayName("ทดสอบ method การคืนหนังสือ")
    public void returnBook() {
        for(int i = 0; i < this.books.size(); i++){
            this.member1.borrowBook(this.books.get(i));
        }
        this.member1.returnBook(this.books.get(0));
        assertEquals(2, this.member1.getBorrowedBooks().size());
        this.member1.returnBook(this.books.get(1));
        this.member1.returnBook(this.books.get(2));
        assertEquals(0, this.member1.getBorrowedBooks().size());
    }
}