import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.example.TODOList.Task;

public class TaskTest {
    // ประกาศตัวแปร fields และ กำหนดค่าเริ่มต้นให้เป็น null
    private Task task1 = null;
    private Task task2 = null;

    @Before
    public void setUp(){
        // สร้าง instances ให้ fields ตอนเริ่ม test
        this.task1 = new Task("a");
        this.task2 = new Task("b");
    }

    @After
    public void tearDown(){
        // clear ค่า fields ทั้งหมดทิ้ง
        this.task1 = null;
        this.task2 = null;
    }

    @Test
    public void Task(){
        // ตรวจสอบการสร้าง constructor ของ task ว่าจะมีการ throws exception อะไรออกมาไหม
        assertThrows(IllegalArgumentException.class, () -> {
            // เกิด exception ค่า argument ที่ส่งเข้าไปต้องไม่เป็นค่า null จึงมีการโยน exception ออกมา
            new Task(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            // ชื่อ task ต้องไม่เป็นค่าว่างมีการโยน exception ออกมา
            new Task("");
        });
    }

    @Test
    public void getTitle() {
        // ครวจสอบชื่อ task ว่าตรงกันไหม
        assertEquals("a", this.task1.getTitle());
        assertEquals("b", this.task2.getTitle());
        assertNotEquals("B", this.task2.getTitle());
    }

    @Test
    public void isDone() {
        // ตรวจสอบว่า task ทำงานเสร็จหรือยัง
        // task ตอนเริ่มต้นนั้นเป็น false งานยังไม่เสร็จค่าจึงเป็น false
        assertFalse(this.task1.isDone());
        assertFalse(this.task2.isDone());
        // task 1 ทำงานเสร็จแล้วค่าเป็น true
        this.task1.markDone();
        // ค่าต้องเป็น true เพราะ task 1 ทำงานเสร็จแล้ว
        assertTrue(this.task1.isDone());
    }

    @Test
    public void markDone() {
        // task 1 และ 2 ทำงานเสร็จแล้ว
        this.task1.markDone();
        this.task2.markDone();
        // ค่า task ที่ได้ต้องเป็น true ทั้งหมด
        assertTrue(this.task1.isDone());
        assertTrue(this.task2.isDone());
        // task 2 ต้องไม่เป็นค่า false
        assertNotEquals(false, this.task2.isDone());
    }

    @Test
    public void markUndone() {
        // task 1 ยังทำงานไม่เสร็จค่าจึงเป็น false
        assertFalse(this.task1.isDone());
        // task 1 ทำงาเสร็จแล้ว
        this.task1.markDone();
        // ตรวจสอบ task 1 ค่าต้องเป็น true เพราะงานเสร็จแล้ว
        assertTrue(this.task1.isDone());
        // เปลี่ยนค่า task 1 ว่าทำงายังไม่เสร็จ
        this.task1.markUndone();
        // ตรวจสอบค่า task 1 ใหม่ค่าที่ได้ต้องเป็น false
        assertFalse(this.task1.isDone());
    }
}