package com.healthcare.model.records;

import com.healthcare.model.enums.DoctorSpeciality;
import jakarta.validation.constraints.Positive;

public record DoctorRequest(String doctorName,
                            @Positive double amount,
                            DoctorSpeciality doctorSpeciality,
                            String doctorAbout) {
}
