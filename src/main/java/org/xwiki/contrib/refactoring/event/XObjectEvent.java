package org.xwiki.contrib.refactoring.event;

import org.xwiki.model.reference.EntityReference;

public interface XObjectEvent
{
    Integer getNumber();

    EntityReference getXClassReference();
}
