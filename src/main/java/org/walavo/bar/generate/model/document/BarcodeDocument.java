package org.walavo.bar.generate.model.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("barcode")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeDocument {

    @JsonIgnore
    @Id
    private String id;

    private String uuid;

    private byte[] barcodeByte;

    private String encoder;

    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date;

}
