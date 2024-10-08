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
import model.Account;

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
            String strSelect = "SELECT TOP 3 * FROM Blog order by [number_of_access] desc";
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

    public ArrayList<Post> getHottestPost1() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Post> hottest_post_list = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Blog order by [number_of_access] desc";
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

    public ArrayList<Post> searchPosts(String keyword) {
        ArrayList<Post> filteredPosts = new ArrayList<>();
        String query = "SELECT * FROM Blog WHERE blog_title LIKE ? OR blog_content LIKE ?"; // Tìm kiếm theo tiêu đề hoặc nội dung

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                filteredPosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredPosts;
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
        PostDAO a = new PostDAO();
        ArrayList<Post> h = a.getHottestPost();
        for (Post s : h) {
            System.out.println(s.getBlog_content());
        }
    }

    public int countCreatedBlogs(Account a) {
        String sql = "select COUNT(*) as count_blog from Blog\n"
                + "where account_id = ?";
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccount_id());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count_blog");
            }
        } catch (Exception e) {
        }
        return count;
    }

    public ArrayList<Post> getLatestPosts() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Post> latestPosts = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Blog ORDER BY created_date DESC"; // Sắp xếp theo ngày tạo mới nhất
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                // Thiết lập các thuộc tính cho đối tượng Post
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                latestPosts.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return latestPosts;
    }

    public ArrayList<Post> getOldestPosts() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Post> oldestPosts = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Blog ORDER BY created_date ASC"; // Sắp xếp theo ngày tạo cũ nhất
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                // Thiết lập các thuộc tính cho đối tượng Post
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                oldestPosts.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return oldestPosts;
    }

    public ArrayList<Post> searchBlogsByCategory(String keyword) {
        ArrayList<Post> search_post = new ArrayList<>();
        
        String sql = "SELECT * FROM Blog WHERE category_id in (select category_id from Category where category_name= ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Sử dụng % để tìm kiếm bất kỳ vị trí nào trong tên hoặc mô tả
            pstmt.setString(1, keyword);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setBlog_id(rs.getInt("blog_id"));
                post.setBlog_title(rs.getString("blog_title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setCreated_date(rs.getString("created_date"));
                post.setBlog_summary(rs.getString("blog_summary"));
                post.setBlog_content(rs.getString("blog_content"));
                post.setUpdated_date(rs.getString("updated_date"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                post.setCategory_id(rs.getInt("category_id"));
                post.setAccount_id(rs.getInt("account_id"));
                post.setNumber_of_access(rs.getInt("number_of_access"));

                search_post.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return search_post;
    }

}
