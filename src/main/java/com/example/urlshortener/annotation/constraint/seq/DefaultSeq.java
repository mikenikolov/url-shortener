package com.example.urlshortener.annotation.constraint.seq;

import javax.validation.GroupSequence;

@GroupSequence({First.class, Second.class, Third.class})
public interface DefaultSeq {
}
