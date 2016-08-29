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
public class RegistryVariable {

    public RegistryVariable(int id, ArrayList<FieldVariable> variableFieldList) {
        this.id = id;
        this.variableFieldList = variableFieldList;
    }
    
    public RegistryVariable(ArrayList<FieldVariable> variableFieldList, int length) {
        this.variableFieldList = variableFieldList;
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

    public ArrayList<FieldVariable> getVariableFieldList() {
        return variableFieldList;
    }

    public void setVariableFieldList(ArrayList<FieldVariable> variableFieldList) {
        this.variableFieldList = variableFieldList;
    }
    int id, length;
    ArrayList<FieldVariable> variableFieldList = new ArrayList();
}
