package com.example.Wordle.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @OneToOne(mappedBy = "word", fetch = FetchType.LAZY)
    private Word word;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<UserGameGuess> userGameGuesses;
}
