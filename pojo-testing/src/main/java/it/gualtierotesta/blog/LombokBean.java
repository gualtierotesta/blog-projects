package it.gualtierotesta.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Lombok version of the BasicBean to demonstrate how to implement class conditions using Lombok annotations
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"password", "veryLongString"})
public class LombokBean {

    private int id;
    private String string;
    private char[] password;
    private Long[] longArray;
    private String veryLongString;

    public LombokBean(final int id) {
        this.id = id;
    }

}
