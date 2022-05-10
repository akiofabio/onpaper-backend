package com.LES.EcommerceOnPaper.model;


import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_pedidos")
public class StatusPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stp_id")
	private long id;
	
	@Column(name = "stp_status")
	private String status;
	
	@Column(name = "stp_data")
	private Date data;

	public StatusPedido() {}
	
	public StatusPedido(String status, Date data) {
		super();
		this.status = status;
		this.data = data;
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}	
}
