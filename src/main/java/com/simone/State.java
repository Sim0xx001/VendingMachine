package com.simone;

public abstract class State {
    public VendingMachine vm;

    public abstract boolean insertMoney(float val); // Monete
    public abstract int selectProduct(int id, VendingMachine vm); // Selection
    public abstract float abort(); // Selection
    public abstract float giveChange(); // Resto

    public State(VendingMachine vm){
        this.vm = vm;
    }
}
