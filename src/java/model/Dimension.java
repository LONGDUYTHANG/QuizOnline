
package model;



public class Dimension {
    private int dimension_id;
    private DimensionType dimension_type_id;
    private Subject subject_id;
    private String dimension_name;

    public Dimension(int dimension_id, DimensionType dimension_type_id, Subject subject_id, String dimension_name) {
        this.dimension_id = dimension_id;
        this.dimension_type_id = dimension_type_id;
        this.subject_id = subject_id;
        this.dimension_name = dimension_name;
    }

  
    
    

    public int getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(int dimension_id) {
        this.dimension_id = dimension_id;
    }

    public Subject getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Subject subject_id) {
        this.subject_id = subject_id;
    }

   
   

    public String getDimension_name() {
        return dimension_name;
    }

    public void setDimension_name(String dimension_name) {
        this.dimension_name = dimension_name;
    }

    public Dimension() {
    }

    public DimensionType getDimension_type_id() {
        return dimension_type_id;
    }

    public void setDimension_type_id(DimensionType dimension_type_id) {
        this.dimension_type_id = dimension_type_id;
    }

 
  
}
