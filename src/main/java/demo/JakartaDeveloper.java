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
import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.List;
import java.util.UUID;
@Entity
public record JakartaDeveloper(@Id String id, @Column String name, @Column String city, @Column String country,
                               @Column List<JakartaSpec> specs) {
    @WithSpan
    public static JakartaDeveloper of(String name, String city, @Column String country, JakartaSpecsType... specs) {
        return new JakartaDeveloper(UUID.randomUUID().toString(), name, city, country, List.of(specs).stream().map(JakartaSpec::new).toList());
    }
    @WithSpan
    public static JakartaDeveloper create(Faker faker){
        return JakartaDeveloper
                .of(faker.name().fullName(),
                        faker.address().city(),
                        faker.address().country(),
                        JakartaSpecsType.randomize(faker));
    }

}
