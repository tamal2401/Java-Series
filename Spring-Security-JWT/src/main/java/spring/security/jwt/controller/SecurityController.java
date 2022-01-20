package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.jwt.model.TokenRequest;
import spring.security.jwt.model.TokenResponse;
import spring.security.jwt.security.JwtTokenUtil;
import spring.security.jwt.service.InMemoryUserDetailsService;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    InMemoryUserDetailsService inMemoryUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody TokenRequest tokenRequest) throws Exception {
        authenticate(tokenRequest.getUserName(), tokenRequest.getPassword());
        UserDetails user = inMemoryUserDetailsService.loadUserByUsername(tokenRequest.getUserName());
        String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
