/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Norby09
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Aceasta clasa implementeaza interfata grafica si se
 *  ocupa de interactiunea dintre utilizator si aplicatie.
 *  
 */


@SuppressWarnings("serial")
public class Interfata extends JFrame implements ActionListener,MouseListener{
	private JPanel myPanel,depPanel,comPanel;		//panel ul principal si panelul coresp com si depozitului
	private JTable jDepTab,jComTab;				//tabelele pentru comenzi si departament
	private JScrollPane scr1,scr2;				//scroluri
	private DefaultTableModel dtm1,dtm2;		//tabele depozit si comenzi
	private JButton addBtn,delBtn,efBtn,findBtn,impBtn;	//butoane de add delete find efecturare si import
	private JRadioButton DepRBtn,ComRBtn;				//radio buttons pentru selectare depozit sau comenzi
	private ButtonGroup radioGr;				//grup de butoane 
	private JTextField txtFind;					//text field unde se cuta date in tabel
	private int sel;							//sel
	private Warehouse wh;						//variabila de tip depozit
	private OPDept opd;						//de tip procesare a comenzilor

	
	

public Interfata(){
		
		super("Order Management");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,500);
		this.setupComponenents();
		this.add(myPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setupListeners();
		wh=new Warehouse();
		opd=new OPDept();
		loadData();						//loadData se incarca date in tabele
		showTable();				//afiseaza continutul tabelelor
		
	}
/**
 * adauga componentele grafice
 */
	private void setupComponenents() {
		
		myPanel=new JPanel();			//adauga panelul principal
		depPanel=new JPanel();			//adauga panelul pentru elementele din depozit
		comPanel=new JPanel();			//adauga panelul pentru comenzi
		
		
	//tabele
		//Depozit
		//instantiaza capul de tabel pentru depozit printr-un TabelMode
		dtm1=new DefaultTableModel();
		dtm1.addColumn("Cod Produs");
		dtm1.addColumn("Nume Produs");
		dtm1.addColumn("Cantitate");
		dtm1.addColumn("Pret unitar");
		jDepTab=new JTable(dtm1);
		scr1=new JScrollPane(jDepTab);
		scr1.setBounds(0,0,790,300);
	
	
		//tabel Comenzi
		dtm2=new DefaultTableModel();
		dtm2.addColumn("Cod Comanda");
		dtm2.addColumn("Nume Produs");
		dtm2.addColumn("Cantitate");
		dtm2.addColumn("Pret");
		dtm2.addColumn("Nume Client");
		dtm2.addColumn("Email");
		dtm2.addColumn("Nr Tel");
		dtm2.addColumn("Status");
		jComTab=new JTable(dtm2);
		scr2=new JScrollPane(jComTab);
		scr2.setBounds(0,0,790,300);
		
		
	//butoane	
		addBtn=new JButton("Adauga");
		addBtn.setBounds(100,300,100,30);
		delBtn=new JButton("Sterge");
		delBtn.setBounds(100,330,100,30);
		efBtn=new JButton("Efectueaza");
		efBtn.setBounds(100,360,100,30);
		findBtn=new JButton("Find");
		findBtn.setBounds(620,300,60,30);
		impBtn=new JButton("Import");
		impBtn.setBounds(620,350,100,30);
		DepRBtn=new JRadioButton("Depozit",true);
		DepRBtn.setBounds(20,300,80,40);
		ComRBtn=new JRadioButton("Comenzi",false);
		ComRBtn.setBounds(20,340,80,40);
		radioGr=new ButtonGroup();
		radioGr.add(DepRBtn);
		radioGr.add(ComRBtn);

	//txtfield
		txtFind=new JTextField();
		txtFind.setBounds(690,300,100,30);
		
		//depPanel 
		depPanel.setLayout(null);
		depPanel.setBounds(0,0,800,300);
		depPanel.add(scr1);
		
		//comPanel
		comPanel.setLayout(null);
		comPanel.setBounds(0,0,800,300);
		comPanel.add(scr2);
		
		//myPanel
		myPanel.setLayout(null);
		myPanel.add(addBtn);
		myPanel.add(delBtn);
		myPanel.add(efBtn);
		myPanel.add(findBtn);
		myPanel.add(impBtn);
		myPanel.add(DepRBtn);
		myPanel.add(ComRBtn);
		myPanel.add(txtFind);
		myPanel.add(depPanel);
		myPanel.add(comPanel);
		
		
	}

	
/**
 * seteaza listenerii pt butoane si tabele
 */
	private void setupListeners() {
	addBtn.addActionListener(this);
	delBtn.addActionListener(this);
	efBtn.addActionListener(this);
	findBtn.addActionListener(this);
	impBtn.addActionListener(this);
	jDepTab.addMouseListener(this);
	jComTab.addMouseListener(this);
	DepRBtn.addActionListener(this);
	ComRBtn.addActionListener(this);
	
}
	/**
	 * afiseaza tabelul corespunzator
	 */
	private void showTable() {
		if(DepRBtn.isSelected()==true){
			efBtn.setEnabled(false);
			depPanel.setVisible(true);
		}
			
		else
			depPanel.setVisible(false);
		if(ComRBtn.isSelected()==true){
			efBtn.setEnabled(true);
			comPanel.setVisible(true);
		}
			
		else
			comPanel.setVisible(false);
	
}
	
	
	/**
	 * creaza fereastra pentru introducerea datelor pentru un produs nou
	 */
	private void introTab1(){
		final JDialog intro1=new JDialog(this,"Date Produs");
		
		//JLabels
		JLabel lNume=new JLabel("Nume produs:");
		lNume.setBounds(10,10,80,30);
		JLabel lCant=new JLabel("Cantitate:");
		lCant.setBounds(10,40,80,30);
		JLabel lPret=new JLabel("Pret unitar:");
		lPret.setBounds(10,70,80,30);
		
		
		//JTextFields
		final JTextField tNume=new JTextField();
		tNume.setBounds(90,15,130,20);
		final JTextField tCant=new JTextField();
		tCant.setBounds(90,45,130,20);
		final JTextField tPret=new JTextField();
		tPret.setBounds(90,75,130,20);
	
		
		//Buton
		JButton okBtn=new JButton("OK");
		okBtn.setBounds(100,100,55,30);
		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Order ord;
				String[] rand=new String[4];
			try{	
				rand[1]=tNume.getText();		//introduce un nou produs in baza de date 
				rand[2]=tCant.getText();		//cu elementele nume cantitate pret
				rand[3]=tPret.getText();
				if(Integer.parseInt(rand[2])<=0) throw new Exception();			//daca nu se introduce o valoare 
				if(Integer.parseInt(rand[3])<=0) throw new Exception();			//numerica se arunca o exceptie
				Product p=new Product(rand);									
				rand[0]=Integer.toString(p.getCod());					//se pune codul produsului
				addRow(getTableModel(),rand);							//se adauga val vectorului rand in tabel
				wh.getTreeMap().put(p.getNume(),p);						//se pune in depozit produsul nou intodus
				for(int i=0;i<dtm2.getRowCount();i++){						//se parcurg elementele din depozit pana se termina randurile de numarat
					if((jComTab.getValueAt(i,1)).equals(rand[1])){			//se cauta in map tree daca mai exista prod cu 
						ord=opd.cauta(Integer.parseInt( (String) jComTab.getValueAt(i,0)));	//acelasi nume, daca exista se face 
						ord.setProd(p);				//se seteaza produsul p;										//un update la statusul acelui produs
						ord.updateStatus();
					}	
				}
				intro1.setVisible(false);		//se inchide panelul de introducere a datelor dupa ok
				wh.serialization();			//are loc serializarea la nivelul celor 2 fisiere. 
				opd.serialization();
				updateTables();				//dupa serializare se face un update la nivelul tabelelor
				
			}
			catch(Exception e1){			//eroare in cazul in care datele au fost introduse gresit
				JOptionPane.showMessageDialog(myPanel,
						"Date introduse gresit!" +
						"\nCantitatea si Pretul trebuie sa aiba \nvalori numerice strict pozitive",         
						"Eroare",
						JOptionPane.ERROR_MESSAGE);
			}
			}

		});
		
		intro1.setSize(250, 200);
		intro1.setResizable(false);
		intro1.setLayout(null);
		intro1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		intro1.setVisible(true);
		intro1.add(lNume);
		intro1.add(lCant);
		intro1.add(lPret);
		intro1.add(tNume);
		intro1.add(tCant);
		intro1.add(tPret);
		intro1.add(okBtn);
	}
	
	/**
	 * creaza fereastra pentru introducerea unei noi comenzi
	 */
	private void introTab2(){
		final JDialog intro2=new JDialog(this,"Date comanda");
	
	//JLabels
	JLabel lNumeP=new JLabel("Nume produs:");
	lNumeP.setBounds(10,10,80,30);
	JLabel lCant=new JLabel("Cantitate:");
	lCant.setBounds(10,40,80,30);
	JLabel lNumeC=new JLabel("Nume client:");
	lNumeC.setBounds(10,70,80,30);
	JLabel lEmail=new JLabel("Email");
	lEmail.setBounds(10,100,80,30);
	JLabel lNrTel=new JLabel("Nr. tel:");
	lNrTel.setBounds(10,130,80,30);
	
	
	//JTextFields
	final JTextField tNumeP=new JTextField();
	tNumeP.setBounds(90,15,130,20);
	final JTextField tCant=new JTextField();
	tCant.setBounds(90,45,130,20);
	final JTextField tNumeC=new JTextField();
	tNumeC.setBounds(90,75,130,20);
	final JTextField tEmail=new JTextField();
	tEmail.setBounds(90,105,130,20);
	final JTextField tNrTel=new JTextField();
	tNrTel.setBounds(90,135,130,20);
	
	
	//Buton
	JButton okBtn=new JButton("OK");
	okBtn.setBounds(100,160,55,30);
	okBtn.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] rand=new String[8];
		try{
			rand[1]=tNumeP.getText();
			rand[2]=tCant.getText();
			rand[4]=tNumeC.getText();
			rand[5]=tEmail.getText();
			rand[6]=tNrTel.getText();
			if(Integer.parseInt(rand[2])<=0) throw new Exception();
			if(Integer.parseInt(rand[6])<=0) throw new Exception();
			Order ord=new Order(rand,wh);
			rand[0]=Integer.toString(ord.getCod());
			rand=setStatusCol(ord,rand);
			addRow(getTableModel(),rand);
			opd.getTreeMap().put(ord.getCod(),ord);
			intro2.setVisible(false);
			opd.serialization();
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(myPanel,
					"Date introduse gresit!" +
					"\nCantitatea si Nr.Tel trebuie sa aiba \nvalori numerice strict pozitive",         
					"Eroare",
					JOptionPane.ERROR_MESSAGE);
		}
		}

	});
	
	intro2.setSize(250, 300);
	intro2.setResizable(false);
	intro2.setLayout(null);
	intro2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	intro2.setVisible(true);
	intro2.add(lNumeC);
	intro2.add(lEmail);
	intro2.add(lNrTel);
	intro2.add(lNumeP);
	intro2.add(lCant);
	intro2.add(tNumeC);
	intro2.add(tEmail);
	intro2.add(tNrTel);
	intro2.add(tNumeP);
	intro2.add(tCant);
	intro2.add(okBtn);
	
	}
	/**
	 * incarca datele din fisere
	 */
	private void loadData(){
		String[] rand;
		Product p;
		Order o;
		wh.deserialization();
		Set<?> set = wh.getTreeMap().entrySet(); //se introduc datele din tree map din fisier
		Iterator<?> i = set.iterator(); 			//iteratorul ia locul unei enumeratii
		while(i.hasNext()) { 
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry)i.next(); 
				p=(Product) me.getValue();
		    	rand=new String[]{Integer.toString(p.getCod()),p.getNume(),Integer.toString(p.getCant()),Integer.toString(p.getPretU())};
		    	addRow(dtm1,rand);
		    }
		opd.deserialization();
		set = opd.getTreeMap().entrySet(); 
		i = set.iterator(); 
		while(i.hasNext()) { 
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry)i.next(); 
				o=(Order) me.getValue();
				rand=new String[]{Integer.toString(o.getCod()),o.getNumeProd(),Integer.toString(o.getCant()),Integer.toString(o.getPret()),o.getCust().getNume(),o.getCust().getEmail(),o.getCust().getNrTel(),Integer.toString(o.isStatus())};
				rand=setStatusCol(o,rand);
				addRow(dtm2,rand);
		    }
	}
	/**
	 * redeseneaza tabelele in conformitate cu continutul TreeMap-urilor
	 */
	private void updateTables(){
		dtm1.getDataVector().removeAllElements();
		dtm2.getDataVector().removeAllElements();
		loadData();
	}
	/**
	 * Scrie in coloane diferite mesaje referitoare la statusul comenzii
	 * @param o - obiect Order
	 * @param rand - String[] ce contine randul din tabel
	 * @return returneaza String[] cu randul din tabel modificat
	 */
	private String[] setStatusCol(Order o,String[] rand){
		if(o.getPret()==-1)
			rand[3]="-";
		else
			rand[3]=Integer.toString(o.getPret());
		if(o.isStatus()==1)
			rand[7]="Se poate efectua";
		else
			if(o.isStatus()==0)
				rand[7]="Produs indisponibil";
			else
				rand[7]="Depasire cantitate";
		return rand;
	}
	/**
	 * adauga rand nou in  tabel
	 * @param dtm - DefaultTableModel
	 * @param rand - String[] ce contine randul ce va fi adaugat
	 */
	private void addRow(DefaultTableModel dtm,String[] rand){
		dtm.addRow(rand);
	}
	/**
	 * sterge randurile selecatate din tabel si obiectele corespunzatoare din TreeMap
	 * @param dtm - DefaultTableModel
	 * @param jTab - tabelul
	 */
	private void delSelRow(DefaultTableModel dtm,JTable jTab){
		Object s;
		
		for(int i=0; i<=jTab.getRowCount() ; i++ ) {
			if(getTable().isRowSelected(i)){
				if(getTable()==jDepTab){
					s=jDepTab.getValueAt(i,1);
					wh.getTreeMap().remove(s);
				}
				else{
					s=jComTab.getValueAt(i,0);
					opd.getTreeMap().remove(Integer.parseInt((String) s));
				}
			}
			
		}
		for(int i=0; i<sel ; i++ ) {
			dtm.removeRow(jTab.getSelectedRow());	
		}	
	
	}

	/**
	 * 
	 * @return returneaza DefaultTableModel-ul curent
	 */
	private DefaultTableModel getTableModel(){
		if(depPanel.isVisible()==true)
			return dtm1;
		else
			return dtm2;
	}
	/**
	 * 
	 * @return returneaza tabelul curent
	 */
	private JTable getTable(){
		if(depPanel.isVisible()==true){
			
			return jDepTab;	
		}
			
		else{
			
			return jComTab;
		}
			
	}
	/**
	 * trateaza evenimentele referitoare la butoane
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==addBtn){
			
			if(getTableModel()==dtm1)
				introTab1();		
			else
				introTab2();
			
		}
		if(e.getSource()==delBtn){

			delSelRow(getTableModel(),getTable());
		

			opd.serialization();
			wh.serialization();
		}	
		if(e.getSource()==efBtn){                                   //efectuarea unei comenzi
			for(int i=0; i<=getTable().getRowCount() ; i++ ) {
				if(getTable().isRowSelected(i)){
					Object s = jComTab.getValueAt(i,0);
				    Order ord=(Order) opd.getTreeMap().get(Integer.parseInt((String) s));
					if(ord.isStatus()==1){
						Product p=(Product) wh.getTreeMap().get(ord.getNumeProd());
						p.setCant(p.getCant()-ord.getCant());      //se scade din depozit cantitatea livrata
						for(int j=0;j<jDepTab.getRowCount();j++ ) {
							if(jDepTab.getValueAt(j,1)==p.getNume())
								if(p.getCant()>0)
									jDepTab.setValueAt(p.getCant(), j, 2);
								else
									{
									dtm1.removeRow(j);                        //se sterge produsul din depozit daca cantitatea=0
									wh.getTreeMap().remove(p.getNume());
									}
						}
						opd.getTreeMap().remove(Integer.parseInt((String) s));
						getTableModel().removeRow(i);
					}
					else
						JOptionPane.showMessageDialog(myPanel,
								"Comanda nu poate fi efectuata!",        
								"Eroare",
								JOptionPane.ERROR_MESSAGE);
					
				}
			}
			opd.serialization();
			wh.serialization();
		}
		if(e.getSource()==findBtn){
			updateTables();
			String find,s;
			boolean gasit=false;
			find=txtFind.getText();
			for(int i=0;i<getTable().getRowCount();i++){
				gasit=false;
				for(int j=1;j<getTable().getColumnCount();j++){
					s=(String) getTable().getValueAt(i, j);
					if(s.indexOf(find)!=-1){
						gasit=true;
					}
				}
				if(gasit==false){
					getTableModel().removeRow(i);
					i--;
				}
				
			}
			
		}
		
		if(e.getSource()==impBtn){             //import
			JFileChooser fc= new JFileChooser();
			if(fc.showOpenDialog(Interfata.this)==JFileChooser.APPROVE_OPTION)
			{
				File file=fc.getSelectedFile();
				if(getTable()==jDepTab)
					wh.deserialization(file);
				else
					opd.deserialization(file);
			}
		}
		
		if(e.getSource()==DepRBtn){
			showTable();
			updateTables();
		}
		if(e.getSource()==ComRBtn){
			showTable();
			updateTables();
		}
		
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(depPanel.isVisible()==true)
			sel=jDepTab.getSelectedRows().length;
		else
			sel=jComTab.getSelectedRows().length;
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	





}
