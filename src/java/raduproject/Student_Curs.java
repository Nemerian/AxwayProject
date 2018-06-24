package raduproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xenot
 */
public class Student_Curs {
    protected String ids;
    protected String idc;
 
    public Student_Curs() {
    }
 
 
    public Student_Curs(int id, String ids, String idc) {
        this(ids, idc);
    }
     
    public Student_Curs(String ids, String idc) {
        this.ids = ids;
        this.idc = idc;
    }
 
    public String getids() {
        return ids;
    }
 
    public void setids(String ids) {
        this.ids = ids;
    }
    
    public String getidc() {
        return idc;
    }
    
    public void setidc(String idc) {
        this.idc = idc;
    }
}