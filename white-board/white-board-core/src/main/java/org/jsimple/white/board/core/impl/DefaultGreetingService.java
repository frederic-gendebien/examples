package org.jsimple.white.board.core.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.jsimple.white.board.api.GreetingProvider;
import org.jsimple.white.board.api.GreetingService;

/**
 *
 * @author frederic
 */
public class DefaultGreetingService implements GreetingService {

    //--------------------------------------------------------------------------
    // Private fields
    //--------------------------------------------------------------------------
    private final Map<String, GreetingProvider> providers;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    public DefaultGreetingService() {
        this.providers = new ConcurrentHashMap<>();
    }

    //--------------------------------------------------------------------------
    // Life-cycle methods
    //--------------------------------------------------------------------------
    public void register(final GreetingProvider contentProvider) {
        this.providers.putIfAbsent(
            contentProvider.getLanguage(),
            contentProvider);
    }

    public void unregister(final GreetingProvider contentProvider) {

        Optional
            .ofNullable(contentProvider)
            .map((GreetingProvider cp) -> cp.getLanguage())
            .ifPresent((String language) -> {
                this.providers.remove(language);
            });
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------
    @Override
    public String sayHello(String language) {
        return
            Optional
                .ofNullable(this.providers.get(language))
                .orElseThrow(() -> new IllegalArgumentException("Unknown language"))
                .sayHello();
    }
}
