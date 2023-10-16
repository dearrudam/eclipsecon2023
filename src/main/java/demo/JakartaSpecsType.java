/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Maximillian Arruda
 *
 */

package demo;

import com.github.javafaker.Faker;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import java.util.LinkedHashSet;
import java.util.Set;

public enum JakartaSpecsType {
    JakartaNoSQL,
    JakartaData,
    JakartaPersistence,
    JakartaServlet,
    JakartaFaces,
    JakartaWebSocket,
    JakartaRestfulWebServices,
    JakartaBeanValidation;

    @WithSpan
    public static JakartaSpecsType[] randomize(Faker faker) {
        Set<JakartaSpecsType> specs = new LinkedHashSet<>();
        var numOfSpecs = faker.number().numberBetween(0, JakartaSpecsType.values().length -1);
        for(int i = 0; i < numOfSpecs; i++) {
            specs.add(JakartaSpecsType.values()[faker.number().numberBetween(0, JakartaSpecsType.values().length -1)]);
        }
        return specs.toArray(new JakartaSpecsType[0]);
    }
}
