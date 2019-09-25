/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package com.microsoft.azure.spring.data.cosmosdb.repository.query;

import com.microsoft.azure.spring.data.cosmosdb.repository.support.CosmosEntityInformation;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.EntityMetadata;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;

import java.lang.reflect.Method;

public class ReactiveCosmosQueryMethod extends QueryMethod {

    private CosmosEntityMetadata<?> metadata;

    public ReactiveCosmosQueryMethod(Method method, RepositoryMetadata metadata, ProjectionFactory factory) {
        super(method, metadata, factory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public EntityMetadata<?> getEntityInformation() {
        final Class<Object> domainClass = (Class<Object>) getDomainClass();
        final CosmosEntityInformation entityInformation =
                new CosmosEntityInformation<Object, String>(domainClass);

        this.metadata = new SimpleCosmosEntityMetadata<>(domainClass, entityInformation);
        return this.metadata;
    }
}
