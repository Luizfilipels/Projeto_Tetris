package br.inatel.projetofinal.tetris.gamecomponents;

import br.inatel.projetofinal.tetris.Main;
import br.inatel.projetofinal.tetris.gui.GameArea;
import br.inatel.projetofinal.tetris.gui.GameForm;


/*
a classe GameThread controla a lógica principal do jogo Tetris,
atualizando o estado do jogo, a pontuação, o nível e a velocidade 
do jogo à medida que o jogador joga.
*/


public class GameThread extends Thread {
    private final GameArea ga;
    private final GameForm gf;
    private int score = 0;
    private int level = 1;
    private final int scorePorNivel = 10;
    private int vel = 1000;
    private final int velPorNivel = 100;
    
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
