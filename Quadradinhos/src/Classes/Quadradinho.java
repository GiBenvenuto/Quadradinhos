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
public class Quadradinho {
    private int num;
    private int posI;
    private int posJ;
    private int dist;

    public Quadradinho(int num, int posI, int posJ) {
        this.num = num;
        this.posI = posI;
        this.posJ = posJ;
        this.dist = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPosI() {
        return posI;
    }

    public void setPosI(int posI) {
        this.posI = posI;
    }

    public int getPosJ() {
        return posJ;
    }

    public void setPosJ(int posJ) {
        this.posJ = posJ;
    }

    public int getDist() {
        return calculaDist();
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    
    
    

    
    public int calculaDist(){
        int posFI = this.num / 3;
        int posFJ = this.num % 3;
        
        //Distância de Mahhattan
        this.dist = Math.abs(posFI - posI) + Math.abs(posFJ - posJ);        
        
        return dist;
    }
    
    
    
}
