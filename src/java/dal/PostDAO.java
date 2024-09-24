/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class PostDAO extends DBContext {

    public ArrayList<Post> getPost() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Post> post_list = new ArrayList<>();
        try {
            String strSelect = "SELECT Top 5 * FROM Blog";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                post_list.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return post_list;
    }

    public ArrayList<Post> getHottestPost() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Post> hottest_post_list = new ArrayList<>();
        try {
            String strSelect = "SELECT Top 5 * FROM Blog order by [number_of_access] desc";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                hottest_post_list.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return hottest_post_list;
    }

    public Post getPostByBlogID(int blog_id) {
        PreparedStatement stm;
        ResultSet rs;
        Post myPost = new Post();
        try {
            String strSelect = "SELECT * FROM Blog where blog_id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, blog_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                myPost.setBlog_id(rs.getInt("blog_id"));
                myPost.setBlog_title(rs.getString("blog_title"));
                myPost.setThumbnail(rs.getString("thumbnail"));
                myPost.setCreated_date(rs.getString("created_date"));
                myPost.setBlog_summary(rs.getString("blog_summary"));
                myPost.setBlog_title(rs.getString("blog_title"));
                myPost.setBlog_content(rs.getString("blog_content"));
                myPost.setUpdated_date(rs.getString("updated_date"));
                myPost.setIsFeatured(rs.getBoolean("isFeatured"));
                myPost.setStatus(rs.getBoolean("status"));
                myPost.setCategory_id(rs.getInt("category_id"));
                myPost.setAccount_id(rs.getInt("account_id"));
                myPost.setNumber_of_access(rs.getInt("number_of_access"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return myPost;
    }

    public static void main(String[] args) {
        PostDAO a =new PostDAO();
        ArrayList<Post> h= a.getHottestPost();
        for(Post s:h){
        System.out.println(s.getBlog_content());
        }
    }
}
