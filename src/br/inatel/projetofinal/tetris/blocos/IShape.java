package br.inatel.projetofinal.tetris.blocos;

import br.inatel.projetofinal.tetris.gamecomponents.BlocoTetris;

public class IShape extends BlocoTetris{
    
    public IShape() {
        super(new int[][]{ {1,1,1,1} });
    }
    
    @Override
    public void rotacionar() {
        super.rotacionar();
        
        if(this.getLargura() == 1) {
            this.setX(this.getX() +1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
    
}
