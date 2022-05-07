package com.LES.EcommerceOnPaper.model;

import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidosStatus")
public class PedidoStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pst_id")
	private long id;
	
	@Column(name = "pst_status")
	private String status;
	
	@Column(name = "pst_data")
	private Date data;
	
	
	
}
