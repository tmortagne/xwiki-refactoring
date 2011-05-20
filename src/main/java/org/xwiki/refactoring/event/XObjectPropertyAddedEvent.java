package org.xwiki.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

public class XObjectPropertyAddedEvent extends AbstractXObjectPropertyEvent
{
    public XObjectPropertyAddedEvent()
    {
    }

    public XObjectPropertyAddedEvent(DocumentReference documentReference, DocumentReference classReference,
        Integer identifier, String property)
    {
        super(documentReference, classReference, identifier, property);
    }
}
