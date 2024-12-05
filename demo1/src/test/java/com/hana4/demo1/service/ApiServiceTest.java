package com.hana4.demo1.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hana4.demo1.dto.UserDTO;

@SpringBootTest
public class ApiServiceTest {
    @Autowired
    ApiService service;

    @Test
    void getUserTest() {
        final Long ID = 1L;
        UserDTO user = service.getUser(ID);

        // Optional<User> ouser = Optional.of(new User(1L, "AA11", (short)33));
        // Mockito.when(apiDAO.select(1L)).thenReturn(ouser);
        // UserDTO user = apiService.getUser(1L);

        assertThat(user.getId()).isEqualTo(ID);
        // assertThat(user.getName()).isEqualTo("AA10");
        assertThat(user.getName()).isNotNull();
    }
}