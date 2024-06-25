package com.yo.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@SQLDelete(sql = "UPDATE EMPLOYEE SET ACCOUNT_STATUS ='REMOVED' WHERE EMP_NO=?")
@Where(clause = "ACCOUNT_STATUS <> 'REMOVED' ")
public class Employee {
	 @Id
	@SequenceGenerator(name = "EmpGen",sequenceName = "empno_seq",initialValue = 8,allocationSize = 1)
	@GeneratedValue(generator = "EmpGen",strategy = GenerationType.SEQUENCE)
	private Integer empNo;
	private String name;
	private Integer deptNo;
	private String location;
	private Double sal;
	private String accountStatus="ACTIVE";	

}
