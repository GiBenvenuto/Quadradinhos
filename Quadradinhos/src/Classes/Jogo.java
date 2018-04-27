/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Gi
 */
public class Jogo {

    private Quadradinho q[][];

    public Jogo() {
        int cont = 1;
        this.q = new Quadradinho[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.q[i][j] = new Quadradinho(cont, i, j);
                cont++;
            }//for i
        }//for j
    }//construtor
    
    public void heuristicaNivel1(){
    
    }
}
