/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Document {

    public Document() {
    }

    public Document(String name) {
        this.name = name;
        file = new File("./"+name+".txt");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<String> getRegistryList() {
        return registryList;
    }

    public void setRegistryList(ArrayList<String> registryList) {
        this.registryList = registryList;
    }
    String name;
    File file=null;
    ArrayList<String> registryList = new ArrayList();
}
