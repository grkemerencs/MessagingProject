package com.example.notificationproject.util;

import com.example.notificationproject.Model.entity.MessageTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class MessageTemplateParameterExtractorTest {

    @Test
    void testExtractParameters() {
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setBody_template("merhabalarfgndfg${name}fdg -hgöj-h${site}");
        messageTemplate1.setTitle_template("gdhfghfgh  hg hg g g g- -rth-/* ${}");
        Set<String> template1Parameters = new HashSet<>(List.of("name","site"));

        MessageTemplate messageTemplate2 = new MessageTemplate();
        messageTemplate2.setTitle_template("merhabalar {name} sizle tanışmak ${iltifat}");
        messageTemplate2.setBody_template("${merhaba}");
        Set<String> template2Parameters = new HashSet<>(List.of("iltifat","merhaba"));


        Assertions.assertEquals(template1Parameters,TemplateParameterExtractor.extract(messageTemplate1));
        Assertions.assertEquals(template2Parameters,TemplateParameterExtractor.extract(messageTemplate2));
    }
} 