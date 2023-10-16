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
import org.eclipse.jnosql.communication.document.DocumentEntity;
import org.eclipse.jnosql.communication.document.DocumentManager;
import org.eclipse.jnosql.communication.document.DocumentQuery;

import java.util.UUID;

public class AppApi {

    @WithSpan
    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var manager = container.select(DocumentManager.class).get();

            if (manager.count(DocumentQuery.builder().select()
                    .from("JakartaDeveloperSchemaLess")
                    .build()) == 0) {
                for (int i = 0; i < 100; i++) {
                    var presenter = DocumentEntity.of("JakartaDeveloperSchemaLess");
                    presenter.add("_id", UUID.randomUUID().toString());
                    presenter.add("name", faker.name().fullName());
                    manager.insert(presenter);
                }
            }

            var developers = manager.select(DocumentQuery.builder()
                    .select()
                    .from("JakartaDeveloperSchemaLess")
                    .build()).toList();

            developers.forEach(System.out::println);


        }

    }
}
