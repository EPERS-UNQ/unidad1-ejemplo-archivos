package ar.edu.unq.unidad1.modelo.exception;

import ar.edu.unq.unidad1.modelo.Item;
import ar.edu.unq.unidad1.modelo.Personaje;

public class MuchoPesoException extends RuntimeException {
    private final Personaje personaje;
    private final Item item;

    public MuchoPesoException(Personaje personaje, Item item) {
        super("El personaje [" + personaje + "] no puede recoger [" + item + "] porque cagar mucho peso ya");
        this.personaje = personaje;
        this.item = item;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public Item getItem() {
        return item;
    }
}