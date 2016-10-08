package br.almadaapps.fundamentalssolutions;

/**
 * Created by vinicius-almada on 08/10/16.
 */

public class Solucao {
    private int id;
    private String description;

    public Solucao(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
