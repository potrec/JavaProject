package com.example.Wordle.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_game_guessing")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GameGuess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameGuessId;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @Column(name = "guess_number")
    private int guessNumber;
    private String word;
    private boolean result;
    private LocalDateTime date;
}
