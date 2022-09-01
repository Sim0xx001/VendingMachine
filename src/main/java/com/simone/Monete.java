package com.simone;

import java.util.ArrayList;
import java.util.Arrays;

public class Monete extends State {
    ArrayList<Float> acceptedCoins = new ArrayList<Float>(Arrays.asList(0.05f, 0.10f, 0.20f, 0.5f, 1f, 2f, 5f, 10f, 20f));

    public Monete(VendingMachine vm){
        super(vm);
    }

    public boolean insertMoney(float val){
        boolean bool = true;
        for(float x : this.acceptedCoins)
            if(x == val){
                vm.credit += val;
                bool = false;
                break;
            }
        return bool;
    }

    public int selectProduct(int id, VendingMachine vm){return 0;}
    public float abort() {return 0;}
    public float giveChange() {return 0;};
}
