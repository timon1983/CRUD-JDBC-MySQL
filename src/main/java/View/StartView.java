package View;

import java.sql.SQLException;
import java.util.Scanner;

public class StartView implements View{
    private Scanner in = new Scanner(System.in);

    @Override
    public void doChoise() throws SQLException, ClassNotFoundException {
        System.out.println("1 - User\n2 - Post\n3 - Region\n4 - Exit");
        do{
            int numb = in.nextInt();
            switch (numb){
                case 1:
                    View viewUser = new UserView();
                    viewUser.doChoise();
                case 2:
                    View viewPost= new PostView();
                    viewPost.doChoise();
                case 3:
                    View viewRegion = new RegionView();
                    viewRegion.doChoise();
                case 4:
                    System.exit(0);
            }
        }while (true);
    }
}
