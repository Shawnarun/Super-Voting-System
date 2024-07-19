package com.shawn.supervoting.dto;


import com.shawn.supervoting.entity.CandidateRegisteredEvent;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CandidatesDTO {
    private String candidatesId;
    private String name;
    private String nic;
    private String description;
    private String photoUrl;

}
