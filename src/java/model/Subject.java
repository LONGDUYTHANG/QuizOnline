/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author FPT SHOP
 */
public class Subject {
    private int subjectId;
    private String subjectName;
    private int categoryId;
    private boolean status;
    private boolean isFeatured;
    private String thumbnail;
    private String tagline;
    private String description;
    private int accountId;
    private java.sql.Timestamp createdDate;

    public Subject() {
    }

    public Subject(int subjectId, String subjectName, int categoryId, boolean status, boolean isFeatured, String thumbnail, String tagline, String description, int accountId, Timestamp createdDate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.categoryId = categoryId;
        this.status = status;
        this.isFeatured = isFeatured;
        this.thumbnail = thumbnail;
        this.tagline = tagline;
        this.description = description;
        this.accountId = accountId;
        this.createdDate = createdDate;
    }

    
    
    
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectName=" + subjectName + ", categoryId=" + categoryId + ", status=" + status + ", isFeatured=" + isFeatured + ", thumbnail=" + thumbnail + ", tagline=" + tagline + ", description=" + description + ", accountId=" + accountId + ", createdDate=" + createdDate + '}';
    }
    
    private int subject_id;
    private String subject_name;
    private SubjectCategory category;
    private int status1;
    private String tag_line;
    private boolean is_featured;
    private Account account_id;
    private Date created_date;

    public Subject(int subject_id, String subject_name, SubjectCategory category, int status1, boolean is_featured, String thumbnail, String tag_line, String description, Account account_id, Date created_date) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.category = category;
        this.status1 = status1;
        this.is_featured = is_featured;
        this.thumbnail = thumbnail;
        this.tag_line = tag_line;
        this.description = description;
        this.account_id = account_id;
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

    public int getStatus1() {
        return status1;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }

    public String getTag_line() {
        return tag_line;
    }

    public void setTag_line(String tag_line) {
        this.tag_line = tag_line;
    }

    public boolean isIs_featured() {
        return is_featured;
    }

    public void setIs_featured(boolean is_featured) {
        this.is_featured = is_featured;
    }

    public Account getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Account account_id) {
        this.account_id = account_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    
    
    
    
}
