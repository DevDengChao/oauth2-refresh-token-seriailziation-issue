package dev.dengchao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

import java.util.Date;

@SpringBootTest
class Oauth2RefreshTokenSerializationIssueApplicationTests {

    @Autowired
    ObjectMapper mapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        String dummyAccessToken = mapper.writeValueAsString(new DefaultOAuth2AccessToken("dummy"));
        Assertions.assertNotNull(dummyAccessToken);
        DefaultOAuth2AccessToken accessToken = mapper.readValue(dummyAccessToken, DefaultOAuth2AccessToken.class);
        Assertions.assertNotNull(accessToken);// pass

        String dummyRefreshToken = mapper.writeValueAsString(new DefaultExpiringOAuth2RefreshToken("dummy", new Date()));
        Assertions.assertNotNull(dummyRefreshToken);
        DefaultExpiringOAuth2RefreshToken refreshToken = mapper.readValue(dummyRefreshToken, DefaultExpiringOAuth2RefreshToken.class);
        Assertions.assertNotNull(refreshToken);// this should pass as above
    }

}
