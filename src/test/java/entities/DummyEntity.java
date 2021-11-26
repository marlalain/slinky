package entities;

import generics.IGenericEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyEntity implements IGenericEntity<DummyEntity, Long> {
	private Long id;
}
