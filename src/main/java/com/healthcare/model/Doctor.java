package com.healthcare.model;


import com.healthcare.model.enums.DoctorSpeciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_firstname", nullable = false)
    private String doctorFirstname;

    @Column(name = "doctor_lastname", nullable = false)
    private String doctorLastname;

    @Column(name = "doctor_telephone")
    private String doctorTelephone;


    @Column(name = "doctor_speciality")
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality doctorSpeciality;

    @Column(name = "doctor_about")
    private String doctorAbout;


    @Override
    public String toString(){
        return "Doctor{" +
                "doctor_id=" + id +
                ", doctor_firstname='" + doctorFirstname + '\'' +
                ", doctor_lastname=" + doctorLastname + '\'' +
                ", doctor_telephone=" + doctorTelephone + '\'' +
                ", doctor_speciality="+ doctorSpeciality+'\'' +
                ", doctor_about="+
                '}';
    }
}