package com.example.javatraining.utils.jwt;


import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class JwtUserPayload extends JwtPayload {
    private final String email;
    private final String role;
    private final long id;

    @Builder
    public JwtUserPayload(
            final String iss,
            final String sub,
            final long iat,
            final long exp,
            final String email,
            final String role,
            final long id
    ) {
        super(iss, sub, iat, exp);
        this.email = email;
        this.role = role;
        this.id = id;
    }

    @NonNull
    public static JwtUserPayload from(@NonNull final Claims claims) {
        return JwtUserPayload.builder()
                .iss(claims.getIssuer())
                .exp(claims.getExpiration().getTime())
                .iat(claims.getIssuedAt().getTime())
                .sub(claims.getSubject())
                .email(claims.get("email", String.class))
                .role(claims.get("role", String.class))
                .id(Long.parseLong(claims.get("id", String.class)))
                .build();
    }
}
