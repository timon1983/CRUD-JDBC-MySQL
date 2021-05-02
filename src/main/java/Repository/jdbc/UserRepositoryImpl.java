package Repository.jdbc;

import Model.Post;
import Model.Region;
import Model.User;
import Repository.UserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends Util implements UserRepository {

    @Override
    public User getById(Long id) {
        User user = new User(0, null,null, null, null);
        Region region = new Region(0, null);
        List<Post> posts = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statementUser = connection.createStatement();
             Statement statementPost = connection.createStatement();
             Statement statementRegion = connection.createStatement()) {
                String sqlSelectUsers = "SELECT * FROM users WHERE user_id =" + id;
                String sqlSelectPosts = "SELECT * FROM posts";
                String sqlSelectRegions = "SELECT * FROM regions WHERE user_id =" + id;
                ResultSet resultSetUsers = statementUser.executeQuery(sqlSelectUsers);
                ResultSet resultSetPosts = statementPost.executeQuery(sqlSelectPosts);
                ResultSet resultSetRegions = statementRegion.executeQuery(sqlSelectRegions);
                resultSetUsers.next();
                user.setId(resultSetUsers.getLong("user_id"));
                user.setFirstName(resultSetUsers.getString("first_name"));
                user.setLastName(resultSetUsers.getString("last_name"));
                resultSetRegions.next();
                region.setId(resultSetRegions.getLong("region_id"));
                region.setName(resultSetRegions.getString("region_name"));
                user.setRegion(region);
                    while (resultSetPosts.next()) {
                        Post post = new Post(0, null, 0, 0);
                        post.setId(resultSetPosts.getLong("post_id"));
                        post.setContent(resultSetPosts.getString("content"));
                        post.setCreated(resultSetPosts.getLong("created"));
                        post.setUpdated(resultSetPosts.getLong("updated"));
                        posts.add(post);
                    }
                    user.setPosts(posts);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statementUser = connection.createStatement();
             Statement statementPost = connection.createStatement();
             Statement statementRegion = connection.createStatement()) {
                String sqlSelectUsers = "SELECT * FROM users";
                String sqlSelectPosts = "SELECT * FROM posts";
                String sqlSelectRegions = "SELECT * FROM regions JOIN users WHERE regions.user_id = users.user_id";
                ResultSet resultSetUsers = statementUser.executeQuery(sqlSelectUsers);
                ResultSet resultSetPosts = statementPost.executeQuery(sqlSelectPosts);
                ResultSet resultSetRegions = statementRegion.executeQuery(sqlSelectRegions);
            if (resultSetUsers == null) {
                return null;
            } else {
                while (resultSetPosts.next()) {
                    Post post = new Post(0, null, 0, 0);
                    post.setId(resultSetPosts.getLong("post_id"));
                    post.setContent(resultSetPosts.getString("content"));
                    post.setCreated(resultSetPosts.getLong("created"));
                    post.setUpdated(resultSetPosts.getLong("updated"));
                    posts.add(post);
                }
                while (resultSetUsers.next()) {
                    User user = new User(0, null, null, null, null);
                    Region region = new Region(0, null);
                    user.setId(resultSetUsers.getLong("user_id"));
                    user.setFirstName(resultSetUsers.getString("first_name"));
                    user.setLastName(resultSetUsers.getString("last_name"));
                    resultSetRegions.next();
                    region.setId(resultSetRegions.getLong("region_id"));
                    region.setName(resultSetRegions.getString("region_name"));
                    user.setRegion(region);
                    user.setPosts(posts);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return users;
    }

    @Override
    public User save(User user)  {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            String sqlInsertUsers = "INSERT INTO users(first_name , last_name) " +
                    "VALUES ('" + user.getFirstName() + "','" + user.getLastName() + "')";
            statement.executeUpdate(sqlInsertUsers);
            String sqlInsertRegion = "INSERT INTO regions(region_name, user_id) " +
                    "VALUES ('" + user.getRegion().getName() + "',(SELECT MAX(user_id) FROM users))";
            statement.executeUpdate(sqlInsertRegion);

            ResultSet resultSelectUsers = statement.executeQuery("SELECT * FROM users " +
                    "WHERE user_id = (SELECT MAX(user_id) FROM users)");
            resultSelectUsers.next();
            long newUserID = resultSelectUsers.getLong("user_id");
            user.setId(newUserID);

            ResultSet resultSelectRegion = statement.executeQuery("SELECT * FROM regions " +
                    "WHERE region_id = (SELECT MAX(region_id) FROM regions)");
            resultSelectRegion.next();
            long newRegionID = resultSelectRegion.getLong("region_id");
            user.getRegion().setId(newRegionID);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlUpdateUser = "UPDATE users SET first_name = '" + user.getFirstName() + "', last_name = '" +
                        user.getLastName() + "' WHERE user_id = " + user.getId();
                String sqlUpdateRegion = "UPDATE regions SET region_name = '" + user.getRegion().getName() +
                        "' WHERE user_id = " + user.getId();
                statement.executeUpdate(sqlUpdateUser);
                statement.executeUpdate(sqlUpdateRegion);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return getById(user.getId());
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlDeleteUser = "DELETE FROM users WHERE user_id = " + id;
                String sqlDeleteRegion = "DELETE FROM regions WHERE user_id = " + id;
                statement.executeUpdate(sqlDeleteUser);
                statement.executeUpdate(sqlDeleteRegion);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
        }
}
