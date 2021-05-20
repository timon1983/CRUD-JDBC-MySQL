package controller;

import model.Post;
import model.Region;
import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService = Mockito.mock(UserService.class);
    @Mock
    private UserController userController = Mockito.mock(UserController.class);

    @Test
    void checkSaveController_Should_Return_User() {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0, "USA");
        User user = new User(0, "Igor","Golin", posts,region);
        when(userController.checkSaveController("Igor","Golin", "USA")).thenReturn(user);
    }

    @Test
    void checkGetByIdController_Should_Return_User_By_Id() {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(2,"Igor","Golin", posts,region);
        when(userController.checkGetByIdController(2L)).thenReturn(user);
    }

    @Test
    void checkGetAllController_Should_Show_All_of_Users() {
        List<User> users = new ArrayList<>();
        when(userController.checkGetAllController()).thenReturn(users);
    }

    @Test
    void checkUpdateController_Should_Return_UpdateUser() {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(3,"Igor","Golin", posts,region);
        when(userController.checkUpdateController(3L,"Igor","Golin", "USA")).thenReturn(user);
    }

    @Test
    void checkDeleteByIdController_Should_Run_DeleteUser() {
        userController.checkDeleteByIdController(2L);
        Mockito.verify(userController).checkDeleteByIdController(2L);
    }
}