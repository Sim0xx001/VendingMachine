package com.simone;

public class VendingMachine {
    private Prodotti products;
    public State state = new Monete(this);
    public float credit;

    public VendingMachine(){
        this.products = new Prodotti();
    }

    public Prodotti getProducts(){
        return this.products;
    }
}
