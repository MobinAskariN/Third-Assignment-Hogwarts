import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        Hogwarts hogwarts = new Hogwarts();
        hogwarts.fake_user();
        while (true){
            boolean access = false;
            System.out.println("choose your place in school, 1)management  2)assistant  3)teacher  4)student");
            Scanner in = new Scanner(System.in);
            int place = Integer.parseInt(in.nextLine());
            boolean back = false;
            if(place == 1){

                while (!access){
                    System.out.print("enter username (type \"back\" to return to main menu) : ");
                    String username = in.next();
                    if(username.equals("back")) {
                        back = true;
                        break;
                    }
                    System.out.print("enter password : ");
                    String password = in.next();
                    access = hogwarts.manager_login(username, password);
                    if(!access)
                        System.out.println("username or password is wrong, try again");
                }
                if(back)
                    continue;
                System.out.println("known as a manager, give command");
                while (true) {

                    String c = in.next();
                    if (c.equals("add")) {// input : obj, username, password
                        String obj = in.next();
                        if (obj.equals("course"))
                            hogwarts.add_course(in.next());
                        else if(!hogwarts.add(obj, in.next(), in.next()))
                            System.out.println("something went wrong, try again");

                    } else if (c.equals("show")) {
                        hogwarts.show();
                    } else if (c.equals("delete")) {
                        hogwarts.delete(in.next());
                    } else if (c.equals("comment")) {
                        //                 obj      username    message
                        hogwarts.comment(in.next(), in.next(), in.nextLine());
                    } else if (c.equals("course")) {
                        hogwarts.show_courses(in.next());
                    } else if (c.equals("profile")){
                        hogwarts.show_profile(in.next());
                    } else if(c.equals("back")){
                        break;
                    } else
                        System.out.println("command not found");
                    }
            } else if(place == 2){ //   assistant
                Account assistant = new Account("","");
                while (!access){
                    System.out.print("enter username (type \"back\" to return to main menu) : ");
                    String username = in.next();
                    if(username.equals("back")) {
                        back = true;
                        break;
                    }
                    System.out.print("enter password : ");
                    String password = in.next();
                    assistant = hogwarts.assistant_login(username, password);
                    if(assistant == null)
                        access = false;
                    else
                        access = true;
                    if(!access)
                        System.out.println("username or password is wrong, try again");
                }
                if(back)
                    continue;
                System.out.println("known as a assistant, give command");
                while (true) {

                    String c = in.next();
                    if (c.equals("add")) {// input : obj, username, password
                        String obj = in.next();
                        if (obj.equals("course"))
                            hogwarts.add_course(in.next());
                        else if(!hogwarts.add(obj, in.next(), in.next()))
                            System.out.println("something went wrong, try again");

                    } else if (c.equals("show")) {
                        hogwarts.show();
                    } else if (c.equals("delete")) {
                        hogwarts.delete(in.next());
                    } else if (c.equals("comment")) {
                        hogwarts.comment("assistant", in.next(), assistant.username + " : " + in.nextLine());
                    } else if (c.equals("messages")) {
                        assistant.show_comment();
                    } else if (c.equals("course")) {
                        hogwarts.show_courses(in.next());
                    } else if (c.equals("profile")){
                        hogwarts.show_profile(in.next());
                    } else if (c.equals("confirm")) {
                        hogwarts.confirm_teacher();
                    } else if (c.equals("back")){
                        break;
                    } else
                        System.out.println("command not found");
                }

            } else if (place == 3) {//  teacher
                System.out.println("1)register,  2)login");
                if(in.next().equals("1")){
                    System.out.print("enter username (type \"back\" to return to main menu) : ");
                    String username = in.next();
                    if(username.equals("back")) {
                        back = true;
                        break;
                    }
                    System.out.print("enter password : ");
                    String password = in.next();
                    hogwarts.teacher_register(username, password);
                }else {
                    Account teacher = new Account("", "");
                    while (!access) {
                        System.out.print("enter username (type \"back\" to return to main menu) : ");
                        String username = in.next();
                        if(username.equals("back")) {
                            back = true;
                            break;
                        }
                        System.out.print("enter password : ");
                        String password = in.next();
                        teacher = hogwarts.teacher_login(username, password);
                        if (teacher == null)
                            access = false;
                        else
                            access = true;
                        if (!access)
                            System.out.println("username or password is wrong, try again");
                    }
                    if(back)
                        continue;
                    System.out.println("known as a teacher, give command");
                    while (true) {

                        String c = in.next();
                        if (c.equals("register")) {
                            hogwarts.teacher_register_course(in.next(), teacher);
                        } else if (c.equals("course")) {
                            hogwarts.student_courses(teacher);
                        } else if (c.equals("student")) {
                            hogwarts.show_courses(in.next());
                        } else if (c.equals("comment")) {
                            hogwarts.comment("teacher", in.next(), teacher.username + " : " + in.nextLine());
                        } else if (c.equals("messages")) {
                            teacher.show_comment();
                        } else if (c.equals("back")) {
                            break;
                        } else
                            System.out.println("command not found");
                    }
                }

            } else if (place == 4) {
                Account student = new Account("","");
                while (!access){
                    System.out.print("enter username (type \"back\" to return to main menu) : ");
                    String username = in.next();
                    if(username.equals("back")) {
                        back = true;
                        break;
                    }
                    System.out.print("enter password : ");
                    String password = in.next();
                    student = hogwarts.student_login(username, password);
                    if(student == null)
                        access = false;
                    else
                        access = true;
                    if(!access)
                        System.out.println("username or password is wrong, try again");
                }
                if(back)
                    continue;
                System.out.println("known as a student, give command");
                while (true) {

                    String c = in.next();
                    if (c.equals("register")) {
                        hogwarts.student_register_course(in.next(), student);
                    } else if (c.equals("course")) {
                        hogwarts.student_courses(student);
                    } else if (c.equals("teacher")) {
                        hogwarts.student_teachers(student);
                    } else if (c.equals("comment")) {
                        hogwarts.comment("student", in.next(), student.username + " : " + in.nextLine());
                    } else if (c.equals("messages")) {
                        student.show_comment();
                    } else if (c.equals("back")){
                        break;
                    } else
                        System.out.println("command not found");
                }
            }
        }
    }
}
