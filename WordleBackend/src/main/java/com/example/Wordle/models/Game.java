package com.example.Wordle.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "gameGuesses"})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @OrderBy("guessNumber ASC")
    private Set<GameGuess> gameGuesses;
    private int attempts;
    private boolean status;
    private boolean finished;
    private LocalDateTime date;
}
