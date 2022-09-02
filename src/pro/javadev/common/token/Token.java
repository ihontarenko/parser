package pro.javadev.common.token;

import java.util.Objects;

public interface Token {

    int type();

    String[] expressions();

    Token[] tokens();

    interface Entry {

        Token token();

        int position();

        int ordinal();

        String value();

        boolean is(Token token);

        boolean is(Entry entry);

    }

    class EntryImplementation implements Entry {

        private final Token  token;
        private final int    position;
        private final String value;
        private final int    ordinal;

        public EntryImplementation(final Token token, final String value, final int position, final int ordinal) {
            this.token = token;
            this.value = value;
            this.position = position;
            this.ordinal = ordinal;
        }

        @Override
        public Token token() {
            return token;
        }

        @Override
        public String value() {
            return value;
        }

        @Override
        public int position() {
            return position;
        }

        @Override
        public int ordinal() {
            return ordinal;
        }

        @Override
        public boolean is(Token token) {
            return this.token.equals(token);
        }

        @Override
        public boolean is(Entry entry) {
            return equals(entry);
        }

        @Override
        public String toString() {
            return String.format("#%03d %s(%s) %s", ordinal, token, position, value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(token);
        }

        @Override
        public boolean equals(final Object that) {
            if(this == that) {
                return true;
            }

            if(that == null || this.getClass() != that.getClass()) {
                return false;
            }

            boolean[] equals = new boolean[] {
                    Objects.equals(((Entry)that).value(), this.value),
                    Objects.equals(((Entry)that).position(), this.position),
                    Objects.equals(((Entry)that).token(), this.token)
            };

            boolean isEqual = true;

            for (boolean equal : equals) {
                isEqual = isEqual && equal;
            }

            return isEqual;
        }

    }

}
