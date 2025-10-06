import org.example.TODOList.Task;
import org.example.TODOList.TaskManager;
import org.example.TODOList.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskManagerTest {

    User user = new User("user1");
    TaskManager taskmanager  = null;
    @Before
    public void setup(){
        taskmanager = new TaskManager();

    }

    @Test
    public void addUser() {
        taskmanager.addUser(user);
        assertEquals(user,taskmanager.findUser("user1"));

    }

    @Test
    public void findUser() {
        assertNull(null,taskmanager.findUser("user1"));
    }
}