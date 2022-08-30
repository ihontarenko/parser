package net.borisovich.json.token;

import java.util.Objects;

public interface Token {

    int getType();

    String[] getValues();

    interface Entry {

        Token getToken();

        int getPosition();

        String getValue();

    }

    class EntryImplementation implements Entry {

        private final Token  token;
        private final int    position;
        private final String value;

        public EntryImplementation(final Token token, final String value, final int position) {
            this.token = token;
            this.value = value;
            this.position = position;
        }

        @Override
        public Token getToken() {
            return token;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)=%s", token, position, value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(token, value, position);
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
                    Objects.equals(((Entry)that).getValue(), this.value),
                    Objects.equals(((Entry)that).getPosition(), this.position),
                    Objects.equals(((Entry)that).getToken(), this.token)
            };

            boolean isEqual = true;

            for (boolean equal : equals) {
                isEqual = isEqual && equal;
            }

            return isEqual;
        }

    }

}
