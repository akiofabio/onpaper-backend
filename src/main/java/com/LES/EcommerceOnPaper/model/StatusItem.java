package com.LES.EcommerceOnPaper.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_itens")
public class StatusItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sti_id")
	private long id;
	
	@Column(name = "sti_status")
	private String status;
	
	@Column(name = "sti_data")
	private LocalDateTime data;

	public StatusItem(String status, LocalDateTime data) {
		super();
		this.status = status;
		this.data = data;
	}
	public StatusItem() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}

