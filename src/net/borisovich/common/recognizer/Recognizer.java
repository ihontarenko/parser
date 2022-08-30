package net.borisovich.common.recognizer;

import net.borisovich.common.Priority;

import java.util.Optional;

public interface Recognizer<R, T> extends Priority {
    Optional<R> recognize(T subject);
}
