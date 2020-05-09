package wizut.tpsi.lab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private DataSource dataSource;

    public List<BlogPost> getAllPosts() throws SQLException {
        List<BlogPost> posts = new ArrayList<>();

        String sql = "SELECT * FROM blog_post";

        try(Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");

                BlogPost blogPost = new BlogPost(id, title, content);
                posts.add(blogPost);
            }
        }
        return posts;
    }

    public void createPost(BlogPost blogPost) throws SQLException {
        String sql = String.format("INSERT INTO blog_post(title, content) VALUES(?, ?)", blogPost.getTitle(), blogPost.getContent());

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, blogPost.getTitle());
            ps.setString(2, blogPost.getContent());

            ps.executeUpdate();
        }
    }

    public void deletePost(Integer id) throws  SQLException{
        String sql = "DELETE FROM blog_post WHERE id = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }
}
