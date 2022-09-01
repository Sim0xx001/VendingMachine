package com.simone;

public class Resto extends State {
    public float credit;
    public float price;

    public Resto(VendingMachine vm, float credit, float price){
        super(vm);
        this.credit = credit;
        this.price = price;
    }

    public float giveChange() {
        vm.credit = 0;
        vm.state = new Monete(vm);
        return this.credit - this.price;
    };

    public boolean insertMoney(float val){return false;}
    public int selectProduct(int id, VendingMachine vm){return 0;}
    public float abort() {return 0;}
}
