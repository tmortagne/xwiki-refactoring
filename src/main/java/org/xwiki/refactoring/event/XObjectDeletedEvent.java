package org.xwiki.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

public class XObjectDeletedEvent extends AbstractXObjectEvent
{
    public XObjectDeletedEvent()
    {
    }

    public XObjectDeletedEvent(DocumentReference documentReference, DocumentReference classReference, Integer identifier)
    {
        super(documentReference, classReference, identifier);
    }
}
