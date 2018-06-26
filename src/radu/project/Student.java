
package radu.project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xenot
 */
public class Student {
    protected int id;
    protected String cnp;
    protected String nume;
 
    public Student() {
    }
 
    public Student(int id) {
  		this.id = id;
    }

    public Student(int id,String cnp, String nume) {
        this(cnp,nume);
        this.id = id;
    }

    public Student(String cnp, String nume) {
        this.cnp = cnp;
        this.nume = nume;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
	   this.id = id;
    }

    public String getcnp() {
        return cnp;
    }
 
    public void setcnp(String cnp) {
        this.cnp = cnp;
    }
    
    public String getnume() {
        return nume;
    }
    
    public void setnume(String nume) {
        this.nume = nume;
    }
}