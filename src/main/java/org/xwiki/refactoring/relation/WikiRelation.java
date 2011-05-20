package org.xwiki.refactoring.relation;

import org.xwiki.model.reference.EntityReference;

public interface WikiRelation
{
    EntityReference getSourceClass();

    String getSourcePropertyName();

    EntityReference getTargetClass();

    String getTargetPropertyName();
}
