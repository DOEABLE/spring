package com.hana4.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustDTO {
		private Integer id;
		private String name, tel, email;

		@Override
		public String toString() {
				return "CustDTO{" +
						"id=" + id +
						", name='" + name + '\'' +
						", tel='" + tel + '\'' +
						", email='" + email + '\'' +
						'}';
		}
}
