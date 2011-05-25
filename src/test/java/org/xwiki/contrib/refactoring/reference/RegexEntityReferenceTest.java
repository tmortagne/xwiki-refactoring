package org.xwiki.contrib.refactoring.reference;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;
import org.xwiki.model.EntityType;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;

public class RegexEntityReferenceTest
{
    private static final DocumentReference REFERENCETOMATCH = new DocumentReference("wiki", "space", "page");

    @Test
    public void testExact()
    {
        EntityReference wikiReference =
            new RegexEntityReference(Pattern.compile(REFERENCETOMATCH.getWikiReference().getName(), Pattern.LITERAL),
                EntityType.WIKI);
        EntityReference spaceReference =
            new RegexEntityReference(Pattern.compile(REFERENCETOMATCH.getLastSpaceReference().getName(),
                Pattern.LITERAL), EntityType.SPACE, wikiReference);
        EntityReference reference =
            new RegexEntityReference(Pattern.compile(REFERENCETOMATCH.getName(), Pattern.LITERAL), EntityType.DOCUMENT,
                spaceReference);

        Assert.assertTrue(reference.equals(REFERENCETOMATCH));
    }

    @Test
    public void testWithOnlyPage()
    {
        EntityReference reference =
            new RegexEntityReference(Pattern.compile(REFERENCETOMATCH.getName(), Pattern.LITERAL), EntityType.DOCUMENT);

        Assert.assertTrue(reference.equals(REFERENCETOMATCH));
    }

    @Test
    public void testWithOnlyWiki()
    {
        EntityReference reference =
            new RegexEntityReference(Pattern.compile(REFERENCETOMATCH.getWikiReference().getName(), Pattern.LITERAL),
                EntityType.WIKI);

        Assert.assertTrue(reference.equals(REFERENCETOMATCH));
    }
    
    @Test
    public void testPattern()
    {
        EntityReference reference =
            new RegexEntityReference(Pattern.compile("p.*"), EntityType.DOCUMENT);

        Assert.assertTrue(reference.equals(REFERENCETOMATCH));
    }
}
