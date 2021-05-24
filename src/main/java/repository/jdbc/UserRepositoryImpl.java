package repository.jdbc;

import model.Post;
import model.Region;
import model.User;
import repository.UserRepository;
import java.sql.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(Long id) {
        User userGetById = null;
        Set<User> usersUnic = new HashSet<>();
        Set<Post> postsUnic = new HashSet<>();
        List<Post> posts = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statementUser = connection.createStatement())
        {
            String sqlSelUsersAndRegions = "SELECT * FROM (SELECT * FROM users JOIN regions USING(user_id) WHERE user_id = " +
                     + id + ") FULL JOIN posts";
            ResultSet resultSetUsersAndRegions = statementUser.executeQuery(sqlSelUsersAndRegions);
            if (resultSetUsersAndRegions == null) {
                return null;
            } else {
                while (resultSetUsersAndRegions.next()) {
                    Post post = new Post(0, null, 0, 0);
                    post.setId(resultSetUsersAndRegions.getLong("post_id"));
                    post.setContent(resultSetUsersAndRegions.getString("content"));
                    post.setCreated(resultSetUsersAndRegions.getLong("created"));
                    post.setUpdated(resultSetUsersAndRegions.getLong("updated"));
                    postsUnic.add(post);
                    User user = new User(0, null, null, null, null);
                    Region region = new Region(0, null);
                    user.setId(resultSetUsersAndRegions.getLong("user_id"));
                    user.setFirstName(resultSetUsersAndRegions.getString("first_name"));
                    user.setLastName(resultSetUsersAndRegions.getString("last_name"));
                    region.setId(resultSetUsersAndRegions.getLong("region_id"));
                    region.setName(resultSetUsersAndRegions.getString("region_name"));
                    user.setRegion(region);
                    usersUnic.add(user);
                }
                posts.addAll(postsUnic);
                userGetById = usersUnic.stream().findFirst().get();
                userGetById.setPosts(posts);
            }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return userGetById;
    }

    @Override
    public List<User> getAll() {
        Set<User> usersUnic = new HashSet<>();
        List<User> users = new ArrayList<>();
        Set<Post> postsUnic = new HashSet<>();
        List<Post> posts = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statementUser = connection.createStatement())
        {
            String sqlSelUsersAndRegions = "SELECT * FROM (SELECT * FROM users JOIN regions USING(user_id)) FULL JOIN posts";
            ResultSet resultSetUsersAndRegions = statementUser.executeQuery(sqlSelUsersAndRegions);
            if (resultSetUsersAndRegions == null) {
                return null;
            } else {
                while (resultSetUsersAndRegions.next()) {
                    Post post = new Post(0, null, 0, 0);
                    post.setId(resultSetUsersAndRegions.getLong("post_id"));
                    post.setContent(resultSetUsersAndRegions.getString("content"));
                    post.setCreated(resultSetUsersAndRegions.getLong("created"));
                    post.setUpdated(resultSetUsersAndRegions.getLong("updated"));
                    postsUnic.add(post);
                    User user = new User(0, null, null, null, null);
                    Region region = new Region(0, null);
                    user.setId(resultSetUsersAndRegions.getLong("user_id"));
                    user.setFirstName(resultSetUsersAndRegions.getString("first_name"));
                    user.setLastName(resultSetUsersAndRegions.getString("last_name"));
                    region.setId(resultSetUsersAndRegions.getLong("region_id"));
                    region.setName(resultSetUsersAndRegions.getString("region_name"));
                    user.setRegion(region);
                    usersUnic.add(user);
                }
                posts.addAll(postsUnic);
                users = usersUnic.stream().map(x ->{ x.setPosts(posts);
                return x;
                }).collect(Collectors.toList());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return users;
    }

    @Override
    public User save(User user)  {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            String sqlInsertUsers = "INSERT INTO users(first_name , last_name) " +
                    "VALUES ('" + user.getFirstName() + "','" + user.getLastName() + "')";
            statement.executeUpdate(sqlInsertUsers);
            String sqlInsertRegion = "INSERT INTO regions(region_name, user_id) " +
                    "VALUES ('" + user.getRegion().getName() + "',(SELECT MAX(user_id) FROM users))";
            statement.executeUpdate(sqlInsertRegion);

            String sqlNewUser = "SELECT * FROM users JOIN regions USING(user_id) ORDER BY user_id DESC LIMIT 1";
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
        try (Connection connection = Util.getConnection();
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
        try (Connection connection = Util.getConnection();
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
