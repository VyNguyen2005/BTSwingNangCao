/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author nguye
 */
public class JFontDialog extends JDialog{
    private JLabel lblFont, lblFontStyle, lblSize;
    private JButton btnOK, btnCancel;
    private MyNotepad parent;
    private JList listFont, lstFontStyle, lstSize;
    private JLabel lblPreview;
    private int[] fontsStyle = {Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD};
    private Font font;
    
    public JFontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("Font");
        parent = (MyNotepad)owner;
        createGUI();
        createEventProcess();
        setSize(500, 430);
        setLocationRelativeTo(owner);
    }
    
    public static void main(String[] args) {
        JFontDialog fontDialog = new JFontDialog(null, true);
        fontDialog.setVisible(true);
    }

    private void createGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        panel.add(lblFont = new JLabel("Font:"));
        lblFont.setBounds(5, 10, 40, 30);
        
        panel.add(lblFontStyle = new JLabel("Font style:"));
        lblFontStyle.setBounds(200, 10, 60, 30);
        
        panel.add(lblSize = new JLabel("Size:"));
        lblSize.setBounds(360, 10, 40, 30);
        
        panel.add(btnOK = new JButton("OK"));
        panel.add(btnCancel = new JButton("Cancel"));
        btnOK.setBounds(300, 350, 80, 30);
        btnCancel.setBounds(390, 350, 80, 30);
        
        listFont = new JList();
        JScrollPane scrollPaneFont = new JScrollPane(listFont);
        panel.add(scrollPaneFont);
        scrollPaneFont.setBounds(5, 35, 180, 150);
        
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        DefaultListModel<String> modelFont = new DefaultListModel<>();  
        for (Font font : fonts) {
            modelFont.addElement(font.getName());
        }
        
        listFont.setModel(modelFont);
        listFont.setSelectedIndex(0);
        
        lstFontStyle = new JList(new Object[] {"Regular", "Italic", "Bold", "Bold Italic"});
        JScrollPane scrollPaneFontStyle = new JScrollPane(lstFontStyle);
        panel.add(scrollPaneFontStyle);
        scrollPaneFontStyle.setBounds(200, 35, 150, 150);
        lstFontStyle.setSelectedIndex(0);
        
        lstSize = new JList(new Object[] {"8", "9", "10", "11", "12", "12", "13",
                                          "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
        JScrollPane scrollPaneSize = new JScrollPane(lstSize);
        panel.add(scrollPaneSize);
        scrollPaneSize.setBounds(360, 35, 110, 145);
        lstSize.setSelectedIndex(0);
        
        JPanel samplePanel = new JPanel();
        samplePanel.setLayout(new GridLayout(1, 2)); 
        samplePanel.setBounds(200, 190, 270, 90);
        samplePanel.setBorder(BorderFactory.createTitledBorder("Sample"));
        
        JPanel labelPanel = new JPanel();
        labelPanel.add(lblPreview = new JLabel("AaBbYyZz"), BorderLayout.CENTER);
        
        samplePanel.add(labelPanel);
        
        panel.add(samplePanel);
        
        add(panel);
    }

    private void createEventProcess() {
        btnCancel.addActionListener((e) -> {
            this.dispose();
        });
        listFont.addListSelectionListener((e) -> {
            setFont();
        });

        lstFontStyle.addListSelectionListener((e) -> {
            setFont();
        });
        lstSize.addListSelectionListener(((e) -> {
            setFont();
        }));
        btnOK.addActionListener((e) -> {
            parent.getTextEditor().setFont(font);
            
            this.dispose();
        });
    }

    private void setFont() {
        String name = (String) listFont.getSelectedValue();
        int style = fontsStyle[lstFontStyle.getSelectedIndex()];
        int size = Integer.parseInt(lstSize.getSelectedValue().toString());
        font = new Font(name, style, size);
        lblPreview.setFont(font);
    }
}
