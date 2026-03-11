package com.th.readit.domain.link;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record LinkRequest(
        @NotBlank(message = "Url vazia")
        @URL(message = "Informe uma Url válida")
        String url
) {
}
