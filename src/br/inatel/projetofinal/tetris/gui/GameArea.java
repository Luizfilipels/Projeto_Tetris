package br.inatel.projetofinal.tetris.gui;

import br.inatel.projetofinal.tetris.gamecomponents.BlocoTetris;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private int gridLinhas;
    private int gridColunas;
    private int gridTamanhoCelula;
    private Color[][] fundo;
    
    private BlocoTetris bloco;
    
    //Construtor
    public GameArea(JPanel placeholder, int colunas) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColunas = colunas;
        gridTamanhoCelula = this.getBounds().width / gridColunas;
        gridLinhas = this.getBounds().height / gridTamanhoCelula;
        
        fundo = new Color[gridLinhas][gridColunas];
  
    }
    
    public void gerarBloco() {
        bloco = new BlocoTetris(new int[][] { {1,0}, {1,0}, {1,1} }, Color.GREEN);
        bloco.spawn(gridColunas);
    }
    
    //"""Gravidade""" do bloco
    public boolean gravidadeBloco() {
        //Verifica se o bloco chegou no final
        if(checarFinal() == false) {
            moverParaFundo();
            return false;
        } else {
            bloco.moverParaBaixo();
            repaint();
            return true;
        }
    }
    
    //Verificar se o bloco chegou no final da área do jogo
    private boolean checarFinal() {
        return bloco.inferior() != gridLinhas;
    }
    
    //Mover o bloco para o background do jogo
    private void moverParaFundo() {
        int[][] forma = bloco.getForma();
        int a = bloco.getAltura();
        int l = bloco.getLargura();
        
        int posX = bloco.getX();
        int posY = bloco.getY();
        
        Color cor = bloco.getCor();
        
        for(int lin = 0; lin < a; lin++) {
            for(int col = 0; col < l; col++) {
                if(forma[lin][col] == 1) {
                    fundo[lin + posY][col + posX] = cor;
                    
                }
            }
        }
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
                    
                    
                    desenharGrid(g,c,x,y);
                }
            }
        }
    }
    
    
    private void desenharFundo(Graphics g) {
        Color c;
        for(int lin = 0; lin < gridLinhas; lin++) {
            for(int col = 0; col < gridColunas; col++) {
                c = fundo[lin][col];
                
                if(c != null) {
                    int x = col * gridTamanhoCelula;
                    int y = lin * gridTamanhoCelula;
                    
                    desenharGrid(g,c,x,y);
                }
            }
        }
    }
    
    private void desenharGrid(Graphics g, Color c, int x, int y) {
        g.setColor(c);
        g.fillRect(x, y, gridTamanhoCelula, gridTamanhoCelula);

        //Para fazer as linhas do bloco do tetris, tem que setar a cor para preto
        g.setColor(Color.black);
        g.drawRect(x, y, gridTamanhoCelula, gridTamanhoCelula);       
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for(int j=0;j < gridLinhas; j++) {
            for(int i=0;i < gridColunas; i++) {
                g.drawRect(i * gridTamanhoCelula, j * gridTamanhoCelula, gridTamanhoCelula, gridTamanhoCelula);
            }
        }
        
        desenharFundo(g);
        desenharBloco(g);
    }
    
}