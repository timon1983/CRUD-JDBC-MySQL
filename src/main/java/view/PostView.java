package view;

import controller.PostControllerImpl;
import model.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PostView implements View{

    private Scanner in = new Scanner(System.in);
    private PostControllerImpl postController = new PostControllerImpl();
    private View startView = new StartView();

    @Override
    public void doChoise() throws SQLException, ClassNotFoundException {

        long idIn;
        String contentIn;
        long createdIn;
        long updatedIn;

        System.out.println("1 - Create post\n2 - Get post by id\n3 - Get all posts\n" +
                "4 - Change post\n5 - Delete post\n6 - Exit");
        do {
            int run = in.nextInt();
            switch (run) {
                case 1:
                    System.out.println("Create post");
                    System.out.println("Enter the content");
                    contentIn = in.next();
                    System.out.println("Enter the created");
                    createdIn = in.nextLong();
                    System.out.println("Enter the updated");
                    updatedIn = in.nextLong();
                    System.out.println(postController.checkSaveController(contentIn, createdIn, updatedIn));
                    doChoise();
                case 2:
                    System.out.println("Get post by id\nEnter the id:");
                    idIn = in.nextLong();
                    Post postById = postController.checkGetByIdController(idIn);
                    if(postById == null){
                        printNoSuchElement();
                    }else {
                        System.out.println(postById);
                    }
                    doChoise();
                case 3:
                    System.out.println("Get all posts");
                    List<Post> allPosts = postController.checkGetAllController();
                    if (allPosts == null) {
                        System.out.println("The list of posts is empty");
                    } else {
                        allPosts.stream().forEach(x -> System.out.println(x));
                    }
                    doChoise();
                case 4:
                    System.out.println("Change record");
                    System.out.println("Enter the id of post to change his");
                    idIn = in.nextLong();
                    System.out.println("Enter the new content");
                    contentIn = in.next();
                    System.out.println("Enter the new created");
                    createdIn = in.nextLong();
                    System.out.println("Enter the new updated");
                    updatedIn = in.nextLong();
                    Post postUpdate = postController.checkUpdateController(idIn, contentIn, createdIn, updatedIn);
                    if(postUpdate != null) {
                        System.out.println(postUpdate);
                    }else{
                        printNoSuchElement();
                    }
                    doChoise();
                case 5:
                    System.out.println("Delete post\nEnter the id of post to delete his");
                    idIn = in.nextLong();
                    postController.checkDeleteByIdController(idIn);
                    doChoise();
                case 6:
                    System.out.println("Exit to main menu");
                    startView.doChoise();
            }
        }while (true);
    }
    public void printNoSuchElement(){
        System.out.println("The post whith this <id> is not exist");
    }
}
