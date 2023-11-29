package com.example.Wordle.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "word_id")
    private Word word;
    @OneToMany(mappedBy = "game")
    private Set<GameGuess> gameGuesses;
    private int attempts;
    private boolean status;
    private boolean finished;
    private LocalDateTime date;
}
