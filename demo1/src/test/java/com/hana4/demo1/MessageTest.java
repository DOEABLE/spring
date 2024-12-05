package com.hana4.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class MessageTest {
    @Autowired
    MessageSource msgsrc;

    @Test
    void siteMessageTest() {
        String title = msgsrc.getMessage("site.title", null, Locale.KOREAN);
        assertThat(title).isEqualTo("데모앱");
        String titleEn = msgsrc.getMessage("site.title", null, Locale.ENGLISH);
        assertThat(titleEn).isEqualTo("DemoApplication");

        String description = msgsrc.getMessage("site.description", new Object[] {"테스트"}, Locale.KOREAN);
        assertThat(description).isEqualTo("데모::테스트");

        String descriptionZh = msgsrc.getMessage("site.description", new Object[] {"TEST"}, Locale.CHINESE);
        assertThat(descriptionZh).isEqualTo("DEMO大::TEST");
    }

    @Test
    void notFoundTest() {
        assertThatThrownBy(() -> msgsrc.getMessage("site.titleXXX", null, Locale.KOREA)).isInstanceOf(
                NoSuchMessageException.class);

        String msg = msgsrc.getMessage("site.titleXXX", null, "DemoDefault", Locale.KOREA);
        assertThat(msg).isEqualTo("DemoDefault");
    }
}