package com.example.util;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
@MappedSuperclass
public abstract class IdEntity {
	private String id;

	//uuid2对应org.hibernate.id.UUIDGenerator
	//uuid和uuid.hex对应org.hibernate.id.UUIDHexGenerator
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
