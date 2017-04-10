/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ldpereira
 */
public class DAO {

    public static void gravar(LinkedList<Node> nodos, String arquivo) {
        try {
            File file = verificarArquivo(arquivo);

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Node node : nodos) {
                oos.writeObject(node);
            }

            oos.close();
            fos.close();

        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static LinkedList<Node> ler(String arquivo) {
        LinkedList<Node> nodos = new LinkedList<Node>();
        try {
            File file = verificarArquivo(arquivo);

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {

                Object obj = ois.readObject();
                while (obj != null) {
                    nodos.add((Node) obj);
                    obj = ois.readObject();
                }

            } catch (EOFException ex) {

            } finally {
                ois.close();
                fis.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nodos;
    }

    private static File verificarArquivo(String arquivo) throws IOException {
        File file = new File(arquivo);

        if (!file.exists()) {

            file.getParentFile().mkdir();
            if (file.createNewFile()) {
                return file;
            } else {
                throw new IllegalArgumentException("O arquivo não pode ser criado!");
            }
        }
        return file;
    }

}
