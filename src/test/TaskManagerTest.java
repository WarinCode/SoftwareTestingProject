import org.example.TODOList.Task;
import org.example.TODOList.TaskManager;
import org.example.TODOList.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskManagerTest {
    // ประกาศตัวแปร fields
    User user = new User("user1");
    TaskManager taskmanager = null;

    @Before
    public void setup(){
        // เริ่มต้น test ให้ taskmanager สร้าง instance ของมันขึ้นมา
        taskmanager = new TaskManager();
    }

    @Test
    public void addUser() {
        // เพิ่ม user คนที่ 1 เข้าไป
        taskmanager.addUser(user);
        // หา user คนที่เพิ่มไปใน taskmanager
        assertEquals(user,taskmanager.findUser("user1"));
        try {
            // เกิด exceptin ขึ้นเพราะ user ที่เพิ่มเข้าไปเป็น null
            taskmanager.addUser(null);
        } catch(IllegalArgumentException e){
            // เช็คว่าความ exception ที่เกิดขึ้นตรงกันไหม
            assertEquals("User cannot be null", e.getMessage());
        }
    }

    @Test
    public void findUser() {
        // ตรวจสอบว่าเป็น null หรือไม่ taskmanager ตอนเริ่มเป็นแค่ List เปล่าๆไม่มี user เพราะฉะนั้นค่าที่ออกมาตั้งเป็น null
        assertNull(taskmanager.findUser("user1"));
        // เพิ่ม user เข้าไป
        taskmanager.addUser(user);
        // หา user1 เจอแล้วคเพราะถูกเพิ่มเข้าไปค่าจึงไม่เป็น null แต่เป็ object ของ user1
        assertNotNull(taskmanager.findUser("user1"));
        // ค่าที่ได้ต้องเป็น null เพราะไม่มี user2 ใน taskmanager
        assertNull(taskmanager.findUser("user2"));
    }
}