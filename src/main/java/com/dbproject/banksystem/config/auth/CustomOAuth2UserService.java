package com.dbproject.banksystem.config.auth;

import com.dbproject.banksystem.config.auth.dto.OAuthAttributes;
import com.dbproject.banksystem.config.auth.dto.SessionUser;
import com.dbproject.banksystem.domain.user.User;
import com.dbproject.banksystem.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // registrationId
        // * 현재 로그인 진행 중인 서비스 구분 코드
        // * 현재 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시
        // 네이버 로그인인지 구글 로그인인지 구분하기 위해 사용

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttributeName
        // * OAuth2 로그인 진행 시 키가 되는 필드 값을 말함. Primary Key와 같은 의미
        // * 구글의 경우 기본적으로 코드 지원 but 네이버, 카카오 등은 지원하지 않음
        // * 구글 기본 코드는 "sub"
        // * 이후 네이버, 구글 로그인을 동시 지원할 때 사용됨

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // OAuthAttributes
        // * OAuth2UserService 통해 가져온 OAuth2User 의 attribute 담을 클래스
        // * 바로 아래에서 이 클래스의 코드가 나오니 차례로 생성하면 됨

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());

        // SessionUser
        // * 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        // * 왜 User 클래스를 쓰지 않고 새로 만들어 쓰는지 생각해볼 것

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new
                SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}