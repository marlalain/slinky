package generics.readonly.business;

import entities.DummyEntityWithMultipleFields;
import generics.GenericBusiness;
import org.springframework.stereotype.Service;

@Service
public class MultipleFieldsEntityBusiness extends GenericBusiness<DummyEntityWithMultipleFields, Long> {
}
