package organizador.futbol.infrastructure.adapters.in;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import organizador.futbol.application.AuthService;
import organizador.futbol.domain.AuthResponse;
import organizador.futbol.domain.LoginRequest;
import organizador.futbol.domain.RegisterRequest;

@RestController
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
	@Autowired
    private AuthService authService;
	
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
	    ModelAndView modelAndView = new ModelAndView("login.html");
	    return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
	    ModelAndView modelAndView = new ModelAndView("register.html");
	    return modelAndView;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
