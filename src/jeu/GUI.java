package jeu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GUI implements ActionListener
{
    private Jeu jeu;
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }

    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    public void afficher() {
        afficher("\n");
    }

   public void afficheImage( String nomImage) {
	   	URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
	   	if( imageURL != null ) {
        	image.setIcon( new ImageIcon( imageURL ));
            fenetre.pack();
        }
   }

    public void enable(boolean ok) {
        entree.setEditable(ok);
        if ( ! ok )
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUI() {

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int widthS = (int)tailleEcran.getWidth();
        int heightS = (int)tailleEcran.getHeight();
        fenetre = new JFrame("Jeu");
        entree = new JTextField(34);
        texte = new JTextArea();
        texte.setEditable(false);

        JScrollPane listScroller = new JScrollPane(texte);
        JPanel panel = new JPanel();
        image = new JLabel();

//        image.setMaximumSize(new Dimension((int)(920), (int)(740)));
//        image.setPreferredSize(new Dimension((int)(920), (int)(740)));
//        image.setMinimumSize(new Dimension((int)(920), (int)(740)));

        listScroller.setMaximumSize(new Dimension(920, 740));
        listScroller.setPreferredSize(new Dimension(620, 740));

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
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande( commandeLue);
    }
}