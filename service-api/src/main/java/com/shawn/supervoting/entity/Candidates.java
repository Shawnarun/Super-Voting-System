package com.shawn.supervoting.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "candidates")
public class Candidates {

    @Id
    @Column(name="candidates_id")
    private String candidatesId;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="nic", length = 100, nullable = false)
    private String nic;

    @Column(name="description", length = 150, nullable = false)
    private String description;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateRegisteredEvent> candidateRegisteredEvents;

}
