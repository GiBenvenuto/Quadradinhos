/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Comparator;

/**
 *
 * @author Gi
 */
public class Quadradinho implements Comparator<Quadradinho>{
    private int num;
    private int posI;
    private int posJ;
    private int dist;
    
    public Quadradinho (){
        
    }

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

    @Override
    public int compare(Quadradinho o1, Quadradinho o2) {
        return (o1.calculaDist() - o2.calculaDist());
    }
    
    
    
}
