package com.example.notificationproject.util;

import com.example.notificationproject.entity.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TemplateParameterExtractorTest {

    @Test
    void testExtractParameters() {
        Template template1 = new Template();
        template1.setBody_template("merhabalarfgndfg${name}fdg -hgöj-h${site}");
        template1.setTitle_template("gdhfghfgh  hg hg g g g- -rth-/* ${}");
        Set<String> template1Parameters = new HashSet<>(List.of("name","site"));

        Template template2 = new Template();
        template2.setTitle_template("merhabalar {name} sizle tanışmak ${iltifat}");
        template2.setBody_template("${merhaba}");
        Set<String> template2Parameters = new HashSet<>(List.of("iltifat","merhaba"));


        Assertions.assertEquals(template1Parameters,TemplateParameterExtractor.extract(template1));
        Assertions.assertEquals(template2Parameters,TemplateParameterExtractor.extract(template2));
    }
} 