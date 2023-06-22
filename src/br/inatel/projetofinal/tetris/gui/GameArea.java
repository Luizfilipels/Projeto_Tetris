package br.inatel.projetofinal.tetris.gui;

import br.inatel.projetofinal.tetris.blocos.*;
import br.inatel.projetofinal.tetris.gamecomponents.BlocoTetris;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
    

    /*
    Algumas das principais funcionalidades do código incluem:

    - Definir as propriedades do painel do jogo com base em um painel de espaço reservado.
    - Inicializar uma matriz para armazenar as cores dos blocos já posicionados no jogo.
    - Gerar aleatoriamente um bloco do jogo Tetris e posicioná-lo no topo do grid.
    - Verificar se o bloco atual está fora dos limites do grid do jogo.
    - Mover o bloco para baixo, se não houver colisão com outros blocos ou com o limite inferior do grid.
    - Mover o bloco para a direita, esquerda, ou fazer ele "cair" rapidamente.
    - Rotacionar o bloco atualmente em movimento.
    - Verificar se o bloco chegou ao final do grid do jogo ou se há colisão com outros blocos.
    - Limpar linhas completas no grid, removendo-as e deslocando as linhas superiores para baixo.
    - Desenhar o bloco em movimento e o grid de fundo na interface gráfica.
    
*/

    
public class GameArea extends JPanel {
    
    private final int gridLinhas;
    private final int gridColunas;
    private final int gridTamanhoCelula;
    private Color[][] fundo;
    
    private BlocoTetris[] blocos;
    
    private BlocoTetris bloco;
    
    //Construtor
    public GameArea(JPanel placeholder, int colunas) {
        //placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColunas = colunas;
        gridTamanhoCelula = this.getBounds().width / gridColunas;
        gridLinhas = this.getBounds().height / gridTamanhoCelula;
        
        
        
        
        blocos = new BlocoTetris[]{ new IShape(),
                                    new JShape(),
                                    new LShape(),
                                    new OShape(),
                                    new SShape(),
                                    new TShape(),
                                    new ZShape()
        };
    }
    
    public void initFundoArray() {
        fundo = new Color[gridLinhas][gridColunas];
    }
    
    public void gerarBloco() {
        Random r = new Random();
        
        bloco = blocos[r.nextInt(blocos.length)];
        bloco.spawn(gridColunas);
    }
    
    //Função para checar se o bloco está fora do grid, tentei fazer um try catch, mas fiquei horas e horas
    //e não sai do lugar, 100% burrice.
    public boolean isBlocoForaDoGrid() {
        if(bloco.getY() < 0) {
            bloco = null;
            return true;
        }
        return false;
    }
    
    //"""Gravidade""" do bloco
    public boolean gravidadeBloco() {
        //Verifica se o bloco chegou no final
        if(checarFinal() == false) {
            return false;
        } else {
            bloco.moverParaBaixo();
            repaint();
            return true;
        } 
    }

    //metodos de movimento do bloco
    
    public void moverBlocoDireita() {
        if(bloco == null) return;
        if(checarDir() == false) {
            return;
        }
        bloco.moverLadoDireito();
        repaint();
    }
    
    public void moverBlocoEsquerda() {
        if(bloco == null) return;
        if(checarEsq() == false) {
            return;
        }
        bloco.moverLadoEsquerdo();
        repaint();
    }
    
    public void droparBloco() {
        if(bloco == null) return;
        while(checarFinal()) {
            bloco.moverParaBaixo();
        }
        repaint();
    }
    
    public void rotacionarBloco() {
        if(bloco == null) return;
        bloco.rotacionar();
        
        if(bloco.getBordaEsq() < 0) bloco.setX(0);
        if(bloco.getBordaDir() >= gridColunas) bloco.setX(gridColunas - bloco.getLargura());
        if(bloco.inferior() >= gridLinhas) bloco.setY(gridLinhas - bloco.getAltura());
        
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
    
    public int limparLinha() {
        boolean linhaCompleta;
        int linhasLimpas = 0;
        
        for(int l = gridLinhas -1; l >= 0; l--) {
            linhaCompleta = true;
            for(int c = 0; c < gridColunas; c++) {
                if(fundo[l][c] == null) {
                    linhaCompleta = false;
                    break;
                }
            }
            
            //Se o boolean linha completa for true, ele vai limpar a respectiva linha
            if(linhaCompleta) {
                linhasLimpas++;
                limpaLinha(l);
                shiftDown(l);
                limpaLinha(0); //Para limpar a linha 0, afinal ela shiftou tudo pra baixo.
                
                l++; //Yeah Mr. White, Science.
                
                repaint();
            }
        }
        return linhasLimpas;
    }
    
    private void limpaLinha(int l) {
        for(int i = 0;i < gridColunas; i++) {
            fundo[l][i] = null;
        }        
    }
    
    //Vai pegar tudo que está na linha de cima e jogar para a linha debaixo
    //após limpar ela respectivamente.
    private void shiftDown(int l) {
        for(int linha = l; linha >0; linha--) {
            for(int col = 0; col < gridColunas; col++) {
                fundo[linha][col] = fundo[linha - 1][col];
            }
        }
    }
    
    //Mover o bloco para o background do jogo
    public void moverParaFundo() {
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
    
    //metodo para desenhar o bloco na GameArea (considerando como 1 <= pintado ; 0 <= não pintado
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