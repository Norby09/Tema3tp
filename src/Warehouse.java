/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norby09
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * clasa defineste un depozit
 * 
 *
 */
@SuppressWarnings("serial")
public class Warehouse implements Serializable {
	private TreeMap<String,Product> tmProd=new TreeMap<String, Product>();
	
	public Warehouse(){
		
	}
	/**
	 * 
	 * @return returneaza TreeMap-ul cu produsele din depozit
	 */
	public TreeMap<String, Product> getTreeMap(){
		return this.tmProd;
	}
	/**
	 * metoda folosita pentru serializare
	 */
	public void serialization(){
		try { 
		FileOutputStream fos = new FileOutputStream("warehouse"); 
		ObjectOutputStream oos = new ObjectOutputStream(fos); 
		oos.writeObject(tmProd); 
		oos.flush(); 
		oos.close(); 
		} 
		catch(Exception e) { 
			JOptionPane.showMessageDialog(null,
					"Eroare salvare date!",         
					"Eroare",
					JOptionPane.ERROR_MESSAGE);
		} 
	}
	/**
	 * metoda folosita pentru deserializare 
	 * @param file - fisierul sursa
	 */
	@SuppressWarnings("unchecked")
	public void deserialization(File file){
		try {
			FileInputStream fis = new FileInputStream(file); 
		ObjectInputStream ois = new ObjectInputStream(fis); 
		tmProd = (TreeMap<String, Product>)ois.readObject(); 
		ois.close(); 
		serialization();
		} 
		catch(Exception e) { 
			JOptionPane.showMessageDialog(null,
					"Eroare incarcare date!\nFisier sursa incorect",         
					"Eroare",
					JOptionPane.ERROR_MESSAGE);
		} 
	} 
		
	/**
	 * metoda folosita pt deserializare implicita
	 */
	@SuppressWarnings("unchecked")
	public void deserialization(){
		try {
		FileInputStream fis = new FileInputStream("warehouse"); 
		ObjectInputStream ois = new ObjectInputStream(fis); 
		tmProd = (TreeMap<String, Product>)ois.readObject(); 
		ois.close(); 
		} 
		catch(Exception e) { 
			JOptionPane.showMessageDialog(null,
					"Eroare incarcare date!\nFisierul sursa nu a fost gasit\nFolositi Import",         
					"Eroare",
					JOptionPane.ERROR_MESSAGE);
		} 
	} 
	/**
	 * metoda pt gasirea produsului dupa nume
	 * @param nume - numele produsului
	 * @return returneaza obiectul Product
	 */
	public Product numeToProduct(String nume){
		Product p=tmProd.get(nume);
		return p;
		
	}

}

