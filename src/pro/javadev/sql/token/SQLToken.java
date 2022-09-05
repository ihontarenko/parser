package pro.javadev.sql.token;

import pro.javadev.common.token.Token;

public enum SQLToken implements Token {

    T_SQL_AGAINST(10100, "AGAINST"),
    T_SQL_ALL(10200, "ALL"),
    T_SQL_AND(10300, "AND"),
    T_SQL_AS(10400, "AS"),
    T_SQL_ASC(10500, "ASC"),
    T_SQL_BETWEEN(10600, "BETWEEN"),
    T_SQL_BY(10700, "BY"),
    T_SQL_DELETE(10800, "DELETE"),
    T_SQL_DESC(10900, "DESC"),
    T_SQL_DISTINCT(11000, "DISTINCT"),
    T_SQL_EXPLAIN(11100, "EXPLAIN"),
    T_SQL_FALSE(11200, "FALSE"),
    T_SQL_FROM(11300, "FROM"),
    T_SQL_GROUP(11400, "GROUP"),
    T_SQL_HAVING(11500, "HAVING"),
    T_SQL_IN(11600, "IN"),
    T_SQL_INNER(11700, "INNER"),
    T_SQL_INSERT(11800, "INSERT"),
    T_SQL_INTO(11900, "INTO"),
    T_SQL_IS(12000, "IS"),
    T_SQL_JOIN(12100, "JOIN"),
    T_SQL_LEFT(12200, "LEFT"),
    T_SQL_LIKE(12300, "LIKE"),
    T_SQL_LIMIT(12400, "LIMIT"),
    T_SQL_MATCH(12500, "MATCH"),
    T_SQL_NOT(12600, "NOT"),
    T_SQL_NULL(12700, "NULL"),
    T_SQL_ON(12800, "ON"),
    T_SQL_OR(12900, "OR"),
    T_SQL_ORDER(13000, "ORDER"),
    T_SQL_OUTER(13100, "OUTER"),
    T_SQL_RIGHT(13200, "RIGHT"),
    T_SQL_SELECT(13300, "SELECT"),
    T_SQL_SET(13400, "SET"),
    T_SQL_STRAIGHT_JOIN(13500, "STRAIGHT_JOIN"),
    T_SQL_TABLE(13600, "TABLE"),
    T_SQL_TRUE(13700, "TRUE"),
    T_SQL_UPDATE(13800, "UPDATE"),
    T_SQL_USING(13900, "USING"),
    T_SQL_VALUES(14000, "VALUES"),
    T_SQL_WHERE(14100, "WHERE"),
    T_SQL_XOR(14200, "XOR"),
    T_SQL_MODIFIER(15000,
            "LOW_PRIORITY", "HIGH_PRIORITY",
            "DELAYED", "IGNORE",
            "DISTINCTROW", "SQL_SMALL_RESULT",
            "SQL_BIG_RESULT", "SQL_BUFFER_RESULT",
            "SQL_CACHE", "SQL_NO_CACHE",
            "SQL_CALC_FOUND_ROWS", "QUICK"
    );

    private static final String[] MODIFIERS = {};

    private final int      type;
    private final String[] values;

    SQLToken(final int type) {
        this(type, new String[0]);
    }

    SQLToken(final int type, final String... values) {
        this.type = type;
        this.values = values;
    }

    @Override
    public int type() {
        return type;
    }

    @Override
    public String[] expressions() {
        return values;
    }

    @Override
    public SQLToken[] tokens() {
        return values();
    }

}
