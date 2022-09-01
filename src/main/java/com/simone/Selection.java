package com.simone;

import java.util.NoSuchElementException;

public class Selection extends State {
    public float credit;

    public Selection(VendingMachine vm, float credit){
        super(vm);
        this.credit = credit;
    }

    public int selectProduct(int id, VendingMachine vm){
        try {
            Prodotto selected = vm.getProducts().getProduct(id);
            System.out.println("You chose: " + selected.getName() + ", $" + selected.getPrice());
            if(selected.getQuantity() == 0){
                System.out.println("This product is currently not available");
                vm.state = new Resto(vm, this.credit, selected.getPrice());
                return 0;
            }

            if(this.credit < selected.getPrice()){
                System.out.println("Insufficient credit, insert more coins to buy this product");
                this.abort();
                return 1;
            }

            vm.getProducts().modQuantity(id);
            vm.state = new Resto(vm, this.credit, selected.getPrice());
            return 0;
        } catch(NullPointerException | NoSuchElementException e) {
            System.out.println("This product doesn't exist, please choose an existing product");
            return 2;
        }
    }
    public float abort(){
        System.out.println("Operation aborted");
        vm.state = new Monete(vm);
        return this.credit;
    }

    public boolean insertMoney(float val){return false;}
    public float giveChange() {return 0;};
}
