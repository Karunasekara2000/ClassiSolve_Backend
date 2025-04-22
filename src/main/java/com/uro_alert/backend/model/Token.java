package com.uro_alert.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uro_alert.backend.model.enumeration.TokenType;
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
public class Token {


  public int id;

  public String token;

  public TokenType tokenType;

  public boolean revoked;

  public boolean expired;

  public User user;
}
