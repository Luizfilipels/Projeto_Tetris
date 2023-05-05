package br.inatel.projetofinal.tetris.gamecomponents;

import br.inatel.projetofinal.tetris.gui.GameArea;

public class GameThread extends Thread {
    private final GameArea ga;
    
    public GameThread(GameArea ga) {
        this.ga = ga;
    }
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while(true) {
            try {
                ga.gravidadeBloco();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
    }
}
