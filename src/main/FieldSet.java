/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Christian
 */
public class FieldSet extends Field{

    public FieldSet() {
    }

    public FieldSet(String name, boolean key, char type, int length, boolean foreign) {
        super(name,type,key,foreign);
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return content;
    }

    public FieldSet(String content) {
        this.content = content;
    }
    String content;
    int length;
}
