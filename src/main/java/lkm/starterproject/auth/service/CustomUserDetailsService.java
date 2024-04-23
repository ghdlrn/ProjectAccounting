package lkm.starterproject.auth.service;

import lkm.starterproject.auth.repository.UserRepository;
import lkm.starterproject.auth.dto.CustomUserDetails;
import lkm.starterproject.auth.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity memberEntity = userRepository.findByEmail(email);   //DB에서 이메일 찾음

        if (memberEntity != null) {     //member가 null이 아니면 CustomUerDetails에 member정보를 담아 리턴
            return new CustomUserDetails(memberEntity);
        }

        return null;
    }
}
