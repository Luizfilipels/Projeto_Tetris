package br.inatel.projetofinal.tetris;

import br.inatel.projetofinal.tetris.gui.GameForm;
import br.inatel.projetofinal.tetris.gui.LeaderboardForm;
import br.inatel.projetofinal.tetris.gui.StartupForm;
import javax.swing.JOptionPane;



public class Main {
    
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    
    
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
