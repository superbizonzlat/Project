package com.test.Project.security;


import com.test.Project.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//специальный класс обертка над нашим классом, с помощью которого мы достаем всю информацию о нашем юзере(пёрсене)
// здесь есть все стандартные методы, с помощью которых идет проверка. Это своего рода стандарт для Security
public class PersonDetails implements UserDetails {


    private final User user;

    public PersonDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //Чтобы получить данные аутентифицированного пользователя
    public User getPerson()
    {
        return this.user;
    }
}
