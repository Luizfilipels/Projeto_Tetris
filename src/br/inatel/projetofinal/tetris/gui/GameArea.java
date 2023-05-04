package br.inatel.projetofinal.tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private int gridLinhas;
    private int gridColunas;
    private int gridTamanhoCelula;
    
    private int[][] bloco = { {1,0}, {1,0}, {1,1} };
    
    public GameArea(JPanel placeholder, int colunas) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColunas = colunas;
        gridTamanhoCelula = this.getBounds().width / gridColunas;
        gridLinhas = this.getBounds().height / gridTamanhoCelula;
    }
    
    //metodo para desenhar o bloco em formato de L na GameArea (considerando como 1 <= pintado ; 0 <= não pintado
    private void desenharBloco(Graphics g) {
        for(int i = 0; i < bloco.length; i++) {
            for(int j = 0; j < bloco[0].length; j++) {
                if(bloco[i][j] == 1) {
                    g.setColor(Color.cyan);
                    g.fillRect(j * gridTamanhoCelula, i * gridTamanhoCelula, gridTamanhoCelula, gridTamanhoCelula);
                    g.setColor(Color.black);
                    g.drawRect(j * gridTamanhoCelula, i * gridTamanhoCelula, gridTamanhoCelula, gridTamanhoCelula);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for(int j=0;j < gridLinhas; j++) {
            for(int i=0;i < gridColunas; i++) {
                g.drawRect(i * gridTamanhoCelula, j * gridTamanhoCelula, gridTamanhoCelula, gridTamanhoCelula);
            }
        }
        
        desenharBloco(g);
    }
    
}