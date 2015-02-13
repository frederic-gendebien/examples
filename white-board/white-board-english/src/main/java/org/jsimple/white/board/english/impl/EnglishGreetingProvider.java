package org.jsimple.white.board.english.impl;

import org.jsimple.white.board.api.GreetingProvider;

/**
 *
 * @author frederic
 */
public class EnglishGreetingProvider implements GreetingProvider {

    @Override
    public String getLanguage() {
        return "english";
    }

    @Override
    public String sayHello() {
        return "Hello Everybody";
    }

}
