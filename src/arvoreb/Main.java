/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvoreb;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ldpereira
 */
public class Main {
    
    public static void main(String[] args) {
        ArvoreB arvoreB = new ArvoreB(2);
        /*for (int i = 0; i < 100; i++) {
            arvoreB.inserir(i);
        }*/
        arvoreB.inserir(10);
        arvoreB.inserir(40);
        arvoreB.inserir(80);
        arvoreB.inserir(120);
        arvoreB.inserir(30);
        arvoreB.inserir(90);
        arvoreB.inserir(130);
       /* arvoreB.inserir(100);
        arvoreB.inserir(60);
        arvoreB.inserir(110);
        arvoreB.inserir(140);
        arvoreB.inserir(50);
        arvoreB.inserir(20);
        arvoreB.inserir(150);
        arvoreB.inserir(70);
        arvoreB.inserir(45);
        arvoreB.inserir(115);
        arvoreB.inserir(145);
        arvoreB.inserir(55);
        arvoreB.inserir(25);
        arvoreB.inserir(155);
        arvoreB.inserir(75);
        arvoreB.inserir(65);
        arvoreB.inserir(112);
        arvoreB.inserir(142);
        arvoreB.inserir(502);
        arvoreB.inserir(202);
        arvoreB.inserir(152);
        arvoreB.inserir(78);
        arvoreB.inserir(68);
        arvoreB.inserir(118);
        arvoreB.inserir(148);
        arvoreB.inserir(59);
        arvoreB.inserir(29);
        arvoreB.inserir(159);
        arvoreB.inserir(79);*/
        
        ArrayList<Node> lista = arvoreB.emOrdem();
        
        for (Node nodo : lista) {
            System.out.print("node = " + nodo.getChave());
        }
        
        System.out.println("Root= " + arvoreB.getRoot().getChave());
        arvoreB.debug(arvoreB.getRoot(),new LinkedList<Node>());
        
        arvoreB.gravar();
        System.out.println(" nova arvore \n");
        arvoreB.ler();
        ArrayList<Node> listaRetorno = arvoreB.emOrdem();
        
        lista = arvoreB.emOrdem();
        
        for (Node nodo : lista) {
            System.out.print("node = " + nodo.getChave());
        }
        
        System.out.println("Root= " + arvoreB.getRoot().getChave());
        arvoreB.debug(arvoreB.getRoot(), new LinkedList<Node>());
    }
    
}
