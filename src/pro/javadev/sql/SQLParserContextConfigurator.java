package pro.javadev.sql;

import pro.javadev.common.parser.ParserContext;
import pro.javadev.common.parser.ParserContextConfigurator;
import pro.javadev.sql.parser.FieldPathParser;

public class SQLParserContextConfigurator extends ParserContextConfigurator {

    @Override
    public void configure(ParserContext context) {
        super.configure(context);

        context.add(new FieldPathParser());
    }

}
