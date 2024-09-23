/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author nguye
 */
public class JFindDialog extends JDialog{
    private JLabel lblEnterText;
    private JTextField txtEnterText;
    private JButton btnSearch, btnCancel;
    private JRadioButton radUp, radDown;
    private ButtonGroup buttonGroup;
    private MyNotepad parent;
    public JFindDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("Find");
        parent = (MyNotepad)owner;
        createGUI();
        createEventProcess();
        setSize(480, 180);
        setLocationRelativeTo(owner);
    }
    
    public static void main(String[] args) {
        JFindDialog findDialog = new JFindDialog(null, true);
        findDialog.setVisible(true);
        findDialog.setLocationRelativeTo(null);
    }

    private void createGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        panel.add(lblEnterText = new JLabel("Find what: "));
        panel.add(txtEnterText = new JTextField());
        panel.add(btnSearch = new JButton("Find Next"));
        panel.add(btnCancel = new JButton("Cancel"));
        radUp = new JRadioButton("Up");
        radDown = new JRadioButton("Down");
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
                
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radUp);
        buttonGroup.add(radDown);
        
        
        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new GridLayout(1, 2)); 
        directionPanel.setBounds(210, 60, 130, 50);
        directionPanel.setBorder(BorderFactory.createTitledBorder("Direction")); 
        
        directionPanel.add(radUp);
        directionPanel.add(radDown);

        panel.add(directionPanel);

        lblEnterText.setBounds(10, 20, 80, 30);
        txtEnterText.setBounds(90, 20, 250, 30);
        btnSearch.setBounds(350, 20, 100, 30);
        btnCancel.setBounds(350, 60, 100, 30);
        
        add(panel);
        
    }

    private void createEventProcess() {
        btnSearch.addActionListener((e) -> {
        if (radDown.isSelected()) {
            parent.findTextDown(txtEnterText.getText());
        } 
        else if (radUp.isSelected()) {
            parent.findTextUp(txtEnterText.getText());
        } 
        else {
            parent.findTextDown(txtEnterText.getText());
        }
    });
        btnCancel.addActionListener((e) -> {
            this.dispose();
        });
    }
    
}
