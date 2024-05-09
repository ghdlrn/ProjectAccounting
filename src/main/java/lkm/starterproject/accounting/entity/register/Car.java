package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_code")
    private Long code;

    @Column(nullable = false)
    private String licensePlateNumber;  //차량번호

    private String carModel;    //차종

    private String carNameStatus;    //차량명의

    private LocalDate startTenancy; //임차시작일

    private LocalDate endTenancy;   //임차종료일

    private Long mileage;    //주행거리

    private String insurance;   //가입보험

    private LocalDate startInsuranceDate; //보험시작일

    private LocalDate endInsuranceDate; //보험종료일
}
