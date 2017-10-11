/*
 * Copyright (C) 2007-2017, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.compute.afm

import com.gooddata.compute.ObjIdentifierQualifier
import com.gooddata.compute.ObjUriQualifier
import spock.lang.Specification
import spock.lang.Unroll

import static com.gooddata.util.ResourceUtils.readObjectFromResource
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals
import static net.javacrumbs.jsonunit.core.util.ResourceUtils.resource
import static spock.util.matcher.HamcrestSupport.that

class AttributeItemTest extends Specification {

    private static final ObjUriQualifier QUALIFIER = new ObjUriQualifier('/gdc/md/projectId/obj/1')

    @Unroll
    def "should serialize #set values"() {
        expect:
        that item, jsonEquals(resource("compute/afm/${file}.json"))

        where:
        set        | item                                    | file
        'all'      | new AttributeItem(QUALIFIER, 'localId') | 'attributeItemFull'
        'required' | new AttributeItem(QUALIFIER)            | 'attributeItem'
    }

    def "should deserialize all values"() {
        when:
        AttributeItem item = readObjectFromResource('/compute/afm/attributeItemFull.json', AttributeItem)

        then:
        item.displayForm == QUALIFIER
        item.localIdentifier == 'localId'
        item.toString()
    }

    def "should deserialize required values"() {
        when:
        AttributeItem item = readObjectFromResource('/compute/afm/attributeItem.json', AttributeItem)

        then:
        item.displayForm == QUALIFIER
    }

    def "should set properties"() {
        when:
        def item = new AttributeItem(new ObjIdentifierQualifier("id"))
        item.setLocalIdentifier("lId")

        then:
        item.getLocalIdentifier() == "lId"
    }
}