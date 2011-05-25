package org.xwiki.contrib.refactoring.reference;

import java.util.regex.Pattern;

import org.xwiki.model.EntityType;
import org.xwiki.model.reference.EntityReference;

public class RegexEntityReference extends EntityReference
{
    private Pattern pattern;

    public RegexEntityReference(Pattern pattern, EntityType type)
    {
        super(pattern.pattern(), type);
    }

    public RegexEntityReference(Pattern pattern, EntityType type, EntityReference parent)
    {
        super(pattern.pattern(), type, parent);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof EntityReference)) {
            return false;
        }

        EntityReference reference = (EntityReference) obj;

        for (EntityReference entity = reference; entity != null; entity = entity.getParent()) {
            if (getType().equals(entity.getType())) {
                if (this.pattern != null && !this.pattern.matcher(entity.getName()).matches()) {
                    return false;
                } else {
                    return getParent() != null ? getParent().equals(entity) : true;
                }
            }
        }

        return true;
    }
}
