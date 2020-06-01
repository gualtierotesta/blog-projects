package it.gualtierotesta.blog;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Simple JavaBean with the following conditions:
 * <p>
 * - the ID field is the logical equality value
 * <p>
 * - the password field is security sensitive
 * <p>
 * - the veryLongString field should not be logged
 * <p>
 * - the no-args constructor is required by JPA
 */
public class BasicBean {

    private int id;
    private String string;
    private char[] password;
    private Long[] longArray;
    private String veryLongString;

    public BasicBean() {
        // No args constructor required by JPA
    }

    public BasicBean(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(final char[] password) {
        this.password = password;
    }

    public Long[] getLongArray() {
        return longArray;
    }

    public void setLongArray(final Long[] longArray) {
        this.longArray = longArray;
    }

    public String getVeryLongString() {
        return veryLongString;
    }

    public void setVeryLongString(final String veryLongString) {
        this.veryLongString = veryLongString;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BasicBean basicBean = (BasicBean) o;
        return id == basicBean.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BasicBean.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("string='" + string + "'")
            .add("longArray=" + Arrays.toString(longArray))
            .toString();
    }
}
