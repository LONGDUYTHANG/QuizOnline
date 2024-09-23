/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FPT SHOP
 */
public class Dimension {
    private int dimension_id;
    private String dimension_name;
    private int dimension_type_id;
    private int subject_id;

    public Dimension() {
    }

    public Dimension(int dimension_id, String dimension_name, int dimension_type_id, int subject_id) {
        this.dimension_id = dimension_id;
        this.dimension_name = dimension_name;
        this.dimension_type_id = dimension_type_id;
        this.subject_id = subject_id;
    }

    public int getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(int dimension_id) {
        this.dimension_id = dimension_id;
    }

    public String getDimension_name() {
        return dimension_name;
    }

    public void setDimension_name(String dimension_name) {
        this.dimension_name = dimension_name;
    }

    public int getDimension_type_id() {
        return dimension_type_id;
    }

    public void setDimension_type_id(int dimension_type_id) {
        this.dimension_type_id = dimension_type_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Dimension{" + "dimension_id=" + dimension_id + ", dimension_name=" + dimension_name + ", dimension_type_id=" + dimension_type_id + ", subject_id=" + subject_id + '}';
    }
    
    
}