/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class JReplaceDialog extends JDialog{
    private JLabel lblFirst, lblEnd;
    private JTextField txtFirst, txtEnd;
    private JButton btnFind,  btnReplace, btnReplaceAll, btnCancel;
    private MyNotepad parent;
    public JReplaceDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("Replace");
        parent = (MyNotepad)owner;
        createGUI();
        createEventProcess();
        setSize(480, 230);
        setLocationRelativeTo(owner);
    }
    
    public static void main(String[] args) {
        JReplaceDialog replaceDialog = new JReplaceDialog(null, true);
        replaceDialog.setVisible(true);
        replaceDialog.setLocationRelativeTo(null);
    }

    private void createGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        panel.add(lblFirst = new JLabel("Find what: "));
        panel.add(lblEnd = new JLabel("Replace with: "));
        panel.add(txtFirst = new JTextField());
        panel.add(txtEnd = new JTextField());
        panel.add(btnFind = new JButton("Find Next"));
        panel.add(btnReplace = new JButton("Replace"));
        panel.add(btnReplaceAll = new JButton("Replace All"));
        panel.add(btnCancel = new JButton("Cancel"));
        
        lblFirst.setBounds(10, 20, 80, 30);
        txtFirst.setBounds(90, 20, 250, 30);
        lblEnd.setBounds(10, 60, 80, 30);
        txtEnd.setBounds(90, 60, 250, 30);
        btnFind.setBounds(350, 20, 100, 30);
        btnReplace.setBounds(350, 60, 100, 30);
        btnReplaceAll.setBounds(350, 100, 100, 30);
        btnCancel.setBounds(350, 140, 100, 30);
        
        add(panel);
    }

    private void createEventProcess() {
        btnFind.addActionListener((e) -> {
            parent.findTextDown(txtFirst.getText());
        });
        btnReplace.addActionListener((e) -> {
            parent.replaceText(txtFirst.getText(), txtEnd.getText());
        });
        btnReplaceAll.addActionListener((e) -> {
            parent.replaceAllText(txtFirst.getText(), txtEnd.getText());
        });
        btnCancel.addActionListener((e) -> {
            this.dispose();
        });
    }
}
