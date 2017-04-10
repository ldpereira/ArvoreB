/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb;

import java.io.Serializable;

/**
 *
 * @author ldpereira, amleicht
 */
public class Node implements Serializable {

    private Node nodoEsquerda;
    private Node nodoDireita;
    private Node pai;
    private int chave;
    private int balanceado;

    public Node(int chave) {
        balanceado = 0;
        this.chave = chave;
    }

    public Node getNodoEsquerda() {
        return nodoEsquerda;
    }

    public void setNodoEsquerda(Node nodoEsquerda) {
        this.nodoEsquerda = nodoEsquerda;
    }

    public Node getNodoDireita() {
        return nodoDireita;
    }

    public void setNodoDireita(Node nodoDireita) {
        this.nodoDireita = nodoDireita;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getBalanceado() {
        return balanceado;
    }

    public void setBalanceado(int balanceado) {
        this.balanceado = balanceado;
    }

    @Override
    public String toString() {
        return "" + chave;
    }

}
