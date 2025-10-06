import org.example.Library.Book;
import org.example.Library.Library;
import org.example.Library.Member;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    // ประกาศ fields และมีค่าเริ่มต้นให้เป็น null
    private Library library = null;
    private Book book1 = null;
    private Book book2 = null;
    private Member member = null;

    @Before
    public void setUp(){
        // กำหนดค่าเริ่มต้นให้พวก fields ก่อนเริ่ม test
        library = new Library("library1");
        book1 = new Book("book1", "John");
        book2 = new Book("book2","Jane");
        member = new Member("Borrowed");
    }

    @Test
    public void addBook() {
        // เพิ่มหนังสือเล่มที่ 1 และ 2 เข้าไปใน library
        library.addBook(book1);
        library.addBook(book2);
        // ตรวจสอบว่าหนังสือที่เพิ่มเข้าไปใน library ต้องมี 2 เล่ม
        assertEquals(2, library.getBooks().size());
    }

    @Test
    public void getBooks() {
        // ตรวจสอบหนังสือใน library ตอนเริ่มต้นจะไม่มีหนังสือใน library เลย
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void borrowBook() {
        // เพื่มหนังสือ
        library.addBook(book1);
        library.addBook(book2);
        // ยืม book1
        member.borrowBook(book1);
        // ยืม book2
        assertNotNull(library.borrowBook("book2", member));
    }

    @Test
    public void returnBook() {
        // เพื่มหนังสือ
        library.addBook(book1);
        library.addBook(book2);
        // ยืม book1
        library.borrowBook("book1",member);
        // คืน book1
        library.returnBook(book1,member);
        // เช็คจำนวนหนังสือ
        assertEquals(2, library.getAvailableBookCount());

    }

    @Test
    public void getAvailableBookCount() {
        // ตรวจสอบหังสือใ library ที่ใช้งาได้ตอนเริ่มต้นจะมีแค่ 0 เล่ม
        assertEquals(0, library.getAvailableBookCount());
        // เพิ่มหนังสือเข้าไป 2 เล่ม
        library.addBook(new Book("book1", "A"));
        library.addBook(new Book("book2", "B"));
        // ตรวจสอบจำนวนหนังสือใน library ต้องมีหนังสือแน่นอน
        assertNotEquals(0, library.getAvailableBookCount());
    }
}