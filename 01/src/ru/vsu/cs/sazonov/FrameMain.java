package ru.vsu.cs.sazonov;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FrameMain extends JFrame {

    private JLabel labelImg;
    private JButton buttonTask;
    private JPanel panelMain;
    private JButton buttonSave;
    private JSpinner spinnerX;
    private JSpinner spinnerY;
    private JSpinner spinnerBigRadius;
    private JSpinner spinnerSmallRadius;
    private JSpinner spinnerNumberRays;
    private JSpinner spinnerMoveX;
    private JSpinner spinnerMoveY;
    private JButton buttonMove;
    private JButton buttonScaling;
    private JSpinner spinnerScale;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Star");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooserSave = new JFileChooser();

        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Graphics file", "jpg");

        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);
        spinnerX.setValue(200);
        spinnerY.setValue(200);
        spinnerBigRadius.setValue(100);
        spinnerSmallRadius.setValue(50);
        spinnerNumberRays.setValue(5);
        spinnerMoveX.setValue(100);
        spinnerMoveY.setValue(100);
        spinnerScale.setValue(100);

        this.pack();

        buttonTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int x= (int) spinnerX.getValue();
                    int y= (int) spinnerY.getValue();
                    int numberRays= (int) spinnerNumberRays.getValue();
                    int bigRadius= (int) spinnerBigRadius.getValue();
                    int smallRadius=(int) spinnerSmallRadius.getValue();
                    Star star=new Star(x, y, numberRays, bigRadius,smallRadius);
                    BufferedImage img = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_BGR);
                    Graphics2D g2d=img.createGraphics();
                    g2d.setColor(Color.black);
                    star.paint(img.getWidth(),img.getHeight(),g2d);
                    labelImg.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {

                        Icon icon=labelImg.getIcon();
                        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = img.createGraphics();
                        icon.paintIcon(null, g2d, 0,0);
                        g2d.dispose();
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".png")) {
                            file += ".png";
                        }
                        Main.writeImageToFile(file, img);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int x= (int) spinnerX.getValue();
                    int y= (int) spinnerY.getValue();
                    int numberRays= (int) spinnerNumberRays.getValue();
                   int bigRadius= (int) spinnerBigRadius.getValue();
                   int smallRadius=(int) spinnerSmallRadius.getValue();
                    int moveX=(int) spinnerMoveX.getValue();
                    int moveY=(int) spinnerMoveY.getValue();
                    Star star=new Star(x, y, numberRays, bigRadius,smallRadius);
                    star.move(moveX,moveY);
                    BufferedImage img = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_BGR);
                    Graphics2D g2d=img.createGraphics();
                    g2d.setColor(Color.black);
                    star.paint(img.getWidth(),img.getHeight(),g2d);
                    labelImg.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonScaling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int x= (int) spinnerX.getValue();
                    int y= (int) spinnerY.getValue();
                    int numberRays= (int) spinnerNumberRays.getValue();
                    int bigRadius= (int) spinnerBigRadius.getValue();
                    int smallRadius=(int) spinnerSmallRadius.getValue();
                    int scale=(int) spinnerScale.getValue();
                    Star star=new Star(x, y, numberRays, bigRadius,smallRadius);
                    star.scaling(scale);
                    BufferedImage img = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_BGR);
                    Graphics2D g2d=img.createGraphics();
                    g2d.setColor(Color.black);
                    star.paint(img.getWidth(),img.getHeight(),g2d);
                    labelImg.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
