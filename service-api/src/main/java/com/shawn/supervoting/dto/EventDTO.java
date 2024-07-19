package com.shawn.supervoting.dto;


import com.shawn.supervoting.entity.CandidateRegisteredEvent;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EventDTO {
    private String  eventId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

}
