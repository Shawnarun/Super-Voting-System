package com.shawn.supervoting.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voting")
public class Voting {

    @Id
    @Column(name="voting_id")
    private String candidateRegisteredEventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "candidate_registered_event_id")
    private CandidateRegisteredEvent candidateRegisteredEvent;

    private LocalDateTime timestamp;

    // Getters and Setters
}