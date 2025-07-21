package com.example.notificationproject.util;

import com.example.notificationproject.Model.entity.MessageTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParameterExtractor {
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\$\\{([^}]+)}");
    public static Set<String> extract(MessageTemplate messageTemplate){
        List<String> parameters = new ArrayList<>();
        Matcher matcher = PARAM_PATTERN.matcher(messageTemplate.getBody_template().concat(messageTemplate.getTitle_template()));
        Set<String> result = new HashSet<>();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
