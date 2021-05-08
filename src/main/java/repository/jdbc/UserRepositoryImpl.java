package repository.jdbc;

import model.Post;
import model.Region;
import model.User;
import repository.UserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    Util util = Util.getUtil();

    @Override
    public User getById(Long id) {
        User user = new User(0, null,null, null, null);
        Region region = new Region(0, null);
        List<Post> posts = new ArrayList<>();
        try (Connection connection = util.getConnection();
             Statement statementUser = connection.createStatement();
             Statement statementPost = connection.createStatement())
        {
                String sqlSelUsersAndRegions = "SELECT * FROM users JOIN regions USING(user_id) WHERE user_id = " + id;
                String sqlSelectPosts = "SELECT * FROM posts";
                ResultSet resultSetUsersAndRegions = statementUser.executeQuery(sqlSelUsersAndRegions);
                ResultSet resultSetPosts = statementPost.executeQuery(sqlSelectPosts);
            if (!resultSetUsersAndRegions.next()) {
                return null;
            }else{
                user.setId(resultSetUsersAndRegions.getLong("user_id"));
                user.setFirstName(resultSetUsersAndRegions.getString("first_name"));
                user.setLastName(resultSetUsersAndRegions.getString("last_name"));
                region.setId(resultSetUsersAndRegions.getLong("region_id"));
                region.setName(resultSetUsersAndRegions.getString("region_name"));
                user.setRegion(region);
                    while (resultSetPosts.next()) {
                        Post post = new Post(0, null, 0, 0);
                        post.setId(resultSetPosts.getLong("post_id"));
                        post.setContent(resultSetPosts.getString("content"));
                        post.setCreated(resultSetPosts.getLong("created"));
                        post.setUpdated(resultSetPosts.getLong("updated"));
                        posts.add(post);
                    }
                    user.setPosts(posts);}
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        try (Connection connection = util.getConnection();
             Statement statementUser = connection.createStatement();
             Statement statementPost = connection.createStatement())
        {
                String sqlSelUsersAndRegions = "SELECT * FROM users JOIN regions USING(user_id)";
                String sqlSelectPosts = "SELECT * FROM posts";
                ResultSet resultSetUsersAndRegions = statementUser.executeQuery(sqlSelUsersAndRegions);
                ResultSet resultSetPosts = statementPost.executeQuery(sqlSelectPosts);
            if (resultSetUsersAndRegions == null) {
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
                while (resultSetUsersAndRegions.next()) {
                    User user = new User(0, null, null, null, null);
                    Region region = new Region(0, null);
                    user.setId(resultSetUsersAndRegions.getLong("user_id"));
                    user.setFirstName(resultSetUsersAndRegions.getString("first_name"));
                    user.setLastName(resultSetUsersAndRegions.getString("last_name"));
                    region.setId(resultSetUsersAndRegions.getLong("region_id"));
                    region.setName(resultSetUsersAndRegions.getString("region_name"));
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
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()){
            String sqlInsertUsers = "INSERT INTO users(first_name , last_name) " +
                    "VALUES ('" + user.getFirstName() + "','" + user.getLastName() + "')";
            statement.executeUpdate(sqlInsertUsers);
            String sqlInsertRegion = "INSERT INTO regions(region_name, user_id) " +
                    "VALUES ('" + user.getRegion().getName() + "',(SELECT MAX(user_id) FROM users))";
            statement.executeUpdate(sqlInsertRegion);

            String sqlNewUser = "SELECT * FROM users JOIN regions USING(user_id)" +
                    " WHERE user_id = (SELECT MAX(user_id) FROM users)";
            ResultSet resultSetNewUser = statement.executeQuery(sqlNewUser);
            resultSetNewUser.next();
            long newUserID = resultSetNewUser.getLong("user_id");
            user.setId(newUserID);
            long newRegionID = resultSetNewUser.getLong("region_id");
            user.getRegion().setId(newRegionID);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = util.getConnection();
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
        try (Connection connection = util.getConnection();
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