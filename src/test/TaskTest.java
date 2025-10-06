import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.example.TODOList.Task;

public class TaskTest {
    private Task task1 = null;
    private Task task2 = null;

    @Before
    public void setUp(){
        this.task1 = new Task("a");
        this.task2 = new Task("b");
    }

    @After
    public void tearDown(){
        this.task1 = null;
        this.task2 = null;
    }

    @Test
    public void Task(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("");
        });
    }

    @Test
    @DisplayName("ทดสอบ method ในการดึงชื่อ task")
    public void getTitle() {
        assertEquals("a", this.task1.getTitle());
        assertEquals("b", this.task2.getTitle());
    }

    @Test
    @DisplayName("ทดสอบ method ในการเช็คว่า task นั้นทำเสร็จหรือยัง")
    public void isDone() {
        assertFalse(this.task1.isDone());
        assertFalse(this.task2.isDone());
        this.task1.markDone();
        assertTrue(this.task1.isDone());
    }

    @Test
    @DisplayName("ทดสอบ method ในการมารค์ว่าทำงาน task เสร็จเรียบร้อยแล้ว")
    public void markDone() {
        this.task1.markDone();
        this.task2.markDone();
        assertTrue(this.task1.isDone());
        assertTrue(this.task2.isDone());
    }

    @Test
    @DisplayName("ทดสอบ method ในการเลิกมารค์ว่า task ยังทำงาไม่เสร็จ")
    public void markUndone() {
        assertFalse(this.task1.isDone());
        this.task1.markDone();
        assertTrue(this.task1.isDone());
    }
}