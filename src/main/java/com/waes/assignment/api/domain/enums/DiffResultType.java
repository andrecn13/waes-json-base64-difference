package com.waes.assignment.api.domain.enums;

/**
 * Enum that represents types of results
 *
 * EQUAL - when both side are equals
 * NOT_EQUAL - when both side has same size but different content
 * DIFFERENT_SIZES - when one side has different size
 *
 */
public enum DiffResultType {
    EQUAL,
    NOT_EQUAL,
    DIFFERENT_SIZES
}
