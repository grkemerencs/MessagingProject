package com.example.notificationproject.util;

import com.example.notificationproject.entity.Template;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParameterExtractor {
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\$\\{([^}]+)}");
    public static Set<String> extract(Template template){
        List<String> parameters = new ArrayList<>();
        Matcher matcher = PARAM_PATTERN.matcher(template.getBody_template().concat(template.getTitle_template()));
        Set<String> result = new HashSet<>();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
