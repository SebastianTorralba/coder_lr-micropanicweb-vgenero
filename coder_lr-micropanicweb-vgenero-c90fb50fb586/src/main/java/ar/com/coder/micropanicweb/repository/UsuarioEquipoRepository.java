package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.UsuarioEquipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UsuarioEquipoRepository")
public interface UsuarioEquipoRepository extends JpaRepository<UsuarioEquipos, Long> {

    public UsuarioEquipos findUsuarioEquipoById(Long id);

}
