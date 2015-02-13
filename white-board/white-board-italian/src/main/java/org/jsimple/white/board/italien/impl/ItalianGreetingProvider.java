package org.jsimple.white.board.italien.impl;

import org.jsimple.white.board.api.GreetingProvider;

/**
 *
 * @author frederic
 */
public class ItalianGreetingProvider implements GreetingProvider {

    @Override
    public String getLanguage() {
        return "italian";
    }

    @Override
    public String sayHello() {
        return "ciao a tutti";
    }

}
