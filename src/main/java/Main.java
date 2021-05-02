import View.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        View startProgram = new StartView();
        startProgram.doChoise();
    }
}
