/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norby09
 */

import java.io.Serializable;

/**
 * clasa defineste un produs
 * 
 *
 */
@SuppressWarnings("serial")
public class Product implements Serializable{
	private int cod;
	private String nume;
	private int cant;
	private int pretU;
	

	public Product(String[] rand){
		this.cod=this.hashCode();		//cod de tip hashcode
		this.nume=rand[1];
		this.cant=Integer.parseInt(rand[2]);
		this.pretU=Integer.parseInt(rand[3]);
	}


/**
 * 
 * @return returneaza codul produsului
 */
	public int getCod() {
		return cod;
	}

/**
 * returneaza numele produsului
 * @return
 */
	public String getNume() {
		return nume;
	}
	/**
	 * returneaza cantitatea
	 * @return
	 */
	public int getCant() {
		return cant;
	}
/**
 * stabileste cantitatea
 * @param cant
 */
	public void setCant(int cant) {
		this.cant = cant;
	}
	/**
	 * 
	 * @return returneaza pretul unitar
	 */
	public int getPretU() {
		return pretU;
	}
	
	

}


