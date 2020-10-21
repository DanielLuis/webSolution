package br.com.springboot.app.assembler;
import br.com.springboot.app.controller.UserController;
import br.com.springboot.app.domain.User;
import br.com.springboot.app.support.UserStatus;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {

    	EntityModel<User> entityModel = EntityModel.of(user,
                linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
    	
    	if (UserStatus.ACTIVE.getCodigo().equals(user.getStatus())){
    		entityModel.add(linkTo(methodOn(UserController.class).inactivate(user.getId())).withSelfRel());
    	}else {
    		entityModel.add(linkTo(methodOn(UserController.class).active(user.getId())).withSelfRel());
    	}
    	
        return entityModel;
    }
}
