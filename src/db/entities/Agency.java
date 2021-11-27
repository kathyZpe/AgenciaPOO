package db.entities;

public class Agency {
    private int id;
    private String name;

    public Agency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Agency(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
