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
public class RegistrySet {

    public RegistrySet() {
    }

    public RegistrySet(int id, int length) {
        this.id = id;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public ArrayList<FieldSet> getSetFieldList() {
        return setFieldList;
    }

    public void setSetFieldList(ArrayList<FieldSet> setFieldList) {
        this.setFieldList = setFieldList;
    }
    int id, length;
    ArrayList<FieldSet> setFieldList = new ArrayList();
}
