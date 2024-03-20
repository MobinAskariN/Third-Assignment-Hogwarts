import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    public String title;
    public String teacher;
    public List<String> students = new ArrayList<>();
    public UUID id;
    public Course(String title, String teacher){
        this.title = title;
        this.teacher = teacher;
        id = UUID.randomUUID();
    }
}
