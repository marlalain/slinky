package entities;

import generics.IGenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DummyEntityWithMultipleFields implements IGenericEntity<DummyEntityWithMultipleFields, Long> {

	@Id
	private Long id;
	private String name;
	private String occupation;

	@Override
	public Long getId() {
		return this.id;
	}
}
