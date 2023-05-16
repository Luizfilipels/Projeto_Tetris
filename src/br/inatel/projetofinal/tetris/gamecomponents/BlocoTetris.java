package br.inatel.projetofinal.tetris.gamecomponents;

import java.awt.Color;

public class BlocoTetris {
    private int[][] forma;
    private Color cor;
    private int x, y;
    private int[][][] formasBloco;
    private int estadoRotBloco;
    
    //Construtor
    public BlocoTetris(int[][] forma, Color cor) {
        this.forma = forma;
        this.cor = cor;
        iniciarFormasBloco();
    }
    
    
    private void iniciarFormasBloco() {
        formasBloco = new int[4][][];
        
        for(int i=0; i < 4; i++) {
            int l = forma[0].length;
            int c = forma.length;
            
            formasBloco[i] = new int[l][c];
            
            for(int y=0; y < l; y++) {
                for(int x=0; x < c;  x++) {
                    formasBloco[i][y][x] = forma[c - x - 1][y];
                }
            }
            
            forma = formasBloco[i];
        }
    }
    
    public void spawn(int TamanhoGrid) {
        estadoRotBloco = 0;
        forma = formasBloco[estadoRotBloco];
        
        y = -getAltura();
        x = (TamanhoGrid -getLargura()) / 2;
    }

    public int[][] getForma() {
        return forma;
    }

    public Color getCor() {
        return cor;
    }
    
    public int getAltura() {
        return forma.length;
    }
    
    public int getLargura() {
        return forma[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    //Metodos de controle do bloco
    public void moverParaBaixo() {
        y++;
    }
    
    public void moverLadoDireito() {
        x++;
    }
    
    public void moverLadoEsquerdo() {
        x--;
    }
    
    public int inferior() {
        return y + getAltura();
    }
    
    public void rotacionar() {
        estadoRotBloco++;
        if(estadoRotBloco > 3) {
            estadoRotBloco = 0;
        }
        forma = formasBloco[estadoRotBloco];
    }
    
    public int getBordaEsq() {
        return x;
    }
    
    public int getBordaDir() {
        return x + getLargura();
    }
    
}
