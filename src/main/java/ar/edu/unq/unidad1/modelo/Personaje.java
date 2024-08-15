package ar.edu.unq.unidad1.modelo;

import ar.edu.unq.unidad1.modelo.exception.MuchoPesoException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("personaje")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int pesoMaximo;
    private int xp;
    private int vida;
    private Set<Item> inventario;

    private Personaje() {
        this.inventario = new HashSet<>();
    }

    public Personaje(String nombre) {
        this();
        this.nombre = nombre;
    }

    public void recoger(Item item) {
        int pesoActual = getPesoActual();
        if (pesoActual + item.getPeso() > pesoMaximo) {
            throw new MuchoPesoException(this, item);
        }
        inventario.add(item);
    }

    private int getPesoActual() {
        int pesoActual = 0;
        for (Item item : inventario) {
            pesoActual += item.getPeso();
        }
        return pesoActual;
    }

    @Override
    public String toString() {
        return nombre;
    }

    // Getters and setters for the fields
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Set<Item> getInventario() {
        return inventario;
    }

    public void setInventario(Set<Item> inventario) {
        this.inventario = inventario;
    }
}