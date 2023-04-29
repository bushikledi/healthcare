package com.healthcare.model.records;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ScheduleResponse(
        int scheduleId,
        Instant scheduleDate,
        String scheduleAvailability,
        DoctorRecord doctor) {
}
