package organizador.futbol.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import organizador.futbol.domain.AuthResponse;
import organizador.futbol.domain.LoginRequest;
import organizador.futbol.domain.RegisterRequest;
import organizador.futbol.infrastructure.adapters.out.JpaUserRepository;
import organizador.futbol.infrastructure.entities.UserEntity;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
    private JpaUserRepository userRepository;
	@Autowired
    private JwtService jwtService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(((UserEntity) user).getIdUser());
        authResponse.setToken(token);
        authResponse.setIdRole(((UserEntity) user).getRoleId().getIdRole());

        return authResponse;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return AuthResponse.userAlreadyExistsResponse(); 
        }
 
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoleId(request.getRoleId()); 
        user.setMail(request.getMail());
        user.setName(request.getName());
        user.setPhoto("/img/"+request.getPhoto());
        user.setPhone(request.getPhone());
        user.setAge(request.getAge());
        user.setPosition(request.getPosition());
        user.setTeam(request.getTeam());

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getIdUser());
        authResponse.setToken(jwtService.getToken((UserDetails) user));
        System.out.println(authResponse.getToken());

        return authResponse;
    }


}