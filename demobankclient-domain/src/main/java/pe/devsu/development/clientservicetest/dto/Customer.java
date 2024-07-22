package pe.devsu.development.clientservicetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Customer extends Person {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String password;

	private boolean active;

	private Integer personId;
}