package Interfaz;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Prueba extends JPanel {
	
	
    public Prueba(String queryResult) {

        JLabel tituloLbl = new JLabel("Ranking Postulantes a Becas");
        tituloLbl.setBounds(0,20,370,50);
        tituloLbl.setVerticalAlignment(JLabel.TOP);
        tituloLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(tituloLbl);

        //Se crea un label para mostrar el string resultado.
        JLabel resultadoLbl = new JLabel(queryResult);
        resultadoLbl.setBounds(35,60,300,400);
        resultadoLbl.setVerticalAlignment(JLabel.TOP);
        resultadoLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(resultadoLbl);

        JButton volverBtn = new JButton("Volver");
        volverBtn.setBounds(20, 280, 80, 30);
        volverBtn.setVerticalAlignment(JLabel.BOTTOM);
        volverBtn.setHorizontalAlignment(JLabel.CENTER);
        this.add(volverBtn);
    }
}