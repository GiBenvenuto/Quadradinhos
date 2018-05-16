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
    private int Tdis;

    public Quadradinho() {
    }

    public Quadradinho(int num, int Tdis) {
        this.num = num;
        this.Tdis = Tdis;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTdis() {
        return Tdis;
    }

    public void setTdis(int Tdis) {
        this.Tdis = Tdis;
    }

    @Override
    public int compare(Quadradinho o1, Quadradinho o2) {
        return (o1.getTdis() - o2.getTdis());
    }

    
    
    
}
