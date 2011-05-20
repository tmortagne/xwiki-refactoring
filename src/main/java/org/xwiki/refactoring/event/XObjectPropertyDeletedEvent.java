package org.xwiki.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

public class XObjectPropertyDeletedEvent extends AbstractXObjectPropertyEvent
{
    public XObjectPropertyDeletedEvent()
    {
    }

    public XObjectPropertyDeletedEvent(DocumentReference documentReference, DocumentReference classReference,
        Integer identifier, String property)
    {
        super(documentReference, classReference, identifier, property);
    }
}
