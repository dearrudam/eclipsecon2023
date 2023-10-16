/*
 *    Copyright 2023  Contributors to the Eclipse Foundation
 *
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    and Apache License v2.0 which accompanies this distribution.
 *    The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *    and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *    You may elect to redistribute this code under either of these licenses.
 *
 *    Contributors:
 *
 *    Maximillian Arruda
 *
 */

package demo;

import com.github.javafaker.Faker;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

public class AppData {

    @WithSpan
    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Conference conference = container.select(Conference.class, DatabaseQualifier.ofDocument()).get();

            if (conference.count() == 0) {
                for (int i = 0; i < 100; i++) {
                    var presenter = JakartaDeveloper.create(faker);
                    conference.save(presenter);
                }
                var max = JakartaDeveloper.of("Max","São Paulo", "Brazil", JakartaSpecsType.JakartaNoSQL);
                conference.save(max);
            }

            conference.findAll().forEach(System.out::println);
            System.out.println("developersWithNameLike");
            conference.developersWithNameLike("Max").forEach(System.out::println);
            conference.findByNameLike("Max").forEach(System.out::println);

        }

    }
}
