package br.inatel.projetofinal.tetris.gamecomponents;

import br.inatel.projetofinal.tetris.Main;
import br.inatel.projetofinal.tetris.gui.GameArea;
import br.inatel.projetofinal.tetris.gui.GameForm;

public class GameThread extends Thread {
    private final GameArea ga;
    private final GameForm gf;
    private int score;
    private int level = 1;
    private int scorePorNivel = 3;
    private int vel = 1000;
    private int velPorNivel = 100;
    
    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
        gf.atualizaPontos(score);
        gf.atualizaNivel(level);
    }
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while(true) {
            
            ga.gerarBloco();
            
            while(ga.gravidadeBloco()) {
                try {
                    Thread.sleep(vel);
                } catch (InterruptedException ex) {
                    return;
                    //java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }                
            }
            //Verificar no game thread se o bloco saiu do grid para assim mostrar o GameOver
            if(ga.isBlocoForaDoGrid()) {
                Main.fimDeJogo(score);
                break;
            }
            ga.moverParaFundo();
            score += ga.limparLinha();
            gf.atualizaPontos(score);
            
            int lvl = score / scorePorNivel + 1;
            if(lvl > level) {
                level = lvl;
                gf.atualizaNivel(level);
                vel -= velPorNivel;
            }
        }
    }
}
