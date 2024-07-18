package com.shawn.supervoting.dto;

import com.shawn.supervoting.entity.CandidateRegisteredEvent;
import com.shawn.supervoting.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDTO {
    private String candidateRegisteredEventId;
    private UserDTO user;
    private CandidateRegisteredEventDTO candidateRegisteredEvent;

    private LocalDateTime timestamp;

    // Getters and Setters
}