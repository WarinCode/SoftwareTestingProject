import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.example.Library.*;
import java.time.LocalDate;

public class LoanTest {
    // กำหนดค่า fields
    private Loan loan1 = null;
    private Loan loan2 = null;
    private Book[] books = {
            new Book("book1", "A"),
            new Book("book2", "B")
    };
    private Member[] members = {
            new Member("John"),
            new Member("Jack")
    };

    @Before
    public void setUp(){
        // เพิ่มการยืมหนังสือเข้าไปใน loan1 และ loan2 ก่อนเริ่ม test
        this.loan1 = new Loan(this.books[0], this.members[0]);
        this.loan2 = new Loan(this.books[1], this.members[1]);
    }

    @After
    public void tearDown(){
        // clear ค่า loan ทิ้งเมื่อจบ test
        this.loan1 = null;
        this.loan2 = null;
    }

    @Test
    public void getBook() {
        // ตรวจสอบ object หนังสือว่าใช้ object เดียวกันกับที่ยืมไปไหม
        assertEquals(this.books[0], this.loan1.getBook());
        assertEquals(this.books[1], this.loan2.getBook());
        // หนังสือที่ยืมไปทั้ง 2 เล่มต้องไม่เหมือนกัน
        assertNotEquals(this.loan1.getBook(), this.loan2.getBook());
    }

    @Test
    public void getMember() {
        // ตรวจสอบชื่อสมาชิกว่าชื่อคนที่ยืมตรงกันไหม
        assertEquals(this.members[0], this.loan1.getMember());
        assertEquals(this.members[1], this.loan2.getMember());
        // ชื่อสมาชิกคนที่ยืมหนังสือทั้ง 2 คนต้องไม่ตรงกัน
        assertNotEquals(this.loan1.getMember(), this.loan2.getMember());
    }

    @Test
    public void getBorrowDate() {
        // ตรวจสอบวันที่ยืมตรงกันไหม
        assertEquals(this.loan1.getBorrowDate(), this.loan2.getBorrowDate());
        // ดึงวันที่ปัจจุบันตอนนี้มา
        LocalDate now = LocalDate.now();
        // วันที่ยืมหนังสือตรงกับวันปัจจุบัน
        assertEquals(now, this.loan1.getBorrowDate());
        // วันที่ยืมหนังสือต้องไม่ตรงกับวันที่ในเมื่อวาน
        assertNotEquals(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth() - 1), this.loan1.getBorrowDate());
    }

    @Test
    public void getReturnDate() {
        // ตรวจสอบค่า null ของวันที่คืนหนังสือถ้ายังไม่มีการเรียก method คืนหนังสือโดยค่าเริ่มต้นของวันที่คืนจะเป็น null
        assertNull(this.loan1.getReturnDate());
        assertNull(this.loan2.getReturnDate());
        // คืนหนังสือ
        this.loan1.returnBook();
        // วันที่คืนหนังสือตรงกับวันที่ในปัจจุบัน
        assertEquals(LocalDate.now(), this.loan1.getReturnDate());
        // คืนหนังสือแล้ววันที่คืนต้องไม่เป็นค่า null
        assertNotNull(this.loan1.getReturnDate());
    }

    @Test
    public void returnBook() {
        // ตรวจสอบค่า null ของวันที่คืนหนังสือ
        assertNull(this.loan1.getReturnDate());
        // คืนหนังสือ
        this.loan1.returnBook();
        // คืนหนังสือไปแล้ววันที่คืนต้องไม่ใช้ค่า null
        assertNotNull(this.loan1.getReturnDate());
        this.loan2.returnBook();
        // วันที่คืนตรงกับวันที่ใปัจจุบัน
        assertEquals(LocalDate.now(), this.loan2.getReturnDate());
    }
}