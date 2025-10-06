import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;
import org.example.Library.*;
import java.time.LocalDate;

public class LoanTest {
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
        this.loan1 = new Loan(this.books[0], this.members[0]);
        this.loan2 = new Loan(this.books[1], this.members[1]);
    }

    @After
    public void tearDown(){
        this.loan1 = null;
        this.loan2 = null;
    }

    @Test
    @DisplayName("ทดสอบ method การดึงข้อมูลหนังสือ")
    public void getBook() {
        assertEquals(this.books[0], this.loan1.getBook());
        assertEquals(this.books[1], this.loan2.getBook());
        assertNotEquals(this.loan1.getBook(), this.loan2.getBook());
    }

    @Test
    @DisplayName("ทดสอบ method การดึงข้อมูลสมาชิก")
    public void getMember() {
        assertEquals(this.members[0], this.loan1.getMember());
        assertEquals(this.members[1], this.loan2.getMember());
        assertNotEquals(this.loan1.getMember(), this.loan2.getMember());
    }

    @Test
    @DisplayName("ทดสอบ method การขอวันที่ขอยืมหนังสือ")
    public void getBorrowDate() {
        assertEquals(this.loan1.getBorrowDate(), this.loan2.getBorrowDate());
        LocalDate now = LocalDate.now();
        assertEquals(now, this.loan1.getBorrowDate());
        assertNotEquals(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth() - 1), this.loan1.getBorrowDate());
    }

    @Test
    @DisplayName("ทดสอบ method วันที่คืนหนังสือ")
    public void getReturnDate() {
        assertNull(this.loan1.getReturnDate());
        assertNull(this.loan2.getReturnDate());
        this.loan1.returnBook();
        assertEquals(LocalDate.now(), this.loan1.getReturnDate());
        assertNotNull(this.loan1.getReturnDate());
    }

    @Test
    @DisplayName("ทดสอบ method การคืนหนังสือ")
    public void returnBook() {
        assertNull(this.loan1.getReturnDate());
        this.loan1.returnBook();
        assertNotNull(this.loan1.getReturnDate());
        this.loan2.returnBook();
        assertEquals(LocalDate.now(), this.loan2.getReturnDate());
    }
}