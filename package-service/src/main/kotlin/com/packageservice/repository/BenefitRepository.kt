package com.packageservice.repository

import com.packageservice.domain.entity.Benefit
import org.springframework.data.jpa.repository.JpaRepository

interface BenefitRepository : JpaRepository<Benefit, Long>