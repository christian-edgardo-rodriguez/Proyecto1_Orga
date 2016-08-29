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
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class DocumentVariable extends Document {

    public DocumentVariable(HeaderVariable header, String name) {
        super(name);
        this.header = header;
        file = new File("./" + name + ".txt");
    }

    public HeaderVariable getHeader() {
        return header;
    }

    public void setHeader(HeaderVariable header) {
        this.header = header;
    }

    public LinkedList getAvailList() {
        return availList;
    }

    public void setAvailList(LinkedList availList) {
        this.availList = availList;
    }

    public ArrayList<Index> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<Index> index) {
        this.index = index;
    }

    public RandomAccessFile getCatalog() {
        return catalog;
    }

    public void setCatalog(RandomAccessFile catalog) {
        this.catalog = catalog;
    }

    public char getHeaderDelim() {
        return headerDelim;
    }

    public void setHeaderDelim(char headerDelim) {
        this.headerDelim = headerDelim;
    }
    
    void add(RegistryVariable tempReg) {
        int flagReg = header.flag1; 
        int flagFi = header.flag2;
        try {
            catalog = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR, CANT OPEN");
            ex.printStackTrace();
        }
        String insert = "";
        if (flagReg == 0) {
            insert += "#";
            if (flagFi== 0) {
                insert += delim(tempReg);
            } else if (flagFi== 1) {
                insert += iden(tempReg);
            } else if (flagFi == 2) {
                insert += key(tempReg, header);
            }
        } else if (flagReg == 1) {
            String salida;
            insert = "[";
            if (flagFi == 0) {
                insert += delim(tempReg);
            } else if (flagFi == 1) {
                insert += iden(tempReg);
            } else if (flagFi == 2) {
                insert += key(tempReg, header);
            }
            salida = insert.length() - 1 + insert;
            insert = salida;
        }
        if (registryList.isEmpty()) {
            String meta = flagReg + "/" + flagFi;
            meta += "/";
            for (int i = 0; i < header.getRegistry().getVariableFieldList().size(); i++) {
                if (header.getRegistry().getVariableFieldList().get(i).isKey()) {
                    meta += "K/";
                } else {
                    meta += "N/";
                }
                if (header.getRegistry().getVariableFieldList().get(i).foreign) {
                    meta += "K/";
                } else {
                    meta += "N/";
                }
                meta += header.getRegistry().getVariableFieldList().get(i).getName() + "/";
                meta += header.getRegistry().getVariableFieldList().get(i).getType() + "/";
                meta += ";";
            }
            meta += "%";
            try {
                catalog.seek(0);
                catalog.writeBytes(meta);
                int offset = (int) catalog.getFilePointer();
                Index indice_temp = new Index(index.size() + 1, offset);
                index.add(indice_temp);
                catalog.seek(offset);
                catalog.writeBytes(insert);
                try {
                    insert = insert.replaceAll("[", "");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"ERROR, CANT WRITE");
                    e.printStackTrace();
                }
                registryList.add(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, DOCUMENT WONT LOAD");
                ex.printStackTrace();
            }
        } else if (availList.isEmpty()) {
            try {
                catalog.seek(catalog.length());
                int offset = (int) catalog.getFilePointer();
                Index indice_temp = new Index(index.size() + 1, offset);
                index.add(indice_temp);
                catalog.writeBytes(insert);
                try {
                    insert = insert.replaceAll("[", "");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"ERROR, WONT ADD");
                    e.printStackTrace();
                }
                registryList.add(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, DOCUMENT WONT LOAD");
                ex.printStackTrace();
            }
        } else {
            try {
                int pos = -1;
                for (int i = 0; i < availList.size(); i++) {
                    try {
                        int size = index.get((int) availList.get(i) + 1).offset - index.get((int) availList.get(i)).offset;
                        if (size >= insert.length()) {
                            pos = index.get((int) availList.get(i)).id;
                            availList.remove(i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"ERROR, CANT WRITE");
                        e.printStackTrace();
                    }
                }
                int value = 0;
                if (pos == -1) {
                    value = (int)catalog.length();
                } else {
                    value = index.get(pos - 1).getOffset();
                }
                catalog.seek(value);
                catalog.writeBytes(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, CANT WRITE");
                ex.printStackTrace();
            }
        }
    }

    void show() {
        try {
            catalog = new RandomAccessFile(file, "rw");
            catalog.seek(0);
            int RegType = (int) catalog.read();
            catalog.seek(2);
            int FieldType = (int) catalog.read();
            for (int i = 0; i < index.size(); i++) {
                String temp = "";
                int offset = index.get(i).getOffset();
                catalog.seek(offset);
                if ((char) catalog.read() != '*') {
                    int marked = 0;
                    if (index.get(i).id < index.size()) {
                        marked = index.get(i + 1).offset - 1;
                    } else {
                        marked = (int) (catalog.length() - 1);
                    }
                    if (RegType == 0) {
                        if (FieldType == 0) {
                            boolean markErased = false;
                            for (int j = offset; j < marked; j++) {
                                char check = (char) catalog.read();
                                if (check == '*') {
                                    markErased = true;
                                } else if (check == '{') {
                                    markErased = false;
                                }
                                if (markErased == false) {
                                    if (check != '{' && check != '#') {
                                        temp += check;
                                    } else {
                                        temp += " ";
                                    }
                                }
                            }
                            temp += "\n";
                        } else if (FieldType == 1) {
                            boolean erased = false;
                            for (int j = offset; j < marked; j++) {
                                char check = (char) catalog.read();
                                if (check == '*') {
                                    erased = true;
                                } else if (check == '#') {
                                    erased = false;
                                }
                                if (erased == false) {
                                    if (check == '{') {
                                        if (temp.length() > 0) {
                                            temp = temp.substring(0, temp.length() - 1);
                                            temp += " ";
                                        }
                                    } else {
                                        temp += check;
                                    }
                                }
                            }
                            temp += "\n";
                        } else if (FieldType == 2) {
                            boolean test = true;
                            boolean deleted = false;
                            for (int j = offset; j < marked; j++) {
                                char check = (char) catalog.read();
                                if (check == '*') {
                                    deleted = true;
                                } else if (check == '[') {
                                    deleted = false;
                                }
                                if (deleted == false) {
                                    if (check == '=' || check == '|') {
                                        test = !test;
                                        temp += " ";
                                    }
                                    if (test == false && check != '=' && check != '|') {
                                        temp += check;
                                    }
                                }
                            }
                            temp += "\n";
                        }
                    } else if (RegType == 1) {
                        if (FieldType == 0) {
                            char check;
                            int counter = 0;
                            for (int j = offset; j < marked; j++) {
                                check = (char) catalog.read();
                                if (check == '|') {
                                    counter++;
                                    temp += " ";
                                } else if (check != '*') {
                                    if (counter != 0) {
                                        temp += check;
                                    }
                                }
                            }
                            temp += "\n";
                        } else if (FieldType == 1) {
                            boolean deleted = false;
                            for (int j = offset; j < marked; j++) {
                                char check = (char) catalog.read();
                                if (check == '*') {
                                    deleted = true;
                                } else if (check == '[') {
                                    deleted = false;
                                }
                                if (deleted == false) {
                                    if (check == '|') {
                                        if (temp.length() > 0) {
                                            temp = temp.substring(0, temp.length() - 1);
                                            temp += " ";
                                        }
                                    } else {
                                        temp += check;
                                    }
                                }
                            }
                            temp += "\n";
                        } else if (FieldType == 2) {
                            boolean test = true;
                            for (int j = offset; j < marked; j++) {
                                char check = (char) catalog.read();
                                if (check == '=' || check == '|') {
                                    test = !test;
                                    temp += " ";
                                }
                                if (test == true && check != '=' && check != '|') {
                                    temp += check;
                                }
                            }
                            temp += "\n";
                        }
                    }
                    if (!temp.contains("/")) {
                        JOptionPane.showMessageDialog(null,index.get(i).id + ". " + temp);
                    }
                }
            }
            catalog.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Hubo un error en el archivo");
        }
    }

    void delete(int number) {
        if (number > index.size()) {
            JOptionPane.showMessageDialog(null,"Numero ingresado no es valido");
        } else {
            for (int i = 0; i < index.size(); i++) {
                if (index.get(i).getId() == number) {
                    try {
                        catalog = new RandomAccessFile(file, "rw");
                        int marked = 0;
                        if (index.get(i).id < index.size()) {
                            marked = index.get(i + 1).offset;
                        } else {
                            marked = (int) catalog.length();
                        }
                        int offset = index.get(i).getOffset();
                        catalog.seek(offset);
                        try {
                            for (int j = offset; j < marked; j++) {
                                catalog.writeBytes("*");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"ERROR,CANT ERASE");
                            e.printStackTrace();
                        }
                        try {
                            registryList.remove(number - 1);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"ERROR,CANT ERASE");
                        }
                        catalog.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,"ERROR, DOCUMENT WONT LOAD");
                    }
                    availList.push(i);
                }
            }
        }
    }
    
    void modify(int number, RegistryVariable newRegistry) {
        delete(number);
        add(newRegistry);
    }

    void load(RegistryVariable tempReg) {
        try {
            catalog = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERROR, CANT LOAD");
            ex.printStackTrace();
        }
        String insert = "";
        for (int i = 0; i < tempReg.getVariableFieldList().size(); i++) {
            insert += tempReg.getVariableFieldList().get(i).content;
        }
        if (registryList.isEmpty()) {
            try {
                int offset = (int) catalog.getFilePointer();
                Index indice_temp = new Index(index.size() + 1, offset);
                index.add(indice_temp);
                registryList.add(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, CANT LOAD");
                ex.printStackTrace();
            }
        } else if (availList.isEmpty()) {
            try {
                catalog.seek(catalog.length());
                int offset = (int) catalog.getFilePointer();
                Index indice_temp = new Index(index.size() + 1, offset);
                index.add(indice_temp);
                registryList.add(insert);
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, CANT LOAD");
            }
        } else {
            try {
                int pos;
                for (int i = 0; i < availList.size(); i++) {
                    try {
                        int size = index.get((int) availList.get(i) + 1).offset - index.get((int) availList.get(i)).offset;
                        if (size >= insert.length()) {
                            pos = index.get((int) availList.get(i)).id;
                            availList.remove(i);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"ERROR");
                    }
                }
                catalog.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"ERROR, DOCUMENT WONT LOAD");
            }
        }
    }

    void find(int busqueda) {
        if (busqueda > index.size()) {
            JOptionPane.showMessageDialog(null,"ERROR, NUMBER ISNT VALID");
        } else {
            for (int i = 0; i < index.size(); i++) {
                if (index.get(i).getId() == busqueda) {
                    try {
                        catalog = new RandomAccessFile(file, "rw");
                        String temp = "";
                        int offset = index.get(i).getOffset();
                        catalog.seek(offset);
                        if ((char) catalog.read() != '*') {
                            catalog.seek(offset);
                            for (int j = 0; j < header.getRegistry().length; j++) {
                                char value = (char) catalog.read();
                                temp += value + "";
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"ERROR, DOESNT EXIST");
                        }
                        catalog.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"ERROR, DOCUMENT WONT LOAD");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    String delim(RegistryVariable tempReg) {
        String insert = "{";
        ArrayList<FieldVariable> temp = tempReg.getVariableFieldList();
        for (int i = 0; i < temp.size(); i++) {
            insert += ((FieldVariable) temp.get(i)).content + "{";
        }
        return insert;
    }

    String iden(RegistryVariable tempREg) {
        String insert = "";
        ArrayList<FieldVariable> temp = tempREg.getVariableFieldList();
        for (int i = 0; i < temp.size(); i++) {
            insert += ((FieldVariable) temp.get(i)).content.length() + "{" + ((FieldVariable) temp.get(i)).content;
        }
        return insert;
    }

    String key(RegistryVariable tempReg, HeaderVariable header) {
        String record = "";
        for (int i = 0; i < header.getRegistry().getVariableFieldList().size(); i++) {
            record += header.getRegistry().getVariableFieldList().get(i).getName();
            record += "=" + tempReg.getVariableFieldList().get(i).getContent() + "[";
        }
        return record;
    }
    HeaderVariable header;
    LinkedList availList = new LinkedList();
    ArrayList<Index> index= new ArrayList();
    RandomAccessFile catalog = null;
    char headerDelim = ';';
}
