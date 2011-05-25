/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */
package org.xwiki.contrib.refactoring.event;

import org.xwiki.bridge.event.AbstractDocumentEvent;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.event.filter.EventFilter;

/**
 * Base class for all object {@link org.xwiki.observation.event.Event events}.
 * 
 * @version $Id$
 */
public class AbstractXObjectEvent extends AbstractDocumentEvent implements XObjectEvent
{
    /**
     * The version identifier for this Serializable class. Increment only if the <i>serialized</i> form of the class
     * changes.
     */
    private static final long serialVersionUID = 1L;

    private EntityReference xclassReference;

    /**
     * The identifier of the object related to this event.
     */
    private Integer number;

    /**
     * Constructor initializing the event filter with an
     * {@link org.xwiki.observation.event.filter.AlwaysMatchingEventFilter}, meaning that this event will match any
     * other attachment event (add, update, delete).
     */
    public AbstractXObjectEvent()
    {
    }

    /**
     * Constructor initializing the event filter with a {@link org.xwiki.observation.event.filter.FixedNameEventFilter},
     * meaning that this event will match only object events affecting the document matching the passed document name.
     * 
     * @param documentName the name of the updated document to match
     * @param identifier the identifier of the object added/updated/deleted
     */
    public AbstractXObjectEvent(DocumentReference documentReference, EntityReference classReference, Integer identifier)
    {
        super(documentReference);

        this.xclassReference = classReference;
        this.number = identifier;
    }

    /**
     * Constructor using a custom {@link EventFilter}.
     * 
     * @param eventFilter the filter to use for matching events
     */
    public AbstractXObjectEvent(EventFilter eventFilter)
    {
        super(eventFilter);
    }

    public EntityReference getXClassReference()
    {
        return this.xclassReference;
    }

    /**
     * Retrieves the identifier of the object added/updated/deleted in the event.
     * 
     * @return object identifier
     */
    public Integer getNumber()
    {
        return this.number;
    }

    @Override
    public boolean matches(Object otherEvent)
    {
        boolean matches = super.matches(otherEvent);

        if (!matches || !(otherEvent instanceof XObjectEvent)) {
            return false;
        }

        XObjectEvent objectEvent = (XObjectEvent) otherEvent;

        return matchesClass(objectEvent.getXClassReference()) && matchesNumber(objectEvent.getNumber());
    }

    protected boolean matchesClass(EntityReference otherReference)
    {
        return getXClassReference() == null || getXClassReference().equals(otherReference);
    }

    protected boolean matchesNumber(Integer otherNumber)
    {
        return getNumber() == null || getNumber() == otherNumber;
    }
}
