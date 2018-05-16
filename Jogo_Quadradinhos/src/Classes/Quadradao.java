/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gi
 */
public class Quadradao {

    private int[][] q;
    private Quadradao pai;
    private ArrayList<Integer> qViz;

    public Quadradao() {
        int cont = 0;
        this.pai = null;
        this.q = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.q[i][j] = cont;
                cont++;
            }//for i
        }//for j

    }

    public int[][] getQ() {
        return q;
    }

    public void setQ(int[][] q) {
        this.q = q;
    }

    public ArrayList<Integer> getqViz() {
        return qViz;
    }

    public void setqViz(ArrayList<Integer> qViz) {
        this.qViz = qViz;
    }

    public Quadradao getPai() {
        return pai;
    }

    public void setPai(Quadradao pai) {
        this.pai = pai;
    }

    public void setQ(Quadradao q) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.q[i][j] = q.getQ()[i][j];
            }//for i
        }//for j    

    }

}
