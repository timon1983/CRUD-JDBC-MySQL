package view;

import controller.RegionControllerImpl;
import model.Region;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RegionView implements View{

    private Scanner in = new Scanner(System.in);
    private RegionControllerImpl regionController = new RegionControllerImpl();
    private View startView = new StartView();

    @Override
    public void doChoise() throws SQLException, ClassNotFoundException {

        long idIn;
        String nameIn;
        System.out.println("1 - Create region\n2 - Get region by id\n3 - Get all regions\n" +
                "4 - Change region\n5 - Delete region\n6 - Exit");
        do {
            int run = in.nextInt();
            switch (run) {
                case 1:
                    System.out.println("Create region");
                    System.out.println("Enter the name of region");
                    nameIn = in.next();
                    System.out.println(regionController.checkSaveController(nameIn));
                    doChoise();
                case 2:
                    System.out.println("Get region by id\nEnter the id:");
                    idIn = in.nextLong();
                    Region regionById = regionController.checkGetByIdController(idIn);
                    if(regionById == null){
                        printNoSuchElement();
                    }else {
                        System.out.println(regionById);
                    }
                    doChoise();
                case 3:
                    System.out.println("Get all regions");
                    List<Region> allRegions = regionController.checkGetAllController();
                    if (allRegions == null) {
                        System.out.println("The list of regions is empty");
                    } else {
                        allRegions.stream().forEach(x -> System.out.println(x));
                    }
                    doChoise();
                case 4:
                    System.out.println("Change region");
                    System.out.println("Enter the id of region to change his");
                    idIn = in.nextLong();
                    System.out.println("Enter the new nema of region");
                    nameIn = in.next();
                    Region regionUpdate = regionController.checkUpdateController(idIn, nameIn);
                    if(regionUpdate != null) {
                        System.out.println(regionUpdate);
                    }else {
                        printNoSuchElement();
                    }
                    doChoise();
                case 5:
                    System.out.println("Delete region\nEnter the id of region to delete his");
                    idIn = in.nextLong();
                    regionController.checkDeleteByIdController(idIn);
                    doChoise();
                case 6:
                    System.out.println("Exit to main menu");
                    startView.doChoise();
            }
        }while (true);
    }
    public void printNoSuchElement(){
        System.out.println("The region whith this <id> is not exist");
    }
}
