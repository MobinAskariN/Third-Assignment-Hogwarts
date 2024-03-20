import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account implements AccountManagement {
    public String username;
    // TODO: Passwords should hashed
    private String password;
    public UUID accountID;
    private List<String> student_comment = new ArrayList<>();
    private List<String> manager_comment = new ArrayList<>();
    private List<String> teacher_comment = new ArrayList<>();
    private List<String> assistant_comment = new ArrayList<>();
    public List<String> courses = new ArrayList<>();



    public Account(String username, String password){
            this.username = username;
            this.password = password;
            this.accountID = UUID.randomUUID();
    }
    public void show_comment(){
        System.out.println("Student comments:");
        for(String i : student_comment)
            System.out.println("\t" + i);
        System.out.println("teacher comments:");
        for(String i : teacher_comment)
            System.out.println("\t" + i);
        System.out.println("assistant comments:");
        for(String i : assistant_comment)
            System.out.println("\t" + i);
        System.out.println("manager comments:");
        for(String i : manager_comment)
            System.out.println("\t" + i);

    }

    public void leave_comment(String obj, String massage){
        if(obj.equals("student"))
            student_comment.add(massage);
        else if(obj.equals("teacher"))
            teacher_comment.add(massage);
        else if(obj.equals("assistant"))
            assistant_comment.add(massage);
        else if(obj.equals("manager"))
            manager_comment.add(massage);

    }
    @Override
    public boolean validatePassword(String enteredPassword) {
        String pattern = "^(?=(?:.*[A-Za-z]){4})(?=(?:.*\\d){2})[A-Za-z0-9@$!%*?&]{6,}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    @Override
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public boolean changePassword(String newPassword) {
        if(validatePassword(newPassword)) {
            this.password = newPassword;
            return true;
        }else
            return false;
    }
    public boolean check_password(String password){
        if(this.password.equals(password))
            return true;
        else
            return false;
    }
}
