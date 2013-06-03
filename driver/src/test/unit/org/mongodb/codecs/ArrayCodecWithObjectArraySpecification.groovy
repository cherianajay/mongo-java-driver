package org.mongodb.codecs

import org.bson.BSONWriter
import spock.lang.Specification

class ArrayCodecWithObjectArraySpecification extends Specification {
    def BSONWriter bsonWriter = Mock(BSONWriter);
    def Codecs codecs = Mock(Codecs);

    //Object under test
    private ArrayCodec arrayCodec = new ArrayCodec(codecs);

    def 'should write start and end for array of objects and delegate encoding of object'() {
        setup:
        final Object object1 = new Object();
        final Object object2 = new Object();
        final Object[] arrayOfObjects = [object1, object2];

        when:
        arrayCodec.encode(bsonWriter, arrayOfObjects);

        then:
        1 * bsonWriter.writeStartArray();
        1 * codecs.encode(bsonWriter, object1);
        1 * codecs.encode(bsonWriter, object2);
        1 * bsonWriter.writeEndArray();
    }

    def 'should write start & end for array of objects & delegate encoding of object when array disguised as object'() {
        setup:
        final Object object1 = new Object();
        final Object object2 = new Object();
        final Object arrayOfObjects = [object1, object2] as Object[];

        when:
        arrayCodec.encode(bsonWriter, arrayOfObjects);


        then:
        1 * bsonWriter.writeStartArray();
        1 * codecs.encode(bsonWriter, object1);
        1 * codecs.encode(bsonWriter, object2);
        1 * bsonWriter.writeEndArray();
    }

}