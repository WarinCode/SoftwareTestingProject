import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.example.Library.Member;
import org.example.Library.Book;
import static org.junit.Assert.*;

public class MemberTest {
    // ประกาศตัวแปร fields และกำหนดค่าเริ่มต้น null
    private Member member1 = null;
    private Member member2 = null;
    private List<Book> books = null;

    @Before
    public void setUp(){
        // กำหนด instance ให้ members และ สร้าง list ใหม่ให้ books ในตอนเริ่ม test
        this.member1 = new Member("John");
        this.member2 = new Member("Jane");
        this.books = Arrays.asList(new Book("book1", "A"),
                new Book("book2", "B"),
                new Book("book3", "C"));
    }

    @After
    public void tearDown(){
        // clear ค่าทิ้งตอนจบ test
        this.member1 = null;
        this.member2 = null;
        this.books = null;
    }

    @Test
    public void getName() {
        // ตรวจสอบชื่อสมาชิก
        assertEquals("John", this.member1.getName());
        assertNotEquals("Jack", this.member2.getName());
    }

    @Test
    public void getBorrowedBooks() {
        // ยังไม่มีการยืมหนังสือของสมาขิกคนที่ 1 หนังสือที่ยืมจึงเป็น 0
        assertEquals(0, this.member1.getBorrowedBooks().size());

        // สมาชิกคนที่ 2 ยืมหนังสือที่มีทั้งหมด
        for(int i = 0; i < books.size(); i++){
            this.member2.borrowBook(books.get(i));
        }

        // ตรวจสอบสมาชิกคนที่ 2 ต้องมีหนังสือที่ขอยืมไปจำนวน 3 เล่ม
        assertEquals(3, this.member2.getBorrowedBooks().size());
        // ตรวจสอบ array ข้อมูลหนังสือที่ยืมไปต้องตรงกันกับใน array หนังสือที่มีทั้งหมด
        assertArrayEquals(this.books.toArray(), this.member2.getBorrowedBooks().toArray());
        // ตรวจสอบถ้ามีการยืมหนังสือที่เป็น null
        assertThrows(IllegalArgumentException.class, () -> {
            // throws exception ออกมาเพราะหนังสือต้องไม่เป็นค่า null
            this.member1.borrowBook(null);
        });
    }

    @Test
    public void borrowBook() {
        // สมาขิกทั้ง 2 คนทำการยืมหนังสือ
        this.member1.borrowBook(this.books.get(0));
        this.member2.borrowBook(this.books.get(1));
        this.member2.borrowBook(this.books.get(2));
        // ตรวจสอบจำนวนหนังสือที่ยืมของสมาขิกคนที่ 1 ต้องมีหนังสือที่ยืมไป 1 เล่ม
        assertEquals(1, this.member1.getBorrowedBooks().size());
        // ตรวจสอบจำนวนหนังสือของสมาชิกคที่ 2 ต้องมีหนังสือที่ยืมไปแน่นอน
        assertNotEquals(0, this.member2.getBorrowedBooks().size());

        try {
            // เกิด exception ขึ้นเพราะหนังสือเล่มที่ 2 ถูกสมาชิกคนที่ 2 ยืมอยู่
            this.member1.borrowBook(this.books.get(1));
        } catch(IllegalStateException e){
            // ตรวจสอบข้อความ exception ที่เกิดขึ้นหนังสือเล่ม 2 กำลังถูกยืมอยู่ไม่สามารถยืมซ้ำได้
            assertEquals("Book already borrowed", e.getMessage());
        }

        try {
            // เกิด exception เพราะหนังสือที่ยืมเป็นค่า null ไม่ได้
            this.member2.borrowBook(null);
        } catch(IllegalArgumentException e){
            // เช็คข้อความ exception เกิดขึ้นว่าตรงกันไหม
            assertEquals("Book cannot be null", e.getMessage());
        }
    }

    @Test
    public void returnBook() {
        // สมาชิกคนที่ 1 ยืมหนังสือที่มีทั้งหมด
        for(int i = 0; i < this.books.size(); i++){
            this.member1.borrowBook(this.books.get(i));
        }
        // สมาชิกคนที่ 1 คืนหนังสือเล่ม 1
        this.member1.returnBook(this.books.get(0));
        // ตรวจสอบจำนวนหนังสือที่ยืมทั้งหมดของสมาชิกคนที่ 1
        assertEquals(2, this.member1.getBorrowedBooks().size());
        // คืนหนังสือทั้งหมด
        this.member1.returnBook(this.books.get(1));
        this.member1.returnBook(this.books.get(2));
        // สมาชิกคนที่ 1 คืนหนังสือไปหมดแล้วทำให้หนังสือที่ยืมเป็น 0 เล่ม
        assertEquals(0, this.member1.getBorrowedBooks().size());
    }
}