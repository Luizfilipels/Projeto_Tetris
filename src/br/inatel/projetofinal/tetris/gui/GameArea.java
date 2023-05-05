package br.inatel.projetofinal.tetris.gui;

import br.inatel.projetofinal.tetris.gamecomponents.BlocoTetris;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private int gridLinhas;
    private int gridColunas;
    private int gridTamanhoCelula;
    
    private BlocoTetris bloco;
    
    public GameArea(JPanel placeholder, int colunas) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColunas = colunas;
        gridTamanhoCelula = this.getBounds().width / gridColunas;
        gridLinhas = this.getBounds().height / gridTamanhoCelula;
        
        //Chamando o metodo para gerar o bloco
        gerarBloco();
    }
    
    private void gerarBloco() {
        bloco = new BlocoTetris(new int[][] { {1,0}, {1,0}, {1,1} }, Color.GREEN);
        bloco.spawn(gridColunas);
    }
    
    //"""Gravidade""" do bloco
    public void gravidadeBloco() {
        //Verifica se o bloco chegou no final
        if(checarFinal() == false) {
            return;
        }
        
        bloco.moverParaBaixo();
        repaint();
    }
    
    //Verificar se o bloco chegou no final da área do jogo
    private boolean checarFinal() {
        return bloco.inferior() != gridLinhas;
    }
    
    //metodo para desenhar o bloco em formato de L na GameArea (considerando como 1 <= pintado ; 0 <= não pintado
    private void desenharBloco(Graphics g) {
        int alt = bloco.getAltura();
        int larg = bloco.getLargura();
        Color c = bloco.getCor();
        int[][] forma = bloco.getForma();
        
        for(int i = 0; i < alt; i++) {
            for(int j = 0; j < larg; j++) {
                if(forma[i][j] == 1) {
                    //X e Y representa o local onde o bloco vai spawnar
                    int x = (bloco.getX() + j) * gridTamanhoCelula;
                    int y = (bloco.getY() + i) * gridTamanhoCelula;
                    
                    g.setColor(c);
                    g.fillRect(x, y, gridTamanhoCelula, gridTamanhoCelula);
                    
                    //Para fazer as linhas do bloco do tetris, tem que setar a cor para preto
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridTamanhoCelula, gridTamanhoCelula);
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