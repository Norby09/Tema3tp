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
 * Clasa defineste un Client un client, avand ca atribute numele, adresa de
 * e-mail si numarul de telefon al acestuia. Metodele continute sunt getterele
 * si setterele pentru atribute.
 * 
 * @author Veja Alina Andreea
 * 
 */
@SuppressWarnings("serial")
public class Customer implements Serializable {

	private String nume;
	private String email;
	private String nrTel;

	
	/**
	 * Aceasta metoda este constructorul cu parametrii al clasei Customer
	 * @param 1 -nume
	 * @param2 -email
	 * @param3	-nrTel
	 */
	public Customer(String nume, String email, String nrTel) {
		this.nume = nume;
		this.email = email;
		this.nrTel = nrTel;
	}

	/**
	 * getterul pentru nume
	 * @return returneaza numele
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * getterul pentru email
	 * returneaza adresa de Email
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * returneaza numarul de telefon
	 * getterul pentru nr de telefon
	 * @return nrTel
	 */
	public String getNrTel() {
		return nrTel;
	}

}

