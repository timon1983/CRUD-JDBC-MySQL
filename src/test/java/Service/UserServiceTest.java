package Service;

import Model.Post;
import Model.Region;
import Model.User;
import Repository.jdbc.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepositoryImpl userRepository = Mockito.mock(UserRepositoryImpl.class);
    private UserService userService = new UserService(userRepository);

    @Test
    void checkSaveService_Should_Return_User() throws SQLException, ClassNotFoundException {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(0,"Igor","Golin", posts,region);
        when(userService.checkSaveService("Igor","Golin","Russia")).thenReturn(user);
    }

    @Test
    void checkGetByIdService_Should_Return_User_By_Id() throws SQLException, ClassNotFoundException {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(2,"Igor","Golin", posts,region);

        when(userService.checkGetByIdService(2L)).thenReturn(user);
    }
    @Test
    void checkGetAllService_Should_Show_All_of_Users() throws SQLException, ClassNotFoundException {
        List<User> users = userRepository.getAll();
        when(userService.checkGetAllService()).thenReturn(users);
    }

    @Test
    void checkUpdateService_Should_Return_UpdateUser() throws SQLException, ClassNotFoundException {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(3,"Igor","Golin", posts,region);
        when(userService.checkUpdateService(3L,"Igor","Golin", "Russia")).thenReturn(user);
    }

    @Test
    void checkDeleteByIdService() {
    }
}