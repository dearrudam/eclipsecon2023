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

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.stream.Stream;

@Repository
public interface Conference extends PageableRepository<JakartaDeveloper, String> {

    @Query("select * from Developer where name like @name")
    @WithSpan
    Stream<JakartaDeveloper> developersWithNameLike(@Param("name") String name);

    @WithSpan
    Stream<JakartaDeveloper> findByNameLike(String name);

}
