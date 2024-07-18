package com.shawn.supervoting.dto;

import com.shawn.supervoting.entity.Candidates;
import com.shawn.supervoting.entity.Event;
import com.shawn.supervoting.entity.Voting;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRegisteredEventDTO {

    private String candidateRegisteredEventId;
    private Event event;
    private CandidatesDTO candidate;

    private Integer totalVotes;

}
