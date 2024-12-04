package com.hana4.demo1.service;


import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hana4.demo1.dao.ApiDAO;
import com.hana4.demo1.dto.UserDTO;
import com.hana4.demo1.entity.User;

@SpringBootTest
public class ApiServiceMockTest {
    @Autowired
    ApiService service;

    @MockBean
    ApiDAO dao;

    @Test
    void getUserTest() {
        final Long ID = 1L;
        final short age = 10;

        // mocking (given)
        Optional<User> ouser = Optional.of(new User(ID, "AA11", age));

        Mockito.when(dao.select(ID)).thenReturn(ouser);

        // when
        UserDTO user = service.getUser(ID);

        //then
        assertThat(user.getId()).isEqualTo(ID);
        assertThat(user.getName()).isEqualTo("AA11");
    }
}
