package com.itwill.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.security.domain.PrincipalDetails;
import com.itwill.security.domain.Role;
import com.itwill.security.domain.User;
import com.itwill.security.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User join(User user) {
    	System.out.println("1");
        user.setRole(Role.ROLE_USER);
        System.out.println("2");
        String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println("3");
        user.setPassword(encodePwd);
        System.out.println("4");
        User saveUser=userRepository.save(user);  //반드시 패스워드 암호화해야함
        System.out.println(">>>>>>>>>"+user);
        System.out.println("5");
        return saveUser;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    		User byUsername = userRepository.findByUsername(username).orElseThrow(()->{
    			return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다. " + username);
    		});
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        return null;
    }
}