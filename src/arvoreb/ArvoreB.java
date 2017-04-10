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
public class ArvoreB {

    private Node root;
    private int ordem;

    public ArvoreB(int ordem) {
        this.ordem = 2;
    }
    
    public void inserir(int valor) {
        Node n = new Node(valor);
        inserirNode(this.root, n);
    }

    public void inserirNode(Node nodoPai, Node novoNode) {
        if (nodoPai == null) {
            this.root = novoNode;
        } else {

            if (novoNode.getChave() < nodoPai.getChave()) {
                if (nodoPai.getNodoEsquerda() == null) {
                    nodoPai.setNodoEsquerda(novoNode);
                    novoNode.setPai(nodoPai);

                    balanceamentoRecursivo(nodoPai);
                } else {
                    inserirNode(nodoPai.getNodoEsquerda(), novoNode);
                }

            } else if (novoNode.getChave() > nodoPai.getChave()) {
                if (nodoPai.getNodoDireita() == null) {
                    nodoPai.setNodoDireita(novoNode);
                    novoNode.setPai(nodoPai);

                    balanceamentoRecursivo(nodoPai);
                } else {
                    inserirNode(nodoPai.getNodoDireita(), novoNode);
                }
            }
        }
    }

    public void balanceamentoRecursivo(Node nodoAtual) {
        setBalanceado(nodoAtual);
        int balance = nodoAtual.getBalanceado();

        if (balance == -ordem) {

            if (altura(nodoAtual.getNodoEsquerda().getNodoEsquerda()) >= altura(nodoAtual.getNodoEsquerda().getNodoDireita())) {
                nodoAtual = rotacaoDireita(nodoAtual);
            } else {
                nodoAtual = duplaRotacaoEsquerdaDireita(nodoAtual);
            }
        } else if (balance == ordem) {
            if (altura(nodoAtual.getNodoDireita().getNodoDireita()) >= altura(nodoAtual.getNodoDireita().getNodoEsquerda())) {
                nodoAtual = rotacaoEsquerda(nodoAtual);
            } else {
                nodoAtual = doubleRotateRightLeft(nodoAtual);
            }
        }

        if (nodoAtual.getPai() != null) {
            balanceamentoRecursivo(nodoAtual.getPai());
        } else {
            this.root = nodoAtual;            
        }
    }

    public void remover(int valor) {
        removerNodo(this.root, valor);
    }

    public void removerNodo(Node p, int q) {
        if (p == null) {
            return;
        } else {
            if (p.getChave() > q) {
                removerNodo(p.getNodoEsquerda(), q);
            } else if (p.getChave() < q) {
                removerNodo(p.getNodoDireita(), q);
            } else if (p.getChave() == q) {
                removerNodoEncontrado(p);
            }
        }
    }

    public void removerNodoEncontrado(Node q) {
        Node r;
        if (q.getNodoEsquerda() == null || q.getNodoDireita() == null) {
            // the root is deleted
            if (q.getPai() == null) {
                this.root = null;
                q = null;
                return;
            }
            r = q;
        } else {
            r = sucessor(q);
            q.setChave(r.getChave());
        }

        Node p;
        if (r.getNodoEsquerda() != null) {
            p = r.getNodoEsquerda();
        } else {
            p = r.getNodoDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.root = p;
        } else {
            if (r == r.getPai().getNodoEsquerda()) {
                r.getPai().setNodoEsquerda(p);
            } else {
                r.getPai().setNodoDireita(p);
            }
            balanceamentoRecursivo(r.getPai());
        }
        r = null;
    }

    public Node rotacaoEsquerda(Node nodo) {

        Node nodoDireita = nodo.getNodoDireita();
        nodoDireita.setPai(nodo.getPai());

        nodo.setNodoDireita(nodoDireita.getNodoEsquerda());

        if (nodo.getNodoDireita() != null) {
            nodo.getNodoDireita().setPai(nodo);
        }

        nodoDireita.setNodoEsquerda(nodo);
        nodo.setPai(nodoDireita);

        if (nodoDireita.getPai() != null) {
            if (nodoDireita.getPai().getNodoDireita() == nodo) {
                nodoDireita.getPai().setNodoDireita(nodoDireita);
            } else if (nodoDireita.getPai().getNodoEsquerda() == nodo) {
                nodoDireita.getPai().setNodoEsquerda(nodoDireita);
            }
        }

        setBalanceado(nodo);
        setBalanceado(nodoDireita);

        return nodoDireita;
    }

    public Node rotacaoDireita(Node nodo) {

        Node nodoEsquerdo = nodo.getNodoEsquerda();
        nodoEsquerdo.setPai(nodo.getPai());

        nodo.setNodoEsquerda(nodoEsquerdo.getNodoDireita());

        if (nodo.getNodoEsquerda() != null) {
            nodo.getNodoEsquerda().setPai(nodo);
        }

        nodoEsquerdo.setNodoDireita(nodo);
        nodo.setPai(nodoEsquerdo);

        if (nodoEsquerdo.getPai() != null) {
            if (nodoEsquerdo.getPai().getNodoDireita() == nodo) {
                nodoEsquerdo.getPai().setNodoDireita(nodoEsquerdo);
            } else if (nodoEsquerdo.getPai().getNodoEsquerda() == nodo) {
                nodoEsquerdo.getPai().setNodoEsquerda(nodoEsquerdo);
            }
        }

        setBalanceado(nodo);
        setBalanceado(nodoEsquerdo);

        return nodoEsquerdo;
    }

    public Node duplaRotacaoEsquerdaDireita(Node u) {
        u.setNodoEsquerda(rotacaoEsquerda(u.getNodoEsquerda()));
        return rotacaoDireita(u);
    }

    public Node doubleRotateRightLeft(Node u) {
        u.setNodoDireita(rotacaoDireita(u.getNodoDireita()));
        return rotacaoEsquerda(u);
    }

    public Node sucessor(Node q) {
        if (q.getNodoDireita() != null) {
            Node r = q.getNodoDireita();
            while (r.getNodoEsquerda() != null) {
                r = r.getNodoEsquerda();
            }
            return r;
        } else {
            Node p = q.getPai();
            while (p != null && q == p.getNodoDireita()) {
                q = p;
                p = q.getPai();
            }
            return p;
        }
    }

    private int altura(Node cur) {
        if (cur == null) {
            return -1;
        }
        if (cur.getNodoEsquerda() == null && cur.getNodoDireita() == null) {
            return 0;
        } else if (cur.getNodoEsquerda() == null) {
            return 1 + altura(cur.getNodoDireita());
        } else if (cur.getNodoDireita() == null) {
            return 1 + altura(cur.getNodoEsquerda());
        } else {
            return 1 + maximo(altura(cur.getNodoEsquerda()), altura(cur.getNodoDireita()));
        }
    }

    private int maximo(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }

    public void debug(Node n, LinkedList<Node> lista) {
        int l = 0;
        int r = 0;
        int p = 0;
        if (n.getNodoEsquerda() != null) {
            l = n.getNodoEsquerda().getChave();
        }
        if (n.getNodoDireita() != null) {
            r = n.getNodoDireita().getChave();
        }
        if (n.getPai() != null) {
            p = n.getPai().getChave();
        }
        
        lista.add(n);

        System.out.println("Left: " + l + " Key: " + n + " Right: " + r + " Parent: " + p + " Balance: " + n.getBalanceado());

        if (n.getNodoEsquerda() != null) {
            debug(n.getNodoEsquerda(), lista);
        }
        if (n.getNodoDireita() != null) {
            debug(n.getNodoDireita(), lista);
        }
    }

    private void setBalanceado(Node cur) {
        cur.setBalanceado(altura(cur.getNodoDireita()) - altura(cur.getNodoEsquerda()));
    }

    public ArrayList<Node> emOrdem() {
        ArrayList<Node> ret = new ArrayList<Node>();
        emOrdem(root, ret);
        return ret;
    }

    private void emOrdem(Node n, ArrayList<Node> io) {
        if (n == null) {
            return;
        }
        emOrdem(n.getNodoEsquerda(), io);
        io.add(n);
        emOrdem(n.getNodoDireita(), io);
    }

    public Node getRoot() {
        return this.root;
    }
    
    public void gravar() {
        LinkedList<Node> lista = new LinkedList<Node>();
        debug(root, lista);
        DAO.gravar(lista, "C:\\Temp\\arquivo.txt");
    }
    
    public void ler() {
        LinkedList<Node> nodos = DAO.ler("C:\\Temp\\arquivo.txt");        
        this.root = nodos.get(0);
    }
    
}
