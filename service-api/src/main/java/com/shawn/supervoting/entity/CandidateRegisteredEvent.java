package com.shawn.supervoting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_registered_events")
public class CandidateRegisteredEvent {

    @Id
    @Column(name="candidate-registered-event_id")
    private String candidateRegisteredEventId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidates candidate;

    private Integer totalVotes;

    @OneToMany(mappedBy = "candidateRegisteredEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voting> votingList;
}
