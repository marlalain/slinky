package entities;

import com.pauloelienay.slinky.generics.IGenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DummyEntity implements IGenericEntity<DummyEntity, Long> {
	@Id
	private Long id;
}
