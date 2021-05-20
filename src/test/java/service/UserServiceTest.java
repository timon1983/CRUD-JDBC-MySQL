package service;

import model.Post;
import model.Region;
import model.User;
import repository.jdbc.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepositoryImpl userRepository = Mockito.mock(UserRepositoryImpl.class);
    @Mock
    private UserService userServiceMock = Mockito.mock(UserService.class);

    @Test
    void checkSaveService_Should_Return_User() {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(0,"Igor","Golin", posts,region);
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    void checkGetByIdService_Should_Return_User_By_Id() {
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(2,"Igor","Golin", posts,region);
        when(userRepository.getById(2L)).thenReturn(user);
    }
    @Test
    void checkGetAllService_Should_Show_All_of_Users(){
        List<User> users = new ArrayList<>();
        when(userRepository.getAll()).thenReturn(users);
    }

    @Test
    void checkUpdateService_Should_Return_UpdateUser(){
        List<Post> posts = new ArrayList<>();
        Region region = new Region(0,"Russia");
        User user = new User(3,"Igor","Golin", posts,region);
        when(userRepository.update(user)).thenReturn(user);
    }

    @Test
    void checkDeleteByIdService_Should_Run_DeleteUser(){
        userServiceMock.checkDeleteByIdService(2L);
       Mockito.verify(userServiceMock).checkDeleteByIdService(2L);

    }
}