package com.uro_alert.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    private int id;
    private int customerAge;
    private String customerName;
    private String customerEmail;
    private String customerGender;
    private double customerSatisfactionRating;
    private String productPurchased;
    private String ticketChannel;
    private String ticketDescription;
    private String ticketPriority;
    private String ticketSubject;
}
