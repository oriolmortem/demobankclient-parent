package pe.devsu.development.clientservicetest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;

    private String gender;

    private Integer age;

    private String docId;

    private String address;

    private String phoneNumber;
	
	private LocalDateTime creationTime;
	
	private LocalDateTime updateTime;
	
	private LocalDateTime deleteTime;
}