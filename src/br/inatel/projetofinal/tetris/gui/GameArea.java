package br.inatel.projetofinal.tetris.gui;

import br.inatel.projetofinal.tetris.gamecomponents.BlocoTetris;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private final int gridLinhas;
    private final int gridColunas;
    private final int gridTamanhoCelula;
    private final Color[][] fundo;
    
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

    //metodos de movimento do bloco
    
    public void moverBlocoDireita() {
        if(checarDir() == false) {
            return;
        }
        bloco.moverLadoDireito();
        repaint();
    }
    
    public void moverBlocoEsquerda() {
        if(checarEsq() == false) {
            return;
        }
        bloco.moverLadoEsquerdo();
        repaint();
    }
    
    public void droparBloco() {
        while(checarFinal()) {
            bloco.moverParaBaixo();
        }
        repaint();
    }
    
    public void rotacionarBloco() {
        bloco.rotacionar();
        repaint();
    }  
    
    //Verificar se o bloco chegou no final da área do jogo
    private boolean checarFinal() {
        if(bloco.inferior() == gridLinhas) {
            return false;
        }
        
        /*
        Esse monte de codigo abaixo serve para verificar se o quadrado do grid abaixo é nulo ou não.
        O fato dele ser nulo, significa que não tem nenhum bloco ""pintado"" nesse local, e significa
        que o bloco pode mover para baixo, quando ele detecta que tem 0 ou 1, ele para de mover
        */
        int[][]forma = bloco.getForma();
        int a = bloco.getAltura();
        int l = bloco.getLargura();
        
        for(int col=0; col < l; col++) {
            for(int lin=a-1;lin >= 0;lin--) {
                if(forma[lin][col] != 0) {
                    int x = col + bloco.getX();
                    int y = lin + bloco.getY() + 1;
                    if(y < 0) {
                        break;
                    }
                    if(fundo[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checarEsq() {
        if(bloco.getBordaEsq() == 0) {
            return false;
        }
        
        /*
        Esse monte de codigo abaixo serve para verificar se o quadrado do grid abaixo é nulo ou não.
        O fato dele ser nulo, significa que não tem nenhum bloco ""pintado"" nesse local, e significa
        que o bloco pode mover para baixo, quando ele detecta que tem 0 ou 1, ele para de mover
        */
        int[][]forma = bloco.getForma();
        int a = bloco.getAltura();
        int l = bloco.getLargura();
        
        for(int lin=0; lin < a; lin++) {
            for(int col=0;col < l ;col++) {
                if(forma[lin][col] != 0) {
                    int x = col + bloco.getX() - 1;
                    int y = lin + bloco.getY();
                    if(y < 0) {
                        break;
                    }
                    if(fundo[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checarDir() {
        if(bloco.getBordaDir() == gridColunas) {
            return false;
        }
        
        /*
        Esse monte de codigo abaixo serve para verificar se o quadrado do grid abaixo é nulo ou não.
        O fato dele ser nulo, significa que não tem nenhum bloco ""pintado"" nesse local, e significa
        que o bloco pode mover para baixo, quando ele detecta que tem 0 ou 1, ele para de mover
        */
        int[][]forma = bloco.getForma();
        int a = bloco.getAltura();
        int l = bloco.getLargura();
        
        for(int lin=0; lin < a; lin++) {
            for(int col= l - 1;col >= 0 ;col--) {
                if(forma[lin][col] != 0) {
                    int x = col + bloco.getX() + 1;
                    int y = lin + bloco.getY();
                    if(y < 0) {
                        break;
                    }
                    if(fundo[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        
        
        return true;
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