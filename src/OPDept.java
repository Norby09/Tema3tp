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


@SuppressWarnings("serial")
public class OPDept implements Serializable{
	private TreeMap<Integer, Order> tmOrd=new TreeMap<Integer, Order>();
	
	/**
	 * 
	 * @return returneaza TreeMap-ul cu comenzile
	 */
	public TreeMap<Integer, Order> getTreeMap(){
		return this.tmOrd;
	}
	/**
	 * metoda folosita pentru serializare
	 */
	public void serialization(){
		try { 
		
		FileOutputStream fos = new FileOutputStream("order"); 
		ObjectOutputStream oos = new ObjectOutputStream(fos); 
		oos.writeObject(tmOrd); 
		oos.flush(); 			//flushul este o masura de siguranta si nu e apsolut necesar
							//vreau sa fiu sigura ca datele din stream sunt memorate in fisier
		oos.close(); 		//se inchide fisierul dupa ce am scris in el
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
		tmOrd = (TreeMap<Integer, Order>)ois.readObject(); 
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
		FileInputStream fis = new FileInputStream("order"); 
		ObjectInputStream ois = new ObjectInputStream(fis); 
		tmOrd = (TreeMap<Integer, Order>)ois.readObject(); 
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
	 * metoda pentru cautarea unei comenzi cu ajutorul codului
	 * @param cod - codul
	 * @return returneaza obiect Order
	 */
	public Order cauta(int cod){
		Order o;
		o=tmOrd.get(cod);
		return o;	
	}

}
