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
public class FieldVariable extends Field{

    public FieldVariable(String name, char type, boolean key, boolean foreign) {
        super(name, type, key, foreign);
    }

    public FieldVariable(String content, String name, char type, boolean key, boolean foreign) {
        super(name, type, key, foreign);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    String content;
}
