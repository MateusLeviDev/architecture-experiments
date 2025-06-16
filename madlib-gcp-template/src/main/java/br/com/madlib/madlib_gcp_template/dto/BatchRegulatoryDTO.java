package br.com.madlib.madlib_gcp_template.dto;

import java.util.Map;

public record BatchRegulatoryDTO(
        String source,
        String document,
        Map<String, String> result,
        String status) {
}
