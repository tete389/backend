package com.rmuti.guidemap.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.table.UserProfile;
import com.rmuti.guidemap.backend.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final UserProfileService userProfileService;

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public TokenService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    private Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }

    //public String tokenize(UserProfile userProfile)
    public String tokenize(String userProfile){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        Date expiresAt = calendar.getTime();

        if (userProfile.equals("Admin")){
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("principal",userProfile)
                    .withClaim("role","ADMIN")
                    .withExpiresAt(expiresAt)
                    .sign(algorithm());
        }
       return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal",userProfile)
                .withClaim("role","USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        }catch (Exception e){
            return null;
        }

    }

    public UserProfile checkTokenUser() throws BaseException {
        Optional<String> optUser = SecurityUtil.getCurrentUserId();
        if (optUser.isEmpty()) {
            throw UserException.unAuthorized();
        }
        String userId = optUser.get();
        Optional<UserProfile> byIdUser = userProfileService.findUserProfileById(userId);
        if (byIdUser.isEmpty()) {
            throw UserException.userNotFound();
        }
        return byIdUser.get();
    }

    public Boolean checkAdmin() throws BaseException{
        Optional<String> optUser = SecurityUtil.getCurrentUserId();
        if (optUser.isEmpty()) {
            throw UserException.accessDenied();
        }
        return optUser.get().equals("Admin");
    }


}
