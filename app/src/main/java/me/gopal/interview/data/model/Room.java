package me.gopal.interview.data.model;

public class Room {

    public final int id;
    public final String created_at;
    public final String name;
    public final String max_occupancy;
    public final boolean is_occupied;

    public Room(int id,String created_at,String name,String max_occupancy,boolean is_occupied) {
        this.id = id;
        this.created_at=created_at;
        this.name=name;
        this.max_occupancy=max_occupancy;
        this.is_occupied=is_occupied;

    }
}

