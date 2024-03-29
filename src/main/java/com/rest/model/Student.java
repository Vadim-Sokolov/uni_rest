package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "student_card_number")
    private String studentCardNumber;
	
	@Column(name = "firstname")
    private String firstName;
	
	@Column(name = "lastname")
    private String lastName;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
    private Group group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(String studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentCardNumber == null) ? 0 : studentCardNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (studentCardNumber == null) {
            if (other.studentCardNumber != null)
                return false;
        } else if (!studentCardNumber.equals(other.studentCardNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [ID = " + id + ", Name = " + firstName + " " + lastName + ", Student Card Number = "
                + studentCardNumber + ", Group = " + group + "]";
    }
}
