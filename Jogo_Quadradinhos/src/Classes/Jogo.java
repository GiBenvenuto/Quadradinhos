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

    private Quadradao q;
    private int cont;
    private ArrayList<Integer> trocas;
    private final int NIVEL_1 = 1;
    private final int NIVEL_2 = 2;

    public Jogo() {

        this.q = new Quadradao();
    }//construtor

    public ArrayList<Integer> getTrocas() {
        return trocas;
    }

    public void setTrocas(ArrayList<Integer> trocas) {
        this.trocas = trocas;
    }

    public Quadradao getQ() {
        return q;
    }

    public void setQ(Quadradao q) {
        this.q = q;
    }

    public int distanciaManhattan(int num, int i, int j) {
        int posFI = num / 3;
        int posFJ = num % 3;

        //Distância de Mahhattan
        return Math.abs(posFI - i) + Math.abs(posFJ - j);
    }

    public boolean verificaSolucao(Quadradao q) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (distanciaManhattan(q.getQ()[i][j], i, j) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList vizinhos(int i, int j, Quadradao q) {
        ArrayList<Integer> viz = new ArrayList();

        if ((i - 1) >= 0) {
            viz.add(q.getQ()[i - 1][j]);
        }
        if ((i + i) < 3) {
            viz.add(q.getQ()[i + 1][j]);
        }
        if ((j - 1) >= 0) {
            viz.add(q.getQ()[i][j - 1]);
        }
        if ((j + 1) < 3) {
            viz.add(q.getQ()[i][j + 1]);
        }
        return viz;
    }

    public void troca(int qTroca, Quadradao q) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (q.getQ()[i][j] == 8) {
                    q.getQ()[i][j] = -1; //marca o item que vai ser trocado
                } else if (q.getQ()[i][j] == qTroca) {
                    q.getQ()[i][j] = 8; //insere no lugar do quadradinho a ser trocado
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (q.getQ()[i][j] == -1) {
                    q.getQ()[i][j] = qTroca; // insere no lugar do vazio
                    break;
                }
            }
        }
    }

    public int aleatorio() {
        int qTroca;
        Random sort = new Random();

        ArrayList viz = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (q.getQ()[i][j] == 8) {
                    viz = vizinhos(i, j, this.q);
                    break;
                }
            }
        }

        qTroca = (Integer) viz.get(sort.nextInt(viz.size()));
        troca(qTroca, this.q);

        return qTroca;
    }

    public ArrayList embaralhar(int passos) {
        this.trocas = new ArrayList();
        for (int i = 0; i < passos; i++) {
            trocas.add(aleatorio());
        }
        return trocas;
    }

    public ArrayList resolverAleatorio() {
        this.trocas = new ArrayList();
        while (!verificaSolucao(this.q)) {
            trocas.add(aleatorio());
        }
        return trocas;
    }

    public boolean compara(Quadradao c, Quadradao q) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (c.getQ()[i][j] != q.getQ()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verificaLoop(ArrayList<Quadradao> config, Quadradao q) {
        for (Quadradao c : config) {
            if (compara(c, q)) {
                return true; //tem loop
            }
        }
        return false;
    }

    public int calculaDistancia(int[][] qDis) {
        int soma = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                soma += distanciaManhattan(qDis[i][j], i, j); //calcula a disntacia de todos os quadradinhos
            }
        }
        return soma;
    }

    public ArrayList<Quadradinho> getDistancias(ArrayList<Integer> viz, Quadradao q) {
        ArrayList<Quadradinho> auxQ = new ArrayList();
        Quadradinho qZinho;
        Quadradao qAux = new Quadradao();

        for (Integer v : viz) {
            qAux.setQ(q);
            troca(v, qAux);
            qZinho = new Quadradinho(v, calculaDistancia(qAux.getQ())); //Pega o valor da distancia
            auxQ.add(qZinho);
            troca(v, qAux);
        }
        return auxQ;
    }

    public ArrayList<Integer> getVizinhos(Quadradao q) {
        ArrayList<Integer> viz = new ArrayList();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (q.getQ()[i][j] == 8) {
                    viz = vizinhos(i, j, q); //Pega os vizinhos                }
                    break;
                }
            }
        }
        return viz;
    }

    public ArrayList<Integer> ordenaJogadas1(Quadradao q) {
        ArrayList<Integer> viz;
        ArrayList<Quadradinho> auxQ;

        viz = getVizinhos(q);
        auxQ = getDistancias(viz, q);
        Collections.sort(auxQ, new Quadradinho()); //Ordena 
        viz.clear();
        for (Quadradinho qDinho : auxQ) {
            viz.add(qDinho.getNum()); //Volta pro array ordenado
        }

        return viz;

    }

    public ArrayList<Integer> ordenaJogadas3(Quadradao q) {
        ArrayList<Integer> viz;
        ArrayList<Quadradinho> auxQ = new ArrayList();
        Quadradinho qZinho;
        Quadradao qAux = new Quadradao();
        int dist = 0;

        viz = getVizinhos(q);

        for (Integer v : viz) {
            qAux.setQ(q);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (v == qAux.getQ()[i][j]) {
                        dist = distanciaManhattan(qAux.getQ()[i][j], i, j);
                        break;
                    }//if
                }//for j
            }//for i
            if (dist == 0) {//Muda o peso dos que estão no lugar
                dist = 20;
            }
            qZinho = new Quadradinho(v, dist);
            auxQ.add(qZinho);
        }//For dos vizinhos

        Collections.sort(auxQ, new Quadradinho()); //Ordena 
        viz.clear();
        for (Quadradinho qDinho : auxQ) {
            viz.add(qDinho.getNum()); //Volta pro array ordenado
        }

        return viz;

    }

    public ArrayList ordenaJogadas2(Quadradao q) {
        Quadradao novoQ = new Quadradao();
        ArrayList<Integer> viz;
        ArrayList<Quadradinho> auxQ, auxN2;
        viz = getVizinhos(q);
        auxQ = getDistancias(viz, q);//Primeira iteração

        for (Quadradinho qN1 : auxQ) {//pra cada quadradinho do primeiro nível
            novoQ.setQ(q);
            troca(qN1.getNum(), novoQ); //Faz a troca
            viz = getVizinhos(novoQ); //Pega os novos vizinhos
            auxN2 = getDistancias(viz, novoQ); //Pega as distancias desses
            Collections.sort(auxN2, new Quadradinho()); //Ordena as disntancias
            qN1.setTdis(qN1.getTdis() + auxN2.get(0).getTdis()); //A distancia final é a soma do primeiro caminho
            // com o menos caminho do segundo nível
            troca(qN1.getNum(), novoQ); //destroca                                                     
        }

        Collections.sort(auxQ, new Quadradinho()); //Ordena 
        viz.clear();
        for (Quadradinho qDinho : auxQ) {
            viz.add(qDinho.getNum()); //Volta pro array ordenado
        }

        return viz;
    }

    public int heuristicaNiveis(Quadradao q, int nivel) {
        int size, cont = 0, littleQ;
        boolean aux = false;
        ArrayList<Quadradao> config = new ArrayList();
        Quadradao novoQ;
        this.trocas.clear();

        //Pegar os vizinhos ordenados
        if (nivel == NIVEL_1) {   //Primeira heurística
            q.setqViz(ordenaJogadas1(q)); 
        } else if (nivel == NIVEL_2) { //Segunda heurística
            q.setqViz(ordenaJogadas2(q)); 
        } else {        
            q.setqViz(ordenaJogadas3(q)); //heurística própria
        }

        while (!verificaSolucao(q)) {// Enquanto não encontrar a solução

            if (q.getqViz().isEmpty()) {//Se ta vazio já esgotou as possibiliadade 
                q = q.getPai();  //Volta para o pai dessa configuração  
                size = this.trocas.size() - 1;
                if (size >= 0) {
                    this.trocas.remove(size); //Descarta a troca feita
                }
            }

            while (!q.getqViz().isEmpty()) { //Enquanto não percorrer todos os vizinhos
                cont++; //contador de iterações

                littleQ = q.getqViz().remove(0); //Pega o menor caminho

                novoQ = new Quadradao();
                novoQ.setQ(q); //Usa um novo tabuleiro pra não perder o último
                troca(littleQ, novoQ); //Troca o quadradinho com o espaço em branco

                if (verificaLoop(config, novoQ)) { //Verifica se existe loop
                    troca(littleQ, novoQ); //Se existe a última troca é desfeita

                }//loop
                else { //Se não tiver loop
                    config.add(q); //Adiciona as configurações existentes
                    this.trocas.add(littleQ); //Adicona a troca realizada no caminho
                    novoQ.setPai(q); //Seta o pai do quadrado
                    q = novoQ; // O anterior recebe o novo

                    //Pegar os vizinhos ordenados 
                    if (nivel == NIVEL_1) {
                        q.setqViz(ordenaJogadas1(q)); 

                    } else if (nivel == NIVEL_2) {
                        q.setqViz(ordenaJogadas2(q)); 
                    } else {
                        q.setqViz(ordenaJogadas3(q)); 
                    }
                    aux = true;
                    break;
                }
            }//while

            if (aux) {//Se saiu do while pq alcançou uma nova configuração
                aux = false;
            } else { //Se saiu do while pq todos os vizinhos falharam
                size = this.trocas.size() - 1;
                if (size >= 0) {
                    this.trocas.remove(size);
                }
                q = q.getPai(); // volta para o anterior

            }
        }
        this.q = q;
        return cont;

    }

}
