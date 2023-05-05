package com.healthcare.model.records;

import lombok.Builder;

@Builder
public record DoctorRecord(Integer doctorId,
                           String name,
                           String speciality) {
}
