package org.walavo.bar.generate.factory;

public interface BarcodeCodeFactory<T> {

    T generate(String decodeValue);
}
