/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ClassTableModel extends AbstractTableModel {

	private List<BinaryImage> list;
	private String[] columnNames = new String [] {"Image", "Class name", "Cośtam"};
	
	public ClassTableModel(List<BinaryImage> list) { //temporary constructor
		this.list = list;
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return list.size();
	}
	
	public void clear(){
		list.clear();
	}

	public Object getValueAt(int arg0, int arg1) {
		//System.out.println("getting "+arg0+"x"+arg1);
		 switch(arg1){
			 case 0:
				 return list.get(arg0).getScaledImageIcon(40, 40);
			 case 1:
				 //return new String("Class"+arg0);
				 return new String(String.valueOf(list.get(arg0).reconizedNeuron.getIdOrName()));

			 case 2:
				 return Double.toString(list.get(arg0).reconizedNeuron.similarityRatio);
		 }
		return null;
	}
	
	public void setValueAt(Object value, int row, int col) {
		list.get(row).reconizedNeuron.setName((String) value);
	}
	
	public Class<?> getColumnClass(int column) {
		if(column == 0) return ImageIcon.class;
		else return String.class;
	}
	
	public boolean isCellEditable(int row, int col){
		return col == 1;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
