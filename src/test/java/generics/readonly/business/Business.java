package generics.readonly.business;

import entities.DummyEntity;
import generics.GenericBusiness;
import org.springframework.stereotype.Service;

@Service
public class Business extends GenericBusiness<DummyEntity, Long> {
}
