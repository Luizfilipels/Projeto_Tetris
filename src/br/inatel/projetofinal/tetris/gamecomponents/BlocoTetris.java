package br.inatel.projetofinal.tetris.gamecomponents;

import java.awt.Color;

public class BlocoTetris {
    private int[][] forma;
    private Color cor;
    private int x, y;
    
    public BlocoTetris(int[][] forma, Color cor) {
        this.forma = forma;
        this.cor = cor;
    }
    
    public void spawn(int TamanhoGrid) {
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
    
}
