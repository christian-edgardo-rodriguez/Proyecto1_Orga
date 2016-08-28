/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Registry {

    public Registry() {
    }

    public Registry(int id, ArrayList<Field> fieldList) {
        this.id = id;
        this.fieldList = fieldList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList<Field> fieldList) {
        this.fieldList = fieldList;
    }
    int id;
    ArrayList<Field> fieldList;
}
