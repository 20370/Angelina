package Paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author M4NU3L
 */
public class ListaCircular {

    private Nodo primero;
    private Nodo ultimo;

    public ListaCircular() {

        primero = null;
        ultimo = null;
    }

    public void ingresarDato() {      

        FileReader lectorA;
        try {
            lectorA = new FileReader("archivo_entrada");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado " + ex.getMessage());
            return;
        }

        BufferedReader textoArchivo;
        textoArchivo = new BufferedReader(lectorA);

        String lineaTexto = "";
        String leer;
        String[] datos;
        int aux = 0;

        try {

            while ((leer = textoArchivo.readLine()) != null) {
                Persona x;
                lineaTexto += leer;
                datos = leer.split(",");

                x = new Persona(Integer.parseInt(datos[0]), datos[1], Boolean.parseBoolean(datos[2]));

                Nodo nuevo = new Nodo();
                nuevo.setDato(x);

                if (primero == null) {
                    primero = nuevo;
                    ultimo = primero;
                    primero.setSiguiente(ultimo);
                } else {
                    ultimo.setSiguiente(nuevo);
                    nuevo.setSiguiente(primero);
                    ultimo = nuevo;
                }
                
                
               

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
    }

    public void mostrar() {

        Nodo actual = new Nodo();
        actual = primero;

        do {
            Persona p = actual.getDato();
            int carnet = p.getCarnet();
            String nombre = p.getNombre();
            boolean aprovado = p.isAprovo();

            System.out.println(carnet + "  " + nombre + "  " + aprovado);
            actual = actual.getSiguiente();

        } while (actual != primero);

    }

    public Persona getPersonaje(int pos) {

        Nodo actual = new Nodo();
        actual = primero;
        int cont = 0;

        while (actual != null) {

            if (cont == pos) {
                return actual.getDato();
            } else {
                actual = actual.getSiguiente();
                cont++;
            }
        }
        return null;
    }

    public int longitud() {

        Nodo actual = new Nodo();
        actual = primero;
        int cont = 0;

        while (actual != null) {

            actual = actual.getSiguiente();
            cont++;
        }
        return cont;
    }

}
