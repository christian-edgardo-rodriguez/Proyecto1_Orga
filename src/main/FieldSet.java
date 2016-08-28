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

    public FieldSet(String name, String content, boolean key, boolean foreign, char type, int length) {
        super(name,type,key,foreign);
        this.content = content;
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
    String content;
    int length;
}
