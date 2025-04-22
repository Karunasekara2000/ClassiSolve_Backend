package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.Token;

import java.util.List;

public interface TokenRepository  {

//  @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
  List<Token> findAllValidTokenByUser(Integer id);

  Token findByToken(String token);
  Token save(Token token);
  List<Token> saveAll(List<Token> tokenList);
   void batchUpdateTokensAsRevoked(List<Token> tokens);
}
