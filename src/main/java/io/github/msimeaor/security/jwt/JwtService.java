package io.github.msimeaor.security.jwt;

import io.github.msimeaor.VendasApplication;
import io.github.msimeaor.domain.entily.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

  @Value("${security.jwt.expiracao}")
  private String expiracao;

  @Value("{security.jwt.chave-assinatura}")
  private String chaveAssinatura;

  public String gerarToken(Usuario usuario) {
    Long expString = Long.valueOf(expiracao);
    LocalDateTime horaExpiracao = LocalDateTime.now().plusMinutes(expString);
    Date data = Date.from(horaExpiracao.atZone(ZoneId.systemDefault()).toInstant());

    return Jwts
      .builder()
      .setSubject(usuario.getLogin())
      .setExpiration(data)
      .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
      .compact();
  }

  private Claims obterClaims(String token)  {
    return Jwts
      .parser()
      .setSigningKey(chaveAssinatura)
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean tokenIsValid(String token) {
    try {

      Claims claims = obterClaims(token);
      Date tokenExp = claims.getExpiration();
      LocalDateTime data =
        tokenExp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      return data.isAfter(LocalDateTime.now());

    } catch (Exception e) {
      return false;
    }
  }

  public String obterUsuario(String token) {
    return obterClaims(token).getSubject();
  }

  /*
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
    JwtService jwtService = context.getBean(JwtService.class);
    Usuario usuario = Usuario.builder().login("maatsimeao@gmail.com").build();
    String tokenUsuario = jwtService.gerarToken(usuario);

    System.out.println(jwtService.tokenIsValid(tokenUsuario));
    System.out.println(jwtService.obterUsuario(tokenUsuario));
  }
  */

}
