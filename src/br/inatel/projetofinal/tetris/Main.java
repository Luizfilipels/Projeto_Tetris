package br.inatel.projetofinal.tetris;

import br.inatel.projetofinal.tetris.gui.GameForm;
import br.inatel.projetofinal.tetris.gui.LeaderboardForm;
import br.inatel.projetofinal.tetris.gui.StartupForm;
import javax.swing.JOptionPane;

/*
Em resumo, a classe Main controla a lógica principal do jogo Tetris, 
interagindo com os formulários de inicialização (StartupForm), 
jogo (GameForm) e leaderboard (LeaderboardForm). Os métodos da classe Main 
são chamados quando os botões correspondentes são clicados 
nos formulários, iniciando o jogo, exibindo o ranking, voltando ao menu principal e 
adicionando o jogador ao leaderboard.
*/


public class Main {
    
    
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    
    //Metodos abaixo que casam com os botões do Forms
    public static void start() {
        gf.setVisible(true);
        gf.iniciarJogo();
    }
    
    public static void mostrarRanking() {
        lf.setVisible(true);
    }
    
    public static void mostrarStartupMenu() {
        sf.setVisible(true);
    }
    
    public static void fimDeJogo(int score) {
        String nomePlayer = JOptionPane.showInputDialog("Fim de Jogo!!\nEntre com o seu nome no campo abaixo:");
        gf.setVisible(false);
        lf.addPlayer(nomePlayer, score);
    }
    
    
    //Criando o GameForm, StartupForm e o LaderboardForm na Main
    //Logo após isso, é setado a visibilidade do StartupForm com true
    public static void main(String[] args) {
        
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gf = new GameForm();
                sf = new StartupForm();
                lf = new LeaderboardForm();
        
                sf.setVisible(true);   
            }
        });    
        
        
        
        
    }
}
