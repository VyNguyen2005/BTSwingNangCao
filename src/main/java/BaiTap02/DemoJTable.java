/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap02;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class DemoJTable extends JFrame{
    private JLabel lblName, lblAmount;
    private JTextField txtName, txtAmount;
    private JButton btnAdd, btnDelete;
    private JTable tblTaiKhoan;
    private File file;
    
    public DemoJTable(String title){
        super(title);
        createGUI();
        
        createEventProcess();
        
        txtName.requestFocusInWindow();
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        DemoJTable frame = new DemoJTable("DemoJtable");
        frame.setVisible(true);
    }

    public void createGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        String[] columnName = {"Tên tài khoản", "Số tiền"};
        Object[][] data = new Object[][]{
            {"Nguyễn Văn A", 340000},
            {"Nguyễn Thị B", 540000}
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnName);
        tblTaiKhoan = new JTable(model);
        JScrollPane scrollPaneTable = new JScrollPane(tblTaiKhoan);
        
        panel.add(scrollPaneTable);
        
        JPanel pNhapLieu = new JPanel();
        pNhapLieu.add(lblName = new JLabel("Tên tài khoản: "));
        pNhapLieu.add(txtName = new JTextField(15));
        
        pNhapLieu.add(lblAmount = new JLabel("Số tiền: "));
        pNhapLieu.add(txtAmount = new JTextField(10));
        
        pNhapLieu.add(btnAdd = new JButton("Thêm"));
        pNhapLieu.add(btnDelete = new JButton("Xóa"));
        
        btnAdd.setIcon(new ImageIcon(this.getClass().getResource("/images/add.png")));
        btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/images/delete.png")));

        panel.add(pNhapLieu, BorderLayout.NORTH);

        add(panel);
    }

    private void createEventProcess() {
        btnAdd.addActionListener((e) -> {
            String text1 = txtName.getText().trim();
            String text2 = txtAmount.getText().trim();
            
            if (text1.isEmpty() || text2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không thể thêm vì nội dung Textbox đang rỗng. Bạn vui lòng nhập lại!", "Thông báo", JOptionPane.OK_OPTION);
                return;
            }else if (!tryParseDouble(text2)) {
                JOptionPane.showMessageDialog(this, "Không thể thêm vì nội dung Số tiền không phải là kiểu số. Bạn vui lòng nhập lại!", "Thông báo", JOptionPane.OK_OPTION);
                return;
            }
            else if(tryParseDouble(text1)){
                JOptionPane.showMessageDialog(this, "Không thể thêm vì nội dung Số tiền không phải là kiểu chuỗi. Bạn vui lòng nhập lại!", "Thông báo", JOptionPane.OK_OPTION);
                return;
            }
            else{
                DefaultTableModel model = (DefaultTableModel)tblTaiKhoan.getModel();
                model.addRow(new Object[] {txtName.getText(), txtAmount.getText()});
                txtName.setText("");
                txtAmount.setText("");
                txtName.requestFocus();
            }
            
        });
        
        btnDelete.addActionListener((e) -> {
            DefaultTableModel model = (DefaultTableModel)tblTaiKhoan.getModel();
            int selectedRow = tblTaiKhoan.getSelectedRow();
            
            if(selectedRow != -1){
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                   model.removeRow(selectedRow);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mục để xóa", "Thông báo", JOptionPane.OK_OPTION);
            }
        });
    }
    public boolean tryParseDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
