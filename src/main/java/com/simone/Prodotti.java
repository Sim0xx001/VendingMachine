package com.simone;

import java.util.Hashtable;

public class Prodotti {
	private Hashtable<Integer, Prodotto> list = new Hashtable<Integer, Prodotto>();

	public Prodotti(){
		addProduct(1, "Acqua", 0.3f, 20);
		addProduct(2, "Biscotti", 1f, 10);
		addProduct(3, "Succo d'arancia", 0.6f, 15);
	}
	
	public void addProduct(int id, String name, float price, int quantity) {
		if(this.list.containsKey(id)){
			Prodotto product = this.getProduct(id);

			product.setName(name);
			product.setPrice(price);

			quantity = product.getQuantity() + quantity;
			product.setQuantity(quantity);

			this.list.replace(id, product);
			return;
		}

		Prodotto product = new Prodotto(id, name, price, quantity);
		this.list.put(product.getId(), product);
	}
	
	public Prodotto getProduct(int id){
		return this.list.get(id);
	}

	public void modQuantity(int id){
		Prodotto product = this.getProduct(id);

		if(product.getQuantity() > 0){
			product.setQuantity(product.getQuantity() - 1);
		} else
			product.setQuantity(0);
		
		this.list.replace(id, product);
	}

	public Hashtable<Integer, Prodotto> getList(){
		return this.list;
	}

	public void printList() {
		Prodotto product;
		for(int i=1; i<this.list.size()+1; i++) {
			product = this.list.get(i);
			if(product.getQuantity() > 0)
				System.out.println(product.getId() + ", "+ product.getName() + ", $" + product.getPrice());
		}
	}
}
