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
public class Header {

    public Header() {
    }

    public Header(RegistrySet registry) {
        this.amount = 0;
        this.registry = registry;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RegistrySet getRegistry() {
        return registry;
    }

    public void setRegistry(RegistrySet registry) {
        this.registry = registry;
    }
    int amount;
    RegistrySet registry;
}
