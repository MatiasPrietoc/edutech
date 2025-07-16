package com.edutech.edutech.assembler;

import com.edutech.edutech.controller.UsuarioController;
import com.edutech.edutech.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public @NonNull EntityModel<Usuario> toModel(@NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).buscar(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios"));
    }
}
