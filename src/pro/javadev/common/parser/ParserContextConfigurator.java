package pro.javadev.common.parser;

import pro.javadev.common.configurer.Configurator;

public class ParserContextConfigurator implements Configurator<ParserContext> {

    @Override
    public void configure(ParserContext object) {
        object.add(new IdentifierParser());
        object.add(new LiteralParser());
    }

}
