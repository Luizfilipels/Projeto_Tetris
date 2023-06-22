package br.inatel.projetofinal.tetris.gamecomponents;

import java.awt.Color;
import java.util.Random;


/*
Essa classe fornece os métodos necessários para 
representar e controlar um bloco no jogo Tetris, 
como movimentação, rotação e informações sobre a
sua forma, posição e cor.
*/



public class BlocoTetris {
    private int[][] forma;
    private Color cor;
    private int x, y;
    private int[][][] formasBloco;
    private int estadoRotBloco;
    
    private Color[] coresDisponiveis = {Color.blue,Color.orange,Color.red,Color.green};
    
    //Construtor
    public BlocoTetris(int[][] forma) {
        this.forma = forma;
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
        Random r = new Random();
        
        estadoRotBloco = r.nextInt(forma.length);
        forma = formasBloco[estadoRotBloco];
        
        y = -getAltura();
        x = r.nextInt(TamanhoGrid - getLargura());
        
        cor = coresDisponiveis[r.nextInt(coresDisponiveis.length)];
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
