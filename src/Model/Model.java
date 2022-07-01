/**
 * 
 */
package Model;

import java.sql.ResultSet;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

/**
 * @author aa
 *
 */
public class Model {

	public void liste(JTable table, ResultSet rs) {
		new Moyenne().load();

		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(2).setPreferredWidth(122);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(83);
//		table.getColumnModel().getColumn(5).setPreferredWidth(300);
//		table.getColumnModel().getColumn(6).setPreferredWidth(100);
//		table.getColumnModel().getColumn(7).setPreferredWidth(150);
//		table.getColumnModel().getColumn(8).setPreferredWidth(100);
//		table.getColumnModel().getColumn(9).setPreferredWidth(90);
//		table.getColumnModel().getColumn(10).setPreferredWidth(200);

		// table.getColumnModel().getColumn(11).setPreferredWidth(90);
//		table.getColumnModel().getColumn(12).setPreferredWidth(82);
	}

}
