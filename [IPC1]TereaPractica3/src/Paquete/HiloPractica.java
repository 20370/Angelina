package Paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author M4NU3L
 */
public class HiloPractica implements Runnable {

    Persona per;
    Nodo primero;
    Nodo ultimo;

    boolean iterar;
    JButton iniciar;
    JButton pausa;
    JButton continuar;
    JTextArea texto;

    public HiloPractica(JButton entrada1, JButton pausa, JButton continuar, JTextArea texto) {
        this.iniciar = entrada1;
        this.pausa = pausa;
        this.continuar = continuar;
        this.texto = texto;

        primero = null;
        ultimo = null;
    }

    @Override
    public  void run() {

        //synchronized(ultimo);
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
            

            while ( ((leer = textoArchivo.readLine()) != null) || iterar == false) {
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

                try {
                    sleep(1000);
                } catch (Exception e) {
                }

                texto.setText(mostrar());
                //texto.setText(getPersonaje(aux));
                //System.out.println(getPersonaje(aux));
                aux = aux+1;

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
            
    }

    public String mostrar() {
        String var ="";
        String aux ="";
        Nodo actual = new Nodo();
        actual = primero;

        do {
            Persona p = actual.getDato();
            int carnet = p.getCarnet();
            String nombre = p.getNombre();
            boolean aprovado = p.isAprovo();

            var = ""+carnet + "  " + nombre + "  " + aprovado+"";
            aux = var +"\n"+ aux ;
            //System.out.println(carnet + "  " + nombre + "  " + aprovado);
            actual = actual.getSiguiente();

        } while (actual != primero);
       
        return aux;

    }

    public String getPersonaje(int pos) {

        Nodo actual = new Nodo();
        actual = primero;
        int cont = 0;

        while (actual != null) {

            if (cont == pos) {

                Persona p = actual.getDato();
                int carnet = p.getCarnet();
                String nombre = p.getNombre();
                boolean aprovado = p.isAprovo();

                return carnet + "  " + nombre + "  " + aprovado;

            } else {
                actual = actual.getSiguiente();
                cont++;
            }
        }
        return null;
    }

    public void start() {
        iterar = true;
        new Thread(this).start();

    }

    public void detener() {
        this.iterar = false;
    }

}
