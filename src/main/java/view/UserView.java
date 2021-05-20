package view;

import controller.UserController;
import model.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView implements View {

    private Scanner in = new Scanner(System.in);
    private UserController userController = new UserController();
    private View startView = new StartView();

    @Override
    public void doChoise() throws SQLException, ClassNotFoundException {
        long idIn;
        String firstNameIn;
        String lastNameIn;
        long regionIdIn;
        String regionNameIn;
        System.out.println("1-Create user\n2-Get user by id\n3-Get all users\n" +
                "4-Change record\n5-Delete user\n6-Exit");
        do {
            int run = in.nextInt();
            switch (run) {
                case 1:
                    System.out.println("Create user");
                    System.out.println("Enter the firstname");
                    firstNameIn = in.next();
                    System.out.println("Enter the lasttname");
                    lastNameIn = in.next();
                    System.out.println("Enter the region name");
                    regionNameIn = in.next();
                    System.out.println(userController.checkSaveController(firstNameIn, lastNameIn, regionNameIn));
                    doChoise();

                case 2:
                    System.out.println("Get user by id\nEnter the id:");
                    idIn = in.nextLong();
                    User userById = userController.checkGetByIdController(idIn);
                    if(userById == null){
                        printNoSuchElement();
                    }else {
                        System.out.println(userById);
                    }
                    doChoise();
                case 3:
                    System.out.println("Get all users");
                    List<User> allUsers = userController.checkGetAllController();
                    if (allUsers == null) {
                        System.out.println("The list of users is empty");
                    } else {
                        allUsers.stream().forEach(x -> System.out.println(x));
                    }

                    doChoise();
                case 4:
                    System.out.println("Change record");
                    System.out.println("Enter the id of user to change his");
                    idIn = in.nextLong();
                    System.out.println("Enter the new firstname");
                    firstNameIn = in.next();
                    System.out.println("Enter the new lastname");
                    lastNameIn = in.next();
                    System.out.println("Enter the new regionname");
                    regionNameIn = in.next();
                    User userUpdate = userController.checkUpdateController(idIn, firstNameIn, lastNameIn, regionNameIn);
                    if(userUpdate != null){
                        System.out.println(userUpdate);
                    }else {
                        printNoSuchElement();
                    }
                    doChoise();
                case 5:
                    System.out.println("Delete user\nEnter the id of user to delete his");
                    idIn = in.nextLong();
                    userController.checkDeleteByIdController(idIn);
                    doChoise();
                case 6:
                    System.out.println("Exit to main menu");
                    startView.doChoise();

            }
        }while (true);
    }
    public void printNoSuchElement(){
        System.out.println("User whith this <id> is not exist");
    }
}
