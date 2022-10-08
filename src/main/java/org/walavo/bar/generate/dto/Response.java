package org.walavo.bar.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "image.bounds2D")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    private byte[] image;

    private String shortLink;
}
