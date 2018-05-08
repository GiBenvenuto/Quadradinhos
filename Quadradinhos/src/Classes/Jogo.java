/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Gi
 */
public class Jogo {

    private Quadradinho qVazio;
    private Quadradao q;

    public Jogo() {

        this.q = new Quadradao();
        this.qVazio = this.q.getQ()[2][2];
    }//construtor

    public boolean verificaSolucao() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.q.getQ()[i][j].calculaDist() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList vizinhos(int i, int j) {
        ArrayList<Quadradinho> viz = new ArrayList();

        if ((i - 1) >= 0) {
            viz.add(this.q.getQ()[i - 1][j]);
        }
        if ((i + i) < 3) {
            viz.add(this.q.getQ()[i + 1][j]);
        }
        if ((j - 1) >= 0) {
            viz.add(this.q.getQ()[i][j - 1]);
        }
        if ((j + 1) < 3) {
            viz.add(this.q.getQ()[i][j + 1]);
        }
        return viz;
    }

    public void troca(Quadradinho q1) {
        int i, j;
        i = q1.getPosI();
        j = q1.getPosJ();

        q1.setPosI(this.qVazio.getPosI());
        q1.setPosJ(this.qVazio.getPosJ());

        this.q.getQ()[q1.getPosI()][q1.getPosJ()] = q1;
        this.qVazio.setPosI(i);
        this.qVazio.setPosJ(j);
        this.q.getQ()[i][j] = this.qVazio;

    }

    public int aleatorio() {
        int n;
        Random sort = new Random();
        Quadradinho qTroca;
        ArrayList viz;
        viz = vizinhos(this.qVazio.getPosI(), this.qVazio.getPosJ());
        qTroca = (Quadradinho) viz.get(sort.nextInt(viz.size()));
        n = qTroca.getNum();
        troca(qTroca);

        return n;
    }

    public ArrayList embaralhar(int passos) {
        ArrayList<Integer> trocas = new ArrayList();
        for (int i = 0; i < passos; i++) {
            trocas.add(aleatorio());
        }
        return trocas;
    }

    public ArrayList resolverAleatorio() {
        ArrayList<Integer> trocas = new ArrayList();
        while (!verificaSolucao()) {
            trocas.add(aleatorio());
        }
        return trocas;
    }

    public boolean verificaLoop(ArrayList<Quadradao> config, Quadradao q) {
        for (Quadradao c : config) {
            if (c.equals(q)) {
                return true; // tem loop            
            }
        }
        config.add(q);
        return false;
    }

    public ArrayList heuristicaNivelUm(int i, ArrayList<Integer> trocas, ArrayList<Quadradao> config) {
        ArrayList<Quadradinho> qViz = null;
        Quadradao aux = new Quadradao();
        if (verificaSolucao()) {
            return trocas;
        }

        qViz = vizinhos(this.qVazio.getPosI(), this.qVazio.getPosJ());
        Collections.sort(qViz, new Quadradinho());
        if (i < qViz.size()) {
            trocas.add(qViz.get(i).getNum());
            troca(qViz.get(i));
            aux.setQ(this.q.getQ());

            if (verificaLoop(config, aux)) {
                heuristicaNivelUm (i+1, trocas);
            } else {

                heuristicaNivelUm(trocas, config);
            }

        }

        return null;
    }

    public ArrayList heuristicaNivel1() {
        ArrayList<Integer> trocas = new ArrayList();
        ArrayList<Quadradinho> qViz = null;

        while (!verificaSolucao()) {
            qViz = vizinhos(this.qVazio.getPosI(), this.qVazio.getPosJ());
            Collections.sort(qViz, new Quadradinho());
            if (verificaLoop(trocas, qViz.get(0).getNum())) {
                qViz.remove(0);
            }

            qViz.clear();

        }

        return trocas;

    }
}
