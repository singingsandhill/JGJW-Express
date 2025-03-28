package com.zgzg.company.domain;

import java.util.UUID;

import com.zgzg.common.utils.BaseEntity;
import com.zgzg.company.presentation.dto.CompanyResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_company")
public class Company extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private UUID hub_id;

	private String name;

	@Column(name = "company_admin_id")
	private Long companyAdminId;

	private String type;

	private String address;

	public CompanyResponseDTO toDTO(){
		return CompanyResponseDTO.builder()
			.id(this.id)
			.hub_id(this.hub_id)
			.name(this.name)
			.type(this.type)
			.address(this.address)
			.companyAdminId(this.companyAdminId)
			.build();
	}

	public void update(String name, String type, String address){
		this.name = name;
		this.type = type;
		this.address = address;

	}
}
