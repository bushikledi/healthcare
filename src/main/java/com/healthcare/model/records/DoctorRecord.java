package com.healthcare.model.records;

import lombok.Builder;

@Builder
public record DoctorRecord(Long doctorId,
                           String name,
                           String speciality) {
}
