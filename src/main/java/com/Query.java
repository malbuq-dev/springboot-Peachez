package com;

import org.springframework.http.ResponseEntity;

public interface Query<I, O> {
    public ResponseEntity<O> execute(I input);
}
