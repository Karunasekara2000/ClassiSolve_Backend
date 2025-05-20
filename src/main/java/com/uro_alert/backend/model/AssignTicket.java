package com.uro_alert.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignTicket {

    private int id;
    private int ticketId;
    private int employeeId;
    private String ticketSubject;
    private String resolutionTime;
    private String customerName;
    private String customerEmail;
    private LocalDateTime assignedAt;
}
