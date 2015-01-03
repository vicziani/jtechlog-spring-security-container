package jtechlog.springsecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class JpaAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaAuthenticationUserDetailsService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        try {
            LOGGER.info("User betoltese username alapjan: {}", token.getName());
            User user = entityManager.createQuery("select u from User u where u.username = :username", User.class).setParameter("username", token.getName()).getSingleResult();

            PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails details = (PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails) token.getDetails();
            LOGGER.info("Jogosultsagok betoltese web.xml alapjan: {}", details.getGrantedAuthorities());

            user.setAuthorities(details.getGrantedAuthorities());
            return user;
        } catch (NoResultException nre) {
            throw new UsernameNotFoundException("A felhasználó a megadott felhasználónévvel nem található: " + token.getName(), nre);
        }
    }
}
