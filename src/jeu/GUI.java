package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;

public class GUI implements ActionListener, Serializable {
    private final Jeu jeu;
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    public GUI(Jeu jeu) {
        this.jeu = jeu;
        this.creerGUI();
    }

    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    public void afficher() {
        afficher("\n");
    }

    public void afficheImage(String nomImage) {
        URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
        if (imageURL != null) {
            image.setIcon(new ImageIcon(imageURL));
            fenetre.pack();
        }
    }


    public void fermer() {
        fenetre.dispose();
    }

    private void creerGUI() {

        fenetre = new JFrame("Jeu");
        entree = new JTextField(34);
        texte = new JTextArea();
        texte.setEditable(false);

        JScrollPane listScroller = new JScrollPane(texte);
        JPanel panel = new JPanel();
        image = new JLabel();

        listScroller.setMaximumSize(new Dimension(879, 740));
        listScroller.setPreferredSize(new Dimension(650, 740));

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);
        panel.add(listScroller, BorderLayout.EAST);

        panel.setPreferredSize(new Dimension(1540, 740));
        panel.setMinimumSize(new Dimension(1540, 740));

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetre.setPreferredSize(new Dimension(1540, 740));
        fenetre.setMinimumSize(new Dimension(1540, 740));

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        jeu.traiterCommande(lireCommande());
        entree.setText("");
    }

    public String lireCommande() {
        return entree.getText();
    }


}//fin de la classe