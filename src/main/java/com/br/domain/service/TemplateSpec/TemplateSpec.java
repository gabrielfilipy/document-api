package com.br.domain.service.TemplateSpec;

import org.springframework.data.jpa.domain.Specification;
import com.br.domain.model.Model;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class TemplateSpec {

	@Spec(path = "name", spec = Like.class)
    public interface ModelSpec extends Specification<Model> {}

}
