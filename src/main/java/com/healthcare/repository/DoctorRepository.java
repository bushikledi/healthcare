package com.healthcare.repository;

import com.healthcare.model.Doctor;
import com.healthcare.model.enums.DoctorSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Optional<Doctor> findBydoctorFirstname(String doctorFirstname);
}
