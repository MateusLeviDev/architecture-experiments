package com.levi.decision.service.impl;

import com.levi.decision.domain.Decision;
import com.levi.decision.domain.SSN;
import com.levi.decision.repository.DecisionRepository;
import com.levi.decision.service.DecisionMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionMakerServiceImpl implements DecisionMakerService {

    private final DecisionRepository decisionRepository;
    @Override
    public Decision decide(Integer ssn, LocalDate birthDate) {
        Decision decision = Decision.decide(SSN.create(ssn), birthDate);
        Decision decisionCreated = decisionRepository.save(decision);
        log.info("the decision is: {}",decisionCreated);

        return decision;
    }
}
