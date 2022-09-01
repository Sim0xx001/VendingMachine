package com.simone;

import java.util.Scanner;

public class App {
	
	private static void clearScreen(boolean val, VendingMachine vm, float credit) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(val){
            System.out.println("Available products:");
            vm.getProducts().printList();
            System.out.println("\nCredit: " + credit);
			System.out.println();
        }
    }

    private static int abort(VendingMachine vm){
        float change = vm.credit;
        vm.credit = 0;
        vm.state.abort();
        clearScreen(true, vm, 0);
        System.out.println("Change: " + change);
        return 1;
    }		
	public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

		String input;
		boolean value = true;
		Scanner sc = new Scanner(System.in);

        clearScreen(true, vm, vm.credit);
		while(value) {
            
            // insert money
            boolean error = false;
            while(true){
                System.out.println("In order to stop adding money enter 0");

                input = sc.nextLine();
                float parseInput = Float.parseFloat(input);
                if(parseInput == 0) break;

                error = vm.state.insertMoney(parseInput);

                clearScreen(true, vm, vm.credit);

                if(error)
                    System.out.println("Please insert a valid coin");
            }

            if(!error){
                vm.state = new Selection(vm, vm.credit);
            }

            // select product and abort operation
            boolean fail = false;
            int parseInput = 0;
            int cases = 0;
            while(true){
                clearScreen(true, vm, vm.credit);

                if(fail)
                    System.out.println("Option not in menu, please try again");

                System.out.println("1 -> Select a product\n2 -> Abort operation");
                input = sc.nextLine();
                parseInput = Integer.parseInt(input);

                if(parseInput == 1) {
                    clearScreen(true, vm, vm.credit);
                    System.out.println("Enter 0 to abort operation");
                    System.out.println("Choose a product:");
                    input = sc.nextLine();
                    clearScreen(true, vm, vm.credit);
                    int id = Integer.parseInt(input);

                    if(id == 0){
                        cases = abort(vm); 
                        break;
                    }
                    cases = vm.state.selectProduct(id, vm);
                    break;
                } else if(parseInput == 2){
                    cases = abort(vm);                    
                    break;
                } else fail = true;
            }

            // give change

            switch(cases){
                case 0:
                    float change = vm.state.giveChange();
                    System.out.println("Change: " + change);
                    break;

                case 1:
                case 2:
                    break;
                    
            }
		}
		sc.close();
	}
}
