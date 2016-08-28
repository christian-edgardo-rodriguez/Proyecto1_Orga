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
public class RegistryVariable extends Registry {

    public RegistryVariable(int position, int id, ArrayList<Field> fieldList) {
        super(id, fieldList);
        this.position = position;
    }

    public RegistryVariable(int id, ArrayList<Field> fieldList) {
        super(id, fieldList);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    int position;
}
