package me.dio.gameawards.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity(name = "GAMES")
public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    @Column(length = 500)
    private String description;

    private String cover;
    private long votes;

    public long votar(){
        return this.votes++;
    }

}
