package com.example.Wordle.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_guessings")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserGuessing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGuessingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "guess")
    private String guess;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
