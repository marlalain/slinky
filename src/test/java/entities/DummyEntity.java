package entities;

import generics.IGenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DummyEntity implements IGenericEntity<DummyEntity, Long> {
	@Id
	private Long id;
}
