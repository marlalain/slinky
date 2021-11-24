import generics.IGenericEntity;
import lombok.Data;

@Data
public class DummyEntity implements IGenericEntity<DummyEntity, Long> {
	private Long id;

	@Override
	public Long getId() {
		return this.id;
	}
}
