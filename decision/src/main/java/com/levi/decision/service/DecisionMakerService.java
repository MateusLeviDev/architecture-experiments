package com.levi.decision.service;


import com.levi.decision.domain.Decision;

import java.time.LocalDate;

public interface DecisionMakerService {
    Decision decide(Integer ssn, LocalDate birthDate);
}
