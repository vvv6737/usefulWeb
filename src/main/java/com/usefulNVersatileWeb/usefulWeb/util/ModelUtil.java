package com.usefulNVersatileWeb.usefulWeb.util;

import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

public class ModelUtil {

    final static String WEB_PATH = "/view/";

    public static String model(String... modelString) {
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        for (int i = 0; i < modelString.length; i++) {
            model.addAttribute("a", modelString[i]);
        }
        return WEB_PATH + modelString[0];
    }
}
