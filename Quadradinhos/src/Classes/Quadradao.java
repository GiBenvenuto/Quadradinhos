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
public class Quadradao {

    private Quadradinho q[][];

    public Quadradao() {
        int cont = 0;
        this.q = new Quadradinho[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.q[i][j] = new Quadradinho(cont, i, j);
                cont++;
            }//for i
        }//for j    
    }

    public Quadradinho[][] getQ() {
        return q;
    }

    public void setQ(Quadradinho[][] q) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.q[i][j] = q[i][j];
            }//for i
        }//for j    

    }

}
