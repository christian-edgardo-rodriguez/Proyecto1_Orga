/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class DocumentSet {

    public DocumentSet() {
    }

    public DocumentSet(String name, HeaderSet header) {
        this.name = name;
        this.header = header;
        file = new File("./" + name + ".txt");
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

    public HeaderSet getHeader() {
        return header;
    }

    public void setHeader(HeaderSet header) {
        this.header = header;
    }

    public Stack getAvailList() {
        return availList;
    }

    public void setAvailList(Stack availList) {
        this.availList = availList;
    }

    public ArrayList<Index> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<Index> index) {
        this.index = index;
    }

    public ArrayList<String> getRegistryList() {
        return registryList;
    }

    public void setRegistryList(ArrayList<String> registryList) {
        this.registryList = registryList;
    }
    void add(RegistrySet tempReg) {
        try {
            catalog = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERROR, CANT OPEN");
            ex.printStackTrace();
        }
        String insert = "";
        for (int i = 0; i < tempReg.getSetFieldList().size(); i++) {
            insert += tempReg.getSetFieldList().get(i).toString();
            int temp = tempReg.getSetFieldList().get(i).toString().length();
            while (temp < header.getRegistry().getSetFieldList().get(i).getLength()) {
                insert += " ";
                temp++;
            }
        }
        while (insert.length() < header.getRegistry().length) {
            insert += " ";
        }
        if (registryList.isEmpty()) {
            String meta = "";
            meta += header.getRegistry().getLength();
            meta += "-";
            for (int i = 0; i < header.getRegistry().getSetFieldList().size(); i++) {
                if (header.getRegistry().getSetFieldList().get(i).isKey()) {
                    meta += "K/";
                } else {
                    meta += "N/";
                }
                if (header.getRegistry().getSetFieldList().get(i).foreign) {
                    meta += "K/";
                } else {
                    meta += "N/";
                }
                meta += header.getRegistry().getSetFieldList().get(i).getName() + "/";
                meta += header.getRegistry().getSetFieldList().get(i).getType() + "/";
                meta += header.getRegistry().getSetFieldList().get(i).getLength() + "/";
                meta += "-";
            }
            meta += ";";
            try {
                catalog.seek(0);
                catalog.writeBytes(meta);
                int offset = (int) catalog.getFilePointer();
                Index indexTemp = new Index(index.size() + 1, offset);
                index.add(indexTemp);
                catalog.seek(offset);
                catalog.writeBytes(insert);
                registryList.add(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, WONT ADD");
                ex.printStackTrace();
            }
        } else {
            if (availList.isEmpty()) {
                try {
                    catalog.seek(catalog.length());
                    int offset = (int) catalog.getFilePointer();
                    Index indexTemp = new Index(index.size() + 1, offset);
                    index.add(indexTemp);
                    catalog.writeBytes(insert);
                    registryList.add(insert);
                    catalog.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR, WONT ADD");
                    ex.printStackTrace();
                }
            } else {
                try {
                    Node tempList = (Node)availList.pop();
                    int position = (int)tempList.getData();
                    int offset = index.get(position).getOffset();
                    catalog.seek(offset);
                    catalog.writeBytes(insert);
                    catalog.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"ERROR, WONT LOAD");
                    ex.printStackTrace();
                }
            }
        }
    }
    void show() {
        try {
            catalog = new RandomAccessFile(file, "rw");
            for (int i = 0; i < index.size(); i++) {
                String temp = "";
                int offset = index.get(i).getOffset();
                catalog.seek(offset);
                if ((char) catalog.read() != '*') {
                    catalog.seek(offset);
                    for (int j = 0; j < header.getRegistry().length; j++) {
                        char value = (char) catalog.read();
                        temp += value + "";
                    }
                    JOptionPane.showMessageDialog(null,index.get(i).id + ". " + temp);
                }
            }
            catalog.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERROR, WONT SHOH");
            ex.printStackTrace();
        }
    }
    void delete(int number) {
        if (number > index.size()) {
            JOptionPane.showMessageDialog(null,"ERROR, NUMBER ISNT VALID");
        } else {
            for (int i = 0; i < index.size(); i++) {
                if (index.get(i).getId() == number) {
                    try {
                        catalog = new RandomAccessFile(file, "rw");
                        int offset = index.get(i).getOffset();
                        catalog.seek(offset);
                        catalog.writeBytes("*");
                        catalog.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR, WONT ERASE");
                        ex.printStackTrace();
                    }
                    availList.push(i);
                }
            }
        }
    }
    void modify(int number, RegistrySet newRegistry) {
        delete(number);
        add(newRegistry);
    }
    void find(int finder) {
        if (finder > index.size()) {
            JOptionPane.showMessageDialog(null,"ERROR, NUMBER ISNT VALID");
        } else {
            for (int i = 0; i < index.size(); i++) {
                if (index.get(i).getId() == finder) {
                    try {
                        catalog = new RandomAccessFile(file, "rw");
                        String temp = "";
                        int offset = index.get(i).getOffset();
                        catalog.seek(offset);
                        if ((char)catalog.read()!= '*') {
                            catalog.seek(offset);
                            for (int j = 0; j < header.getRegistry().length; j++) {
                                char value = (char) catalog.read();
                                temp += value + "";
                            }
                            JOptionPane.showMessageDialog(null,index.get(i).id + ". " + temp);
                        } else {
                            JOptionPane.showMessageDialog(null,"ERROR, DOESNT EXIST");
                        }
                        catalog.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR, WONT FIND");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    String name;
    File file = null;
    HeaderSet header;
    Stack availList = new Stack();
    ArrayList<Index> index = new ArrayList();
    ArrayList<String> registryList = new ArrayList();
    RandomAccessFile catalog = null;
    char headerDelim = ';';
}
