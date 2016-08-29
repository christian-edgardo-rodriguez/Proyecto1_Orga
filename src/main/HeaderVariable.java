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
public class HeaderVariable {

    public HeaderVariable(RegistryVariable registry ,int flag1, int flag2) {
        this.amount = 0;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.registry = registry;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getFlag2() {
        return flag2;
    }

    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }

    public RegistryVariable getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryVariable registry) {
        this.registry = registry;
    }
    int amount, flag1, flag2;
    RegistryVariable registry;
}
