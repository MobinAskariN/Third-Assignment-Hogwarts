import java.util.*;

public class Hogwarts {

    private List<Account> students = new ArrayList<Account>();
    private List<Account> teachers = new ArrayList<Account>();
    private List<Account> assistants = new ArrayList<Account>();
    private List<Course> courses = new ArrayList<>();
    private List<Account> confirm_teacher_list = new ArrayList<Account>();






    public void fake_user(){
        Account a = new Account("mobin", "1234");
        students.add(a);
        a = new Account("mamad", "1234");
        students.add(a);
        courses.add(new Course("math", ""));
        student_register_course("math", a);
        a = new Account("ahmad", "1234");
        teachers.add(a);
        teacher_register_course("math", a);
        a = new Account("assistant", "1234");
        assistants.add(a);

    }
    public boolean comment(String obj, String username, String massage){
        for(Account i : students)
            if(i.username.equals(username)) {
                i.leave_comment(obj, massage);
                System.out.println("successful !");
                return true;
            }
        for(Account i : teachers)
            if(i.username.equals(username)) {
                i.leave_comment(obj, massage);
                System.out.println("successful !");
                return true;
            }
        for(Account i : assistants)
            if(i.username.equals(username)) {
                i.leave_comment(obj, massage);
                System.out.println("successful !");
                return true;
            }
        System.out.println("user not found");
        return false;
    }
    public void delete(String username){
        for(Account i : students)
            if(i.username.equals(username)) {
                students.remove(i);
                System.out.println("successful !");
                return;
            }
        for(Account i : teachers)
            if(i.username.equals(username)) {
                teachers.remove(i);
                System.out.println("successful !");
                return;
            }
        for(Account i : assistants)
            if(i.username.equals(username)) {
                assistants.remove(i);
                System.out.println("successful !");
                return;
            }
        System.out.println("user not found");
    }
    public boolean add(String obj, String username, String password){
        Account a = new Account(username, password);
        for(Account i : students)
            if(i.username.equals(username))
                return false;
        for(Account i : teachers)
            if(i.username.equals(username))
                return false;
        for(Account i : assistants)
            if(i.username.equals(username))
                return false;

        if(obj.equals("student"))
            students.add(a);
        else if(obj.equals("teacher"))
            teachers.add(a);
        else if(obj.equals("assistant"))
            assistants.add(a);
        else
            return false;
        System.out.println("successful !");
        return true;
    }
    public void add_course(String title){
        courses.add(new Course(title, ""));
        System.out.println("successful !");
    }

    public boolean manager_login(String username, String password){
        if(username.equals("manager") && password.equals("1234"))
            return true;
        else
            return false;
    }
    public Account assistant_login(String username, String password){
        for(Account i : assistants)
            if(i.username.equals(username) && i.check_password(password))
                return i;
        return null;
    }
    public Account student_login(String username, String password){
        for(Account i : students)
            if(i.username.equals(username) && i.check_password(password))
                return i;
        return null;
    }
    public Account teacher_login(String username, String password){
        for(Account i : teachers)
            if(i.username.equals(username) && i.check_password(password))
                return i;
        for(Account i : confirm_teacher_list)
            if(i.username.equals(username))
                System.out.println("you have requested for account and its not accepted yet");

        return null;
    }
    public void teacher_register(String username, String password){
        Account a = new Account(username, password);
        for(Account i : students)
            if(i.username.equals(username)) {
                System.out.println("choose another username");
                return;
            }
        for(Account i : teachers)
            if(i.username.equals(username)) {
                System.out.println("choose another username");
                return;
            }
        for(Account i : assistants)
            if(i.username.equals(username)) {
                System.out.println("choose another username");
                return;
            }
        System.out.println("your request has been sent, wait for assistant configuration");
        confirm_teacher_list.add(a);
    }

    public void confirm_teacher(){
        System.out.println("list of teachers waiting to be accepted :");
        for(Account i : confirm_teacher_list)
            System.out.println("\t" + i.username);
        Scanner in = new Scanner(System.in);
        while (true){
            String action = in.next();
            if (action.equals("accept")){
                String username = in.next();
                boolean find = false;
                for(Account i : confirm_teacher_list)
                    if(i.username.equals(username)){
                        teachers.add(i);
                        confirm_teacher_list.remove(i);
                        System.out.println("successful !");
                        find = true;
                        break;
                    }
                if(!find)
                    System.out.println("username not found");
            } else if (action.equals("deny")){
                String username = in.next();
                boolean find = false;
                for(Account i : confirm_teacher_list)
                    if(i.username.equals(username)){
                        confirm_teacher_list.remove(i);
                        System.out.println("successful !");
                        find = true;
                        break;
                    }
                if(!find)
                    System.out.println("username not found");
            } else if(action.equals("exit")) {
                System.out.println("back to main menu");
                return;
            }
        }
    }
    public void assistant_signup(Account new_account){
        assistants.add(new_account);
    }
    public void show() {
        System.out.println("list of teachers :");
        for(Account i : teachers)
            System.out.println("\tusername : " + i.username);
        System.out.println("list of students :");
        for(Account i : students)
            System.out.println("\tusername : " + i.username);
        System.out.println("list of assistants :");
        for(Account i : assistants)
            System.out.println("\tusername : " + i.username);
        System.out.println("list of courses :");
        for(Course i : courses)
            System.out.println("\ttitle : " + i.title);

    }

    public void show_courses(String title) {
        boolean cond = false;
        if(title.equals("all")){
            System.out.println("list of courses : ");
            for(Course i : courses)
                System.out.println("\t" + i.title);
        }else{
            for(Course i : courses)
                if(i.title.equals(title)){
                    cond = true;
                    System.out.println("title : " + i.title + "  ,teacher : " + i.teacher);
                    System.out.println("students : ");
                    for(String j : i.students)
                        System.out.println("\t" + j);
                }
            if(!cond)
                System.out.println("course not found");
        }

    }
    public void show_profile(String username){
        for(Account i : students)
            if(i.username.equals(username)){
                System.out.println("username : " + i.username);
                System.out.println("courses registered : ");
                for (String s : i.courses)
                    System.out.println("\t" + s);
                return;
            }
        for(Account i : teachers)
            if(i.username.equals(username)){
                System.out.println("username : " + i.username);
                System.out.println("courses registered : ");
                for (String s : i.courses)
                    System.out.println("\t" + s);
                return;
            }
        System.out.println("student or teacher not found");
    }
    public boolean student_register_course(String title, Account student){
        for(Course i : courses)
            if(i.title.equals(title)){
                for(String s : i.students)
                    if(s.equals(student.username)){
                        System.out.println("you have already registered in this course");
                        return false;
                    }
                i.students.add(student.username);
                student.courses.add(title);
                System.out.println("successful !");
                return true;
            }
        System.out.println("course not found");
        return false;
    }
    public boolean teacher_register_course(String title, Account teacher){
        for(Course i : courses)
            if(i.title.equals(title)){
                if(!i.teacher.equals("")){
                    System.out.println("course already has teacher");
                    return false;
                }
                i.teacher = teacher.username;
                teacher.courses.add(title);
                System.out.println("successful !");
                return true;
            }
        System.out.println("course not found");
        return false;
    }

    public void student_courses(Account student){
        System.out.println("courses taken :");
        for(String s : student.courses)
            System.out.println("\t" + s);
    }
    public void student_teachers(Account student){
        System.out.println("list of teachers you have class with :");
        for(String s : student.courses)
            for(Course c : courses)
                if(s.equals(c.title))
                    System.out.println("\t" + c.teacher);
    }
}
