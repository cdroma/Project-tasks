package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "admin.mail=admin@example.com")
class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void shouldLoadAdminMailFromProperties() {
        // When
        String adminMail = adminConfig.getAdminMail();

        // Then
        assertThat(adminMail).isEqualTo("admin@example.com");
    }
}