package Repository.jdbc;

import Model.Post;
import Repository.PostRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl extends Util implements PostRepository {

    @Override
    public Post getById(Long id){

        Post post = new Post(0 , null , 0 , 0);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlSelectPost = "SELECT * FROM posts WHERE post_id = " + id;
                ResultSet resultSetPost = statement.executeQuery(sqlSelectPost);
                resultSetPost.next();
                post.setId(resultSetPost.getLong("post_id"));
                post.setContent(resultSetPost.getString("content"));
                post.setCreated(resultSetPost.getLong("created"));
                post.setUpdated(resultSetPost.getLong("updated"));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlSelectPost = "SELECT * FROM posts";
                ResultSet resultSetPost = statement.executeQuery(sqlSelectPost);
                if(resultSetPost == null){
                    return null;
                }else {
                while (resultSetPost.next()) {
                    Post post = new Post(0, null, 0, 0);
                    post.setId(resultSetPost.getLong("post_id"));
                    post.setContent(resultSetPost.getString("content"));
                    post.setCreated(resultSetPost.getLong("created"));
                    post.setUpdated(resultSetPost.getLong("updated"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return posts;
    }

    @Override
    public Post save(Post post){
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
               String sqlInsertPosts = "INSERT INTO posts(content , created , updated) VALUES('" + post.getContent() +
                    "','" + post.getCreated() + "','" + post.getUpdated() + "')";
               statement.executeUpdate(sqlInsertPosts);
               ResultSet resultSetPost = statement.executeQuery("SELECT * FROM posts WHERE post_id = " +
                    "(SELECT MAX(post_id) FROM posts)");
            resultSetPost.next();
            long newPostID = resultSetPost.getLong("post_id");
            post.setId(newPostID);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlUpdatePost = "UPDATE posts SET content = '" + post.getContent() + "' , created = '"
                        + post.getCreated() + "' , updated = '" + post.getUpdated() + "' WHERE post_id = " + post.getId();
                statement.executeUpdate(sqlUpdatePost);
                ResultSet resultSelectRegion = statement.executeQuery("SELECT * FROM regions" +
                        " WHERE region_id = (SELECT MAX(region_id) FROM regions)");
                resultSelectRegion.next();
                long newPostID = resultSelectRegion.getLong("region_id");
                post.setId(newPostID);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
            return post;
    }

    @Override
    public void deleteById(Long id) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
                String sqlDeletePost = "DELETE FROM posts WHERE post_id = " + id;
                statement.executeUpdate(sqlDeletePost);
            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Statement ERROR");
            }
    }
}
