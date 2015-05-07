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
 * clasa defineste o comanda
 *
 *
 */
@SuppressWarnings("serial")
public class Order implements Serializable{
	private int cod;
	private Customer cust;
	private Product prod;
	private String numeProd;
	private int cant;
	private int pret;
	private int status;//1-comanda se poate efectua,0-produs indisp,2-depasire cantitate
	
	/*
	 * constructorul clasei order
	 */
	public Order(String[] rand,Warehouse wh){
		this.cod=this.hashCode();			//important pentru eficienta hashTabelurilor
		this.cust=new Customer(rand[4],rand[5],rand[6]);		//parametrii elementului de timp customer retinuti in vectori
		this.numeProd=rand[1];
		this.cant=Integer.parseInt(rand[2]);
		this.prod=wh.numeToProduct(numeProd);
		updateStatus();
	}
	/**
	 * restabileste statusul comenzii
	 */
	public void updateStatus(){
		if(prod==null){
			this.pret=-1;
			this.status=0;
		}
		
		else{
			this.pret=cant*prod.getPretU();	
			this.status=1;
		}
		if(this.status==1&&this.cant>prod.getCant())
			this.status=2;
		
	}
/**
 * 
 * @return returneaza codul
 */
	public int getCod() {
		return cod;
	}

/**
 * 
 * @return returneaza clientul care a facut comanda
 */
	public Customer getCust() {
		return cust;
	}
/**
 * stabileste produsul
 * @param prod - produsul
 */
	public void setProd(Product prod) {
		this.prod = prod;
	}

/**
 * 
 * @return returneaza numele produsului
 */
	public String getNumeProd() {
		return numeProd;
	}
/**
 * 
 * @return returneaza cantitatea
 */
	public int getCant() {
		return cant;
	}
/**
 * returneaza statusul
 * @return
 */
	public int isStatus() {
		return status;
	}
/**
 * returneaza pretul
 * @return
 */
	
	public int getPret() {
		return pret;
	}
	
	

}

