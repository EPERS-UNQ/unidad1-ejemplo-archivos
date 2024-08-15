package ar.edu.unq.unidad1.modelo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("item")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int peso;

    private Item() {
    }

    public Item(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return nombre;
    }
}