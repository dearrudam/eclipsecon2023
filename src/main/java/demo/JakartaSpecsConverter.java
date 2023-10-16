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

import org.eclipse.jnosql.mapping.AttributeConverter;

public class JakartaSpecsConverter implements AttributeConverter<JakartaSpecsType,String> {
    @Override
    public String convertToDatabaseColumn(JakartaSpecsType attribute) {
        return attribute==null?null:attribute.name();
    }

    @Override
    public JakartaSpecsType convertToEntityAttribute(String dbData) {
        return dbData==null?null: JakartaSpecsType.valueOf(dbData);
    }
}
