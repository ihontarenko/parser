package net.borisovich.json.lexer;

import net.borisovich.json.token.JSONToken;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractLexer implements Lexer {

    List<JSONToken.Entry> entries = new ArrayList<>(1 << 12);

}
