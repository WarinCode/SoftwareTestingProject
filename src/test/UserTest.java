import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.example.TODOList.User;
import org.example.TODOList.Task;
import java.util.Arrays;
import java.util.List;

public class UserTest {
    // ประกาศ fields ใน class และ กำหนดค่าเริ่มต้นให้เป็น null
    private User user = null;
    private List<Task> tasks = null;

    @Before
    public void setUp(){
        // กำหนดค่า fields ให้ user และ tasks
        this.user = new User("John");
        this.tasks = Arrays.asList(new Task("a"), new Task("b"), new Task("c"));
        // ให้ user เพิ่มงาน tasks ทั้งหมดเข้าไปในตอนเริ่ม test
        for(int i = 0; i < this.tasks.size(); i++){
            this.user.addTask(this.tasks.get(i));
        }
    }

    @After
    public void tearDown(){
        // clear ค่าตอนจบ test
        this.user = null;
        this.tasks = null;
    }

    @Test
    public void getName() {
        // ตรวจสอบชื่อผู้ใช้งาน
        assertEquals("John", this.user.getName());
        assertNotEquals("john", this.user.getName());
        // ตรวจสอบความยาวของชื่อผู้ใช้งานต้องเป็น 4 ตัวอักษร
        assertEquals(4, this.user.getName().length());
        // ตรวจสอบ exception ที่เกิดจากการสร้าง object ของ user
        assertThrows(IllegalArgumentException.class, () -> {
            // ชื่อผู้ใช้งานเป็นค่า null ไม่ได้มีการโยน exception ออกมา
            new User(null);
        });
    }

    @Test
    public void getTasks() {
        // ตรวจสอบจำนวนงานทั้งหมดของ user ตอนเริ่มต้นต้องไม่ใช้ 0 task
        assertNotEquals(0, this.user.getTasks().size());
        // ตรวจสอบจำนวนงานทั้งหมดของ user ตอนเริ่มต้นต้องมี 3 task
        assertEquals(3, this.user.getTasks().size());
    }

    @Test
    public void addTask() {
        // สร้าง task D
        Task task1 = new Task("D");
        // เพิ่ม task ใหม่เข้าไป
        this.user.addTask(task1);
        // จำนวน task ของ user เพิ่มขึ้น 1 จำนวนจาก 3 task จึงกลายเป็น 4
        assertEquals(4, this.user.getTasks().size());
        try {
            // เกิด exception ขึ้นเพราะเพิ่ม task ที่เป็นค่า null ไม่ได้
            this.user.addTask(null);
        } catch(IllegalArgumentException e){
            // เช็คข้อความ exception ว่าตรงกันไหม
            assertEquals("Task cannot be null", e.getMessage());
        }
        // ตรวจสอบจำนวน task จะไม่ถูกเพิ่มเข้าไปเป็น 5 task เพราะเกิด exception ก่อนถูกเพิ่มเข้าไป
        assertNotEquals(5, this.user.getTasks().size());
    }

    @Test
    public void removeTask() {
        // ตรวจสอบจำนวน task ของ user ต้องมี 3 จำนวน
        assertEquals(3, this.user.getTasks().size());
        // ลบ task a ออกจาก user
        this.user.removeTask("a");
        // ตรวจสอบจำนวน task ต้องลดลง 1 เพราะพึ่งลบ task a ไป
        assertEquals(2, this.user.getTasks().size());
        // ลบ task d ที่ไม่มีใน user ไม่มีอะไรเกิดขึ้น
        this.user.removeTask("d");
        // ตรวจสอบจำนวน task ยังมีจำนวนเท่าเดิมไม่มีการลดของจำนวน task ลงเพราะ task d ไม่มีอยู่ใน user
        assertNotEquals(1, this.user.getTasks().size());
        // ลบ task b และ c
        this.user.removeTask("b");
        this.user.removeTask("c");
        // จำนวน task ทั้งหมดของ user ต้องเป็น 0 เพราะลบ task ออกทั้งหมดแล้ว
        assertTrue(this.user.getTasks().size() == 0);
    }
}