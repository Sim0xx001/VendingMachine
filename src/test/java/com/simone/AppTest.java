package com.simone;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    VendingMachine vm;
    
    @Before
    public void prepare(){
        vm = new VendingMachine();
    }

    @Test
    public void checkProductsLoad(){
        int num = vm.getProducts().getList().size();
        assertTrue(num != 0);
    }

    @Test
    public void checkGetList(){
        assertTrue(vm.getProducts().getList().getClass().getSimpleName().equals("Hashtable"));
    }

    @Test
    public void checkGetAndAddProduct(){
        Prodotti products = vm.getProducts();
        
        int id = 100, quantity = 10;
        String name = "test";
        float price = 9999999f;
        products.addProduct(id, name, price, quantity);

        Prodotto product = products.getProduct(id);
        assertTrue(product.getId() == id);
    }

    @Test
    public void checkModQauntity(){
        int id = 1, quantity1, quantity2;

        quantity1 = vm.getProducts().getProduct(id).getQuantity();

        vm.getProducts().modQuantity(id);
        quantity2 = vm.getProducts().getProduct(id).getQuantity();

        assertTrue(quantity2 < quantity1);
    }

    @Test
    public void checkCoinInsert(){
        float val = 1f;
        boolean coin = vm.state.insertMoney(val);
        assertTrue(!coin);
        assertTrue(vm.credit == val);
    }

    @Test
    public void checkProductSelection(){
        vm.state = new Selection(vm, 4f);
        int res = vm.state.selectProduct(1, vm);
        assertTrue(res == 0 || res == 1);
    }

    @Test
    public void checkProductSelectionAbort(){
        float credit = 4f;
        vm.state = new Selection(vm, credit);
        float res = vm.state.abort();
        assertTrue(vm.state.getClass().getSimpleName().equals("Monete"));
        assertTrue(res == credit);
    }

    @Test
    public void checkChange(){
        float credit = 4f, price = 1f;
        vm.state = new Resto(vm, credit, price);
        float change = vm.state.giveChange();
        assertTrue(change == credit - price);
    }

    @Test
    public void checkStateChange(){
        assertTrue(vm.state.getClass().getSimpleName().equals("Monete"));

        float val = 4f;
        vm.state.insertMoney(val);

        vm.state = new Selection(vm, val);
        assertTrue(vm.state.getClass().getSimpleName().equals("Selection"));

        vm.state.selectProduct(1, vm);
        assertTrue(vm.state.getClass().getSimpleName().equals("Resto"));

        vm.state.giveChange();
        assertTrue(vm.state.getClass().getSimpleName().equals("Monete"));

    }
}
