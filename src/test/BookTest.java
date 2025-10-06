import org.example.Library.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class BookTest {
    private Book book1 = null;
    private Book book2 = null;
    private Book book3 = null;

    @Before
    public void setUp(){
        this.book1 = new Book("book1", "John");
        this.book2 = new Book("book2", "Jane");
        this.book3 = new Book("book3", "Jack");
    }

    @After
    public void tearDown(){
        this.book1.returnBook();
        this.book2.returnBook();
        this.book3.returnBook();
    }

    @Test
    @DisplayName("ทดสอบ method การดึงชื่อหนังสือ")
    public void getTitle() {
        assertEquals("book1", this.book1.getTitle());
        assertEquals("book2", this.book2.getTitle());
        assertEquals("book3", this.book3.getTitle());
    }

    @Test
    @DisplayName("ทดสอบ method การดึงชื่อผู้แต่ง")
    public void getAuthor() {
        assertEquals("John", this.book1.getAuthor());
        assertEquals("Jane", this.book2.getAuthor());
        assertNotEquals("jack", this.book3.getAuthor());
    }

    @Test
    @DisplayName("ทดสอบ method ในการเช็คเงื่อนไขว่าได้ยืมหนังสือหรือยัง")
    public void isBorrowed() {
        assertFalse(this.book1.isBorrowed());
        assertFalse(this.book2.isBorrowed());
        this.book3.borrow();
        assertTrue(this.book3.isBorrowed());
    }

    @Test
    @DisplayName("ทดสอบ method การยืมหนังสือ")
    public void borrow() {
        this.book1.borrow();
        this.book2.borrow();
        this.book3.borrow();
        assertThrows(IllegalStateException.class, () -> {
            this.book3.borrow();
            System.out.println("หนังสือ " + this.book1.getTitle() + " กำลังถูกยืมอยู่ยังไม่สามรถยืมหนังสือเล่มนี้ได้!");
        });

        if (this.book1.isBorrowed()){
            assertTrue(this.book1.isBorrowed());
            System.out.println("หนังสือ " + this.book1.getTitle() + " กำลังถูกยืมอยู่");
        }

        if (this.book2.isBorrowed()){
            assertTrue(this.book2.isBorrowed());
            System.out.println("หนังสือ " + this.book2.getTitle() + " กำลังถูกยืมอยู่");
        }
    }

    @Test
    @DisplayName("ทดสอบ method การคืนหนังสือที่ยืม")
    public void returnBook() {
        this.book1.returnBook();
        this.book2.returnBook();
        this.book3.returnBook();
        assertFalse(this.book1.isBorrowed());
        assertFalse(this.book2.isBorrowed());
        assertFalse(this.book3.isBorrowed());
    }
}