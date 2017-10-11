/*
 * Copyright (C) 2007-2017, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.compute.afm

import spock.lang.Specification

import static com.gooddata.util.ResourceUtils.readObjectFromResource
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals
import static net.javacrumbs.jsonunit.core.util.ResourceUtils.resource
import static spock.util.matcher.HamcrestSupport.that

class NativeTotalItemTest extends Specification {

    def "should serialize"() {
        expect:
        that new NativeTotalItem('mId', 'a1', 'a2'),
                jsonEquals(resource('compute/afm/nativeTotalItem.json'))
    }

    def "should deserialize"() {
        when:
        NativeTotalItem total = readObjectFromResource('/compute/afm/nativeTotalItem.json', NativeTotalItem)

        then:
        total.measureIdentifier == 'mId'
        total.attributeIdentifiers == ['a1', 'a2']
        total.toString()
    }
}