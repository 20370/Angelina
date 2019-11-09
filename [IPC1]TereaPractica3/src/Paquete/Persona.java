
package Paquete;

/**
 *
 * @author M4NU3L
 */
public class Persona {
    private int carnet;
    private String nombre;
    private boolean aprovo;

    public Persona(int carnet, String nombre, boolean aprovo) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.aprovo = aprovo;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAprovo(boolean aprovo) {
        this.aprovo = aprovo;
    }

        
    
    
    public int getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAprovo() {
        return aprovo;
    }
    
    
    
    
}
