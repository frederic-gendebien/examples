package org.jsimple.white.board.french.impl;

import org.jsimple.white.board.api.GreetingProvider;

/**
 *
 * @author frederic
 */
public class FrenchGreetingProvider implements GreetingProvider {

    @Override
    public String getLanguage() {
        return "french";
    }

    @Override
    public String sayHello() {
        return "Bonjour à tous";
    }

}
