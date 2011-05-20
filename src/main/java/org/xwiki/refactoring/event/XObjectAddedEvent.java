package org.xwiki.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

public class XObjectAddedEvent extends AbstractXObjectEvent
{
    public XObjectAddedEvent()
    {
    }

    public XObjectAddedEvent(DocumentReference documentReference, DocumentReference classReference, Integer identifier)
    {
        super(documentReference, classReference, identifier);
    }
}
