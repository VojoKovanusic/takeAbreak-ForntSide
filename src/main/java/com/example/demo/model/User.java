package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity 
@NoArgsConstructor
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = -6945475342210470677L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="role")
	private String role;		
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="finishedPause") 
	private String endTimeOfThePausa;
	
	@Column(name="sendAlertWraning")
	private boolean sendAlertWarning;
	
	@Column(name="isTakePauseNow")
	private boolean isTakePauseNow;
	
	@Column(name="isTakePauseToday")
	private boolean isTakePauseToday;

}
