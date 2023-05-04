package br.inatel.projetofinal.tetris.gamecomponents;

import java.awt.Color;

public class BlocoTetris {
    private int[][] forma;
    private Color cor;
    
    public BlocoTetris(int[][] forma, Color cor) {
        this.forma = forma;
        this.cor = cor;
    }

    public int[][] getForma() {
        return forma;
    }

    public Color getCor() {
        return cor;
    }
    
    
}
