/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.image;

import br.com.image.util.Constants;
import br.com.image.util.FiltroColor;
import br.com.image.util.FiltroText;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jesimar S. Arantes
 */
public class MainFrame extends JFrame{            
    
    private JPanel panelImg;
    private JPanel panelButtons;
    private JPanel panelImgNormal;   
    private JPanel panelImgProcessor;          
    
    private JButton btnOpen;      
    private JButton btnProcessor;    
    private JButton btnSave;    
    private JButton btnClose;
    private JButton btnAbout;
    
    private ImageIcon imageIcon;
    private BufferedImage buffer;
    private ImageFileChooser imageFileChooser;
    
    private FiltroColor filtroColor = FiltroColor.COLOR;
    private FiltroText filtroText = FiltroText.TEXT_INPUT;        
    
    private final int WIDTH_FRAME = Constants.WIDTH_FRAME;
    private final int HEIGHT_FRAME = Constants.HEIGHT_FRAME;
             
    private boolean isText = true;
        
    private int sizeFont = 10;
    private int shift = (int)(sizeFont * 0.8);      
    
    private String string = "Texto";        
    
    public MainFrame(){                
        initialize();  
        addPanelImg();
        addPanelButtons();
        addPanelImgNormal(); 
        addPanelImgProcessor();        
        addOptions();                    
        addButtons();
    }
    
    private void initialize(){
        this.setTitle("Framework jCV");
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBounds(100, 100, WIDTH_FRAME * 2, HEIGHT_FRAME + 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setVisible(true);
        
        imageFileChooser = new ImageFileChooser();
    }  
    
    private void addPanelImg(){        
        panelImg = new JPanel();
        panelImg.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImg.setVisible(true);        
        panelImg.setPreferredSize(new Dimension(2*WIDTH_FRAME - 12, 
                HEIGHT_FRAME - 60));
        panelImg.setBackground(Color.WHITE);        
        this.add(panelImg);  
        panelImg.updateUI();         
    } 
    
    private void addPanelButtons(){        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setVisible(true);        
        panelButtons.setPreferredSize(new Dimension(2*WIDTH_FRAME - 12, 
                110));
        panelButtons.setBackground(new Color(180, 180, 255));        
        this.add(panelButtons);  
        panelButtons.updateUI();         
    } 
    
    private void addPanelImgNormal(){ 
        JLabel labelAntes = new JLabel("Antes: ");
        labelAntes.setPreferredSize(new Dimension(WIDTH_FRAME - 30, 20));
        panelImg.add(labelAntes);
        
        JLabel labelDepois = new JLabel("Depois: ");
        labelDepois.setPreferredSize(new Dimension(WIDTH_FRAME - 30, 20));
        panelImg.add(labelDepois);
        
        panelImgNormal = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Processor.printImageNormal(g, imageIcon);
            }
        };
        panelImgNormal.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImgNormal.setVisible(true);        
        panelImgNormal.setPreferredSize(new Dimension(WIDTH_FRAME - 20, 
                HEIGHT_FRAME - 100));
        panelImgNormal.setBackground(new Color(200, 210, 255));        
        panelImg.add(panelImgNormal);  
        panelImgNormal.updateUI();         
    }        
    
    private void addPanelImgProcessor(){        
        panelImgProcessor = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g); 
                if (imageIcon != null){
                    buffer = Processor.printImageProcessor(g, imageIcon, shift, 
                            sizeFont, isText, filtroColor);                                       
                }
            }            
        };
        panelImgProcessor.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImgProcessor.setVisible(true);        
        panelImgProcessor.setPreferredSize(new Dimension(WIDTH_FRAME - 20, 
                HEIGHT_FRAME - 100));
        panelImgProcessor.setBackground(new Color(200, 210, 255));
        panelImg.add(panelImgProcessor);                
        panelImgProcessor.updateUI(); 
        
    }

    private void addOptions() {
        JLabel labelTexto = new JLabel("Texto: ");
        labelTexto.setPreferredSize(new Dimension(60, 25));
        panelButtons.add(labelTexto); 
        
        final JTextField stringJText = new JTextField("Texto");
        stringJText.setPreferredSize(new Dimension(320, 25));
        stringJText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                string = stringJText.getText();
            }
        });
        panelButtons.add(stringJText);
        
        JLabel labelFont = new JLabel("    Tamanho Fonte: ");
        labelFont.setPreferredSize(new Dimension(200, 25));
        panelButtons.add(labelFont);
        
        final JSpinner spinnerSizeFont = new JSpinner();
        spinnerSizeFont.setPreferredSize(new Dimension(100, 25));
        spinnerSizeFont.setValue(sizeFont);
        spinnerSizeFont.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {  
                if ((Integer)spinnerSizeFont.getValue() > 0){
                    sizeFont = (Integer)spinnerSizeFont.getValue();
                }else{
                    spinnerSizeFont.setValue(1);
                }
            }
        });
        panelButtons.add(spinnerSizeFont);
        
        JLabel labelDeslocamento = new JLabel("    Tamanho Deslocamento: ");
        labelDeslocamento.setPreferredSize(new Dimension(200, 25));
        panelButtons.add(labelDeslocamento);
        
        final JSpinner spinnerDeslocamento = new JSpinner();
        spinnerDeslocamento.setPreferredSize(new Dimension(100, 25));
        spinnerDeslocamento.setValue(shift);
        spinnerDeslocamento.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {  
                if ((Integer)spinnerDeslocamento.getValue() > 0){
                    shift = (Integer)spinnerDeslocamento.getValue();
                }else{
                    spinnerDeslocamento.setValue(1);
                }
            }
        });
        panelButtons.add(spinnerDeslocamento);
        
        final JCheckBox checkBoxText = new JCheckBox("Usará Texto");
        checkBoxText.setPreferredSize(new Dimension(120, 25));
        checkBoxText.setSelected(true);
        checkBoxText.setBackground(Color.LIGHT_GRAY);
        checkBoxText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isText = checkBoxText.isSelected();
            }
        });
        panelButtons.add(checkBoxText);
        
//        final JCheckBox checkBoxColor = new JCheckBox("Usará Cor");
//        checkBoxColor.setPreferredSize(new Dimension(120, 25));
//        checkBoxColor.setSelected(true);
//        checkBoxColor.setBackground(Color.LIGHT_GRAY);
//        checkBoxColor.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //isColor = checkBoxColor.isSelected();
//            }
//        });
//        panelButtons.add(checkBoxColor);
        
        JLabel labelFiltroColor = new JLabel("    Filtro Cor: ");
        labelFiltroColor.setPreferredSize(new Dimension(110, 25));
        panelButtons.add(labelFiltroColor);
        
        String names[] = {"Colorido", "Preto e Branco", "Escala de Cinza", 
                "GBR", "GRB", "RBG", "BRG", "BGR", "Preto e Branco RND", "Cor RND", 
                "Escala de Cinza RND", "CMY", "CYM", "YCM", "YMC", "MYC", "MCY",
                "BW Média Geométrica", "SG Média Geométrica", "Transparente"};
        final JComboBox comboBoxColor = new JComboBox();
        comboBoxColor.setModel(new javax.swing.DefaultComboBoxModel(names));
        comboBoxColor.setPreferredSize(new Dimension(140, 25));        
        comboBoxColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                filtroColor = FiltroColor.getFiltro(comboBoxColor.getSelectedIndex());                
            }
        });
        panelButtons.add(comboBoxColor);
        
        JLabel labelFiltroTexto = new JLabel("    Filtro Texto: ");
        labelFiltroTexto.setPreferredSize(new Dimension(110, 25));
        panelButtons.add(labelFiltroTexto);
        
        String namesText[] = {"Texto Entrada", "Texto RND A a Z", "Texto RND a a z", "Texto RND 0 a 9"};
        final JComboBox comboBoxText = new JComboBox();
        comboBoxText.setModel(new javax.swing.DefaultComboBoxModel(namesText));
        comboBoxText.setPreferredSize(new Dimension(140, 25));        
        comboBoxText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                filtroText = FiltroText.getFiltro(comboBoxText.getSelectedIndex());                
            }
        });
        panelButtons.add(comboBoxText);
    }

    private void addButtons() {
        btnOpen = new JButton("Carregar Imagem");
        btnOpen.setPreferredSize(new Dimension(160, 25));
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int result = imageFileChooser.showOpenDialog();
                if (result == JFileChooser.APPROVE_OPTION) {
                    Processor.preencheMatrizChar(string, filtroText);                    
                    btnClose.setEnabled(true);
                    btnSave.setEnabled(true);
                    final File file = imageFileChooser.getSelectedFile();                
                    imageIcon = new ImageIcon(file.getPath());                       
                    panelImgNormal.updateUI();
                    panelImgProcessor.updateUI();                    
                }
            }
        });
        panelButtons.add(btnOpen); 
        btnOpen.updateUI();
        
        btnProcessor = new JButton("Processar");
        btnProcessor.setPreferredSize(new Dimension(160, 25));
        btnProcessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {                                
                Processor.preencheMatrizChar(string, filtroText);
                panelImgNormal.updateUI();
                panelImgProcessor.updateUI();                
            }
        });
        panelButtons.add(btnProcessor); 
        btnProcessor.updateUI();
        
        btnSave = new JButton("Salvar Imagem");
        btnSave.setEnabled(false);
        btnSave.setPreferredSize(new Dimension(160, 25));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int rnd = (int)(Math.random()*1000);
                    ImageIO.write(buffer, "png", new File("img" + rnd + ".png"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "\nErro Salvar Imagem: \n" + 
                            ex, "Erro Salvar Imagem", JOptionPane.ERROR_MESSAGE);
                }                                
            }
        });
        panelButtons.add(btnSave);    
        btnSave.updateUI();
        
        btnClose = new JButton("Fechar Imagem");
        btnClose.setEnabled(false);
        btnClose.setPreferredSize(new Dimension(160, 25));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 btnSave.setEnabled(false);
                 btnClose.setEnabled(false);                 
                 imageIcon = null;
                 buffer = null;
                 panelImgNormal.repaint();
                 panelImgProcessor.repaint();
            }
        });
        panelButtons.add(btnClose);    
        btnClose.updateUI();
        
        btnAbout = new JButton("Sobre");        
        btnAbout.setPreferredSize(new Dimension(160, 25));
        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 JOptionPane.showMessageDialog(null, "Autor: Jesimar da Silva Arantes", 
                         "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelButtons.add(btnAbout);    
        btnAbout.updateUI();
    }
    
}
