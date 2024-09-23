/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTap01;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class MyNotepad extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp, menuZoom;
    private JMenuItem itemNew, itemNewWindow, itemOpen, itemSave, itemSaveAs, itemPageSetUp, itemPrint, itemExit,
            itemUndo, itemCut, itemCopy, itemPaste, itemDelete, itemSearchWithBing, itemFind, itemFindNext, itemFindPrev, itemReplace, itemGoTo, itemSelectAll, itemDateTime,
            itemWrap, itemFont,
            itemStatusBar,
            itemZoomIn, itemZoomOut, itemRestore,
            itemHelp, itemSend, itemAbout;
    private JTextArea textEditor;
    private int size = 14;
    private File currentFile;
    private int lastIndex = -1;
    public MyNotepad(String title) {
        super(title);
        createMenu();

        createProcessEvent();

        createGUI();

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JTextArea getTextEditor() {
        return textEditor;
    }

    public static void main(String[] args) {
        MyNotepad frame = new MyNotepad("Untitled - Notepad");
        frame.setVisible(true);
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.add(menuFile = new JMenu("File"));
        menuBar.add(menuEdit = new JMenu("Edit"));
        menuBar.add(menuFormat = new JMenu("Format"));
        menuBar.add(menuView = new JMenu("View"));
        menuBar.add(menuHelp = new JMenu("Help"));

        // File item
        menuFile.add(itemNew = new JMenuItem("New"));
        menuFile.add(itemNewWindow = new JMenuItem("New Window"));
        menuFile.add(itemOpen = new JMenuItem("Open..."));
        menuFile.add(itemSave = new JMenuItem("Save"));
        menuFile.add(itemSaveAs = new JMenuItem("Save As..."));
        menuFile.addSeparator();
        menuFile.add(itemPageSetUp = new JMenuItem("Page Setup..."));
        menuFile.add(itemPrint = new JMenuItem("Print.."));
        menuFile.addSeparator();
        menuFile.add(itemExit = new JMenuItem("Exit"));
        // Tổ hợp phím File
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemNewWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        // Edit item
        menuEdit.add(itemUndo = new JMenuItem("Undo"));
        menuEdit.addSeparator();
        menuEdit.add(itemCut = new JMenuItem("Cut"));
        menuEdit.add(itemCopy = new JMenuItem("Copy"));
        menuEdit.add(itemPaste = new JMenuItem("Paste"));
        menuEdit.add(itemDelete = new JMenuItem("Delete"));
        menuEdit.addSeparator();
        menuEdit.add(itemSearchWithBing = new JMenuItem("Search with Bing..."));
        menuEdit.add(itemFind = new JMenuItem("Find..."));
        menuEdit.add(itemFindNext = new JMenuItem("Find Next"));
        menuEdit.add(itemFindPrev = new JMenuItem("Find Previous"));
        menuEdit.add(itemReplace = new JMenuItem("Replace..."));
        menuEdit.add(itemGoTo = new JMenuItem("Go To..."));
        menuEdit.addSeparator();
        menuEdit.add(itemSelectAll = new JMenuItem("Select All"));
        menuEdit.add(itemDateTime = new JMenuItem("Time/Date"));
        // Tổ hợp phím Edit
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        itemSearchWithBing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        itemFindPrev.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.SHIFT_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemDateTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        // Format item
        menuFormat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap"));
        menuFormat.add(itemFont = new JCheckBoxMenuItem("Font..."));
        // View item
        menuView.add(menuZoom = new JMenu("Zoom"));
        menuView.add(itemStatusBar = new JCheckBoxMenuItem("Status Bar"));
        // Zoom item
        menuZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        menuZoom.add(itemZoomOut = new JMenuItem("Zoom Out"));
        menuZoom.add(itemRestore = new JMenuItem("Restore Default Zoom"));
        // Tổ hợp phím Zoom
        itemZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        itemZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        itemRestore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        // Help item
        menuHelp.add(itemHelp = new JMenuItem("View Help"));
        menuHelp.add(itemSend = new JMenuItem("Send Feedback"));
        menuHelp.addSeparator();
        menuHelp.add(itemAbout = new JMenuItem("About Notepad"));
        setJMenuBar(menuBar);
    }

    private void createProcessEvent() {
        // View
        itemZoomIn.addActionListener((e) -> {
            size += 3;
            textEditor.setFont(new Font("Times New Roman", Font.PLAIN, size));
        });
        itemZoomOut.addActionListener((e) -> {
            size -= 3;
            textEditor.setFont(new Font("Times New Roman", Font.PLAIN, size));
        });
        itemRestore.addActionListener((e) -> {
            size = 12;
            textEditor.setFont(new Font("Times New Roman", Font.PLAIN, size));
        });
        // File
        itemOpen.addActionListener((e) -> {
            openFile();
        });
        itemSave.addActionListener((e) -> {
            saveFile();
        });
        itemSaveAs.addActionListener((e) -> {
            saveAsFile();
        });
        itemWrap.addItemListener((e) -> {
            textEditor.setLineWrap(true);
        });
        itemExit.addActionListener((e) -> {
            this.dispose();
        });
        itemCopy.addActionListener((e) -> {
            textEditor.copy();
        });
        itemPaste.addActionListener((e) -> {
            textEditor.paste();
        });
        itemFind.addActionListener((e) -> {
            showFindDialog();
        });
        itemReplace.addActionListener((e) -> {
            showReplaceDialog();
        });
        itemFont.addActionListener((e) -> {
            showFontDialog();
        });
    }

    private void createGUI() {
        textEditor = new JTextArea();
        JScrollPane scroll = new JScrollPane(textEditor);
        add(scroll);

        textEditor.setFont(new Font("Times New Roman", Font.PLAIN, size));
        textEditor.setLineWrap(true);
    }

    private void openFile() {
        // Hộp thoại file
        JFileChooser file = new JFileChooser();
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(file.getSelectedFile());
                try {
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    textEditor.setText(new String(b));

                } catch (IOException ex) {
                    Logger.getLogger(MyNotepad.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyNotepad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveAsFile() {
        // Hộp thoại tập tin
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                currentFile = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(currentFile);
                try {
                    fos.write(textEditor.getText().getBytes());
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(MyNotepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                saveFile();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyNotepad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveAsFile();
        } else {
            try (FileWriter writer = new FileWriter(currentFile)) {
                textEditor.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi lưu file: " + ex.getMessage());
            }
        }
    }

    private void showFindDialog() {
        JFindDialog findDialog = new JFindDialog(this, true);
        findDialog.setVisible(true);
    }

    public void findTextDown(String searchString) {
        String text = textEditor.getText();

        String textLower = text.toLowerCase();
        String searchStringLower = searchString.toLowerCase();
        
        if(searchStringLower.isEmpty()){
            JOptionPane.showMessageDialog(this, "Search string cannot be empty!");
            return;
        }
        
        if(lastIndex == -1){
            lastIndex = textLower.indexOf(searchStringLower);
        }
        else{
            lastIndex = textLower.indexOf(searchStringLower, lastIndex + searchString.length());
        }
        if(lastIndex != -1){
            textEditor.setCaretPosition(lastIndex);
            textEditor.select(lastIndex, lastIndex + searchString.length());
        }
        else{
            JOptionPane.showMessageDialog(this, "Can't find '" +searchString + "'");
            lastIndex = -1;
        }
    }
    public void findTextUp(String searchString){
        String text = textEditor.getText();

        String textLower = text.toLowerCase();
        String searchStringLower = searchString.toLowerCase();
        
        if(searchStringLower.isEmpty()){
            JOptionPane.showMessageDialog(this, "Search string cannot be empty!");
            return;
        }
        if(lastIndex == -1){
            lastIndex = textLower.lastIndexOf(searchStringLower);
        }
        else{
            lastIndex = textLower.lastIndexOf(searchStringLower, lastIndex - 1);
        }
        if(lastIndex != -1){
            textEditor.setCaretPosition(lastIndex);
            textEditor.select(lastIndex, lastIndex + searchString.length());
        }
        else{
            JOptionPane.showMessageDialog(this, "Can't find '" +searchString + "'");
            lastIndex = -1;
        }
    }
    private void showReplaceDialog() {
        JReplaceDialog replaceDialog = new JReplaceDialog(this, true);
        replaceDialog.setVisible(true);
    }

    public void replaceText(String firstText, String endText) {
        String text = textEditor.getText();

        String textLower = text.toLowerCase();
        String firstTextLower = firstText.toLowerCase();
        
        if(firstText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Search string cannot be empty!");
            return;
        }
        if(lastIndex == -1){
            lastIndex = textLower.indexOf(firstTextLower);
        }
        else{
            lastIndex = textLower.indexOf(firstTextLower, lastIndex + firstText.length());
        }
        if(lastIndex != -1){
            // Thay đổi trên văn bản
            String updatedText = textLower.replaceFirst(firstText, endText);
            textEditor.setText(updatedText);
            textEditor.setCaretPosition(lastIndex);
            textEditor.select(lastIndex, lastIndex + firstText.length());
        }
        else{
            JOptionPane.showMessageDialog(this, "Can't find '" +firstText + "'");
            lastIndex = -1;
        }
    }
    public void replaceAllText(String firstText, String endText) {
        String text = textEditor.getText();

        String textLower = text.toLowerCase();
        String firstTextLower = firstText.toLowerCase();
        
        if(firstText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Search string cannot be empty!");
            return;
        }
        if(lastIndex == -1){
            lastIndex = textLower.indexOf(firstTextLower);
        }
        else{
            lastIndex = textLower.indexOf(firstTextLower, lastIndex + firstText.length());
        }
        if(lastIndex != -1){
            // Thay đổi trên văn bản
            String updatedText = textLower.replace(firstText, endText);
            textEditor.setText(updatedText);
            textEditor.setCaretPosition(lastIndex);
            textEditor.select(lastIndex, lastIndex + firstText.length());
        }
        else{
            JOptionPane.showMessageDialog(this, "Can't find '" +firstText + "'");
            lastIndex = -1;
        }
    }

    private void showFontDialog() {
        JFontDialog fontDialog = new JFontDialog(this, true);
        fontDialog.setVisible(true);
    }
    
    
}
