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
public class Curs {
    protected int id;
    protected String cod;
    protected String titlu;
 
    public Curs() {
    }

    public Curs(int id) {
        this.id = id;
    }

    public Curs(int id, String cod, String titlu) {
        this.id = id;
        this.cod = cod;
        this.titlu = titlu;
    }
 
    public Curs(String cod, String titlu) {
        this.cod = cod;
        this.titlu = titlu;
    }
 
    public int getId() {
        return id;
    }
 
    public String getcod() {
        return cod;
    }
 
    public void setcod(String cod) {
        this.cod = cod;
    }
    
    public String gettitlu() {
        return titlu;
    }
    
    public void settitlu(String titlu) {
        this.titlu = titlu;
    }
}