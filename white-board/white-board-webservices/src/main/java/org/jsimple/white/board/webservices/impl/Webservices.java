package org.jsimple.white.board.webservices.impl;

import java.util.Optional;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.validation.PredicateValidationException;

/**
 *
 * @author frederic
 */
public class Webservices extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(PredicateValidationException.class)
            .handled(true)
            .transform().constant("content-type header is missing")
            .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(400)
            .setHeader(Exchange.CONTENT_TYPE).constant("application/text")
            .end();

        onException(IllegalArgumentException.class)
            .handled(true)
            .transform().constant("content-type header is does not match any known language")
            .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(400)
            .setHeader(Exchange.CONTENT_TYPE).constant("application/text")
            .end();

        onException(Exception.class)
            .handled(true)
            .transform().simple("${exception}")
            .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(500)
            .setHeader(Exchange.CONTENT_TYPE).constant("application/text")
            .end();

        rest("/white-board/greeting")
            .get()
            .route()
                .validate((Exchange exchng) -> {
                    return
                        Optional
                            .ofNullable(exchng.getIn().getHeader(Exchange.CONTENT_TYPE, String.class))
                            .map((String language) -> language.length() > 0)
                            .orElse(Boolean.FALSE);
                })
                .transform().simple("${in.header.Content-Type}")
                .to("bean:greetingService?method=sayHello")
                .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(200)
                .setHeader(Exchange.CONTENT_TYPE).constant("application/text")
            .endRest();

    }

}
