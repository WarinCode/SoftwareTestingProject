import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.example.TODOList.User;
import org.example.TODOList.Task;
import org.junit.jupiter.api.DisplayName;
import java.util.Arrays;
import java.util.List;

public class UserTest {
    private  User user = null;
    private List<Task> tasks = null;

    @Before
    public void setUp(){
        this.user = new User("John");
        this.tasks = Arrays.asList(new Task("a"), new Task("b"), new Task("c"));
        for(int i = 0; i < this.tasks.size(); i++){
            this.user.addTask(this.tasks.get(i));
        }
    }

    @Test
    @DisplayName("")
    public void getName() {
        assertEquals("John", this.user.getName());
        assertNotEquals("john", this.user.getName());
        assertEquals(4, this.user.getName().length());
        assertThrows(IllegalArgumentException.class, () -> {
            new User(null);
        });
    }

    @Test
    public void getTasks() {
        assertEquals(3, this.user.getTasks().size());
    }

    @Test
    public void addTask() {
        Task task1 = new Task("D");
        this.user.addTask(task1);
        assertEquals(4,this.user.getTasks().size());
    }

    @Test
    public void removeTask() {
        assertEquals(3, this.user.getTasks().size());
        this.user.removeTask("a");
        assertEquals(2, this.user.getTasks().size());
        this.user.removeTask("d");
        assertNotEquals(1, this.user.getTasks().size());
        this.user.removeTask("b");
        this.user.removeTask("c");
        assertTrue(this.user.getTasks().size() == 0);
    }
}