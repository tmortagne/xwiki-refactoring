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
package org.xwiki.refactoring.event;

import org.xwiki.model.reference.DocumentReference;
import org.xwiki.observation.event.filter.EventFilter;

/**
 * Base class for all object {@link org.xwiki.observation.event.Event events}.
 * 
 * @version $Id$
 */
public class AbstractXObjectPropertyEvent extends AbstractXObjectEvent
{
    /**
     * The version identifier for this Serializable class. Increment only if the <i>serialized</i> form of the class
     * changes.
     */
    private static final long serialVersionUID = 1L;

    private String property;

    /**
     * Constructor initializing the event filter with an
     * {@link org.xwiki.observation.event.filter.AlwaysMatchingEventFilter}, meaning that this event will match any
     * other attachment event (add, update, delete).
     */
    public AbstractXObjectPropertyEvent()
    {
    }

    /**
     * Constructor initializing the event filter with a {@link org.xwiki.observation.event.filter.FixedNameEventFilter},
     * meaning that this event will match only object events affecting the document matching the passed document name.
     * 
     * @param documentName the name of the updated document to match
     * @param identifier the identifier of the object added/updated/deleted
     */
    public AbstractXObjectPropertyEvent(DocumentReference documentReference, DocumentReference classReference,
        Integer identifier, String property)
    {
        super(documentReference, classReference, identifier);

        this.property = property;
    }

    /**
     * Constructor using a custom {@link EventFilter}.
     * 
     * @param eventFilter the filter to use for matching events
     */
    public AbstractXObjectPropertyEvent(EventFilter eventFilter)
    {
        super(eventFilter);
    }
}
