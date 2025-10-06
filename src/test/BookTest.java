import org.example.Library.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    // ประกาศ fields ใน class
    private Book book1 = null;
    private Book book2 = null;
    private Book book3 = null;

    @Before
    public void setUp(){
        // กำหนดค่าเริ่มต้นให้แก่ fields ตอนเริ่ม test
        this.book1 = new Book("book1", "John");
        this.book2 = new Book("book2", "Jane");
        this.book3 = new Book("book3", "Jack");
    }

    @After
    public void tearDown(){
        // คืนหนังสือทั้งหมดตอนจบ test
        this.book1.returnBook();
        this.book2.returnBook();
        this.book3.returnBook();
    }

    @Test
    public void getTitle() {
        // ตรวจสอบชื่อของหนังสือว่ตรงกันไหม
        assertEquals("book1", this.book1.getTitle());
        assertEquals("book2", this.book2.getTitle());
        assertEquals("book3", this.book3.getTitle());
    }

    @Test
    public void getAuthor() {
        // ตรวจสอบชื่อผู้แต่งหนังสือ
        assertEquals("John", this.book1.getAuthor());
        assertEquals("Jane", this.book2.getAuthor());
        assertNotEquals("jack", this.book3.getAuthor());
    }

    @Test
    public void isBorrowed() {
        // ตรวจสอบค่าเท็จว่ายืมหนังสือไปหรือยัง
        assertFalse(this.book1.isBorrowed());
        assertFalse(this.book2.isBorrowed());
        // ยืมหนังสือ
        this.book3.borrow();
        // ยืมหนังสือเล่มที่ 3 ไปแล้วค่าต้องเป็น true
        assertTrue(this.book3.isBorrowed());
    }

    @Test
    public void borrow() {
        // ยืมหนังสือทั้ง 3 เล่ม
        this.book1.borrow();
        this.book2.borrow();
        this.book3.borrow();
        // ตรวจสอบว่าถ้า method ด้านใน lambda expression มีการ throws exception ออกมาจะทำการตรวจสอบให้ผ่า
        assertThrows(IllegalStateException.class, () -> {
            // ยืมหนังสือเล่มที่ 3 ซ้ำต้อง throws exception ออกมา
            this.book3.borrow();
            System.out.println("หนังสือ " + this.book1.getTitle() + " กำลังถูกยืมอยู่ยังไม่สามรถยืมหนังสือเล่มนี้ได้!");
        });

        // ยืมหนังสือไปแล้วค่าต้องเป็น true
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
    public void returnBook() {
        // คืหนังสือทั้ง 3 เล่ม
        this.book1.returnBook();
        this.book2.returnBook();
        this.book3.returnBook();
        // ตรวจสอบว่าคืนหนังสือไปแล้วค่าที่ได้ต้องเป็น false
        assertFalse(this.book1.isBorrowed());
        assertFalse(this.book2.isBorrowed());
        assertFalse(this.book3.isBorrowed());
    }
}