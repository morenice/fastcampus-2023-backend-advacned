package org.example;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class Operator1Test {
    private Operator1 operator1 = new Operator1();

    @Test
    void fluxMap() {
        StepVerifier.create(operator1.fluxMap())
                .expectNext(2, 4, 6, 8, 10)
                .verifyComplete();
    }

    @Test
    void fluxFilter() {
        StepVerifier.create(operator1.fluxFilter())
                .expectNext(6,7,8,9,10)
                .verifyComplete();
    }

    @Test
    void fluxFilterTake() {
        StepVerifier.create(operator1.fluxFilterTake())
                .expectNext(6,7,8)
                .verifyComplete();
    }

    @Test
    void fluxFlatMap() {
        StepVerifier.create(operator1.fluxFlatMap())
                .expectNextCount(100)
                .verifyComplete();
    }

    @Test
    void fluxFlatMap2() {
        StepVerifier.create(operator1.fluxFlatMap2())
                .expectNextCount(81)
                .verifyComplete();
    }
}