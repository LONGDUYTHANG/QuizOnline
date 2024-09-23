package model;

//import DAOs.SubjectCategoryDAO;
//import DAOs.SubjectDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Subject {

    private int subject_id;
    private String subject_name;
    private SubjectCategory category;
    private int status;
    private boolean is_featured;
    private String thumbnail;
    private String tag_line;
    private String description;
    private Account account_id;
    private Date created_date;


    public Subject() {
    }

    public Subject(int subject_id, String subject_name, SubjectCategory category, int status, boolean is_featured, String thumbnail, String tag_line, String description, Account account_id, Date created_date) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.category = category;
        this.status = status;
        this.is_featured = is_featured;
        this.thumbnail = thumbnail;
        this.tag_line = tag_line;
        this.description = description;
        this.account_id = account_id;
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

   

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public SubjectCategory getCategory() {
        return category;
    }

    public void setCategory(SubjectCategory category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIs_featured() {
        return is_featured;
    }

    public void setIs_featured(boolean is_featured) {
        this.is_featured = is_featured;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTag_line() {
        return tag_line;
    }

    public void setTag_line(String tag_line) {
        this.tag_line = tag_line;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Account account_id) {
        this.account_id = account_id;
    }
    
    
    
    
}
