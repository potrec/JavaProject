package com.example.Wordle.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_game_guessing")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserGameGuess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGuessingId;

    @Column(name = "guess_number")
    private int guessNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "guess")
    private String guess;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
