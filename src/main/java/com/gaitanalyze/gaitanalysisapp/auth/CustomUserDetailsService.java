package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CaretakerRepository caretakerRepository;

    public CustomUserDetailsService(CaretakerRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Caretaker caretaker = caretakerRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Email: " + email + "not found."));
        return new User(
                caretaker.getEmail(),
                caretaker.getPassword(),
                Collections.emptyList()
        );
    }
}
