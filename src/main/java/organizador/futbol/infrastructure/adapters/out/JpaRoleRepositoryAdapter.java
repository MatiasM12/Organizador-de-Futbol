package organizador.futbol.infrastructure.adapters.out;

import organizador.futbol.domain.Role;
import organizador.futbol.infrastructure.entities.RoleEntity;
import organizador.futbol.infrastructure.mappers.RoleMapper;
import organizador.futbol.infrastructure.ports.out.RoleOutputPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaRoleRepositoryAdapter implements RoleOutputPort {
	
	@Autowired
    private JpaRoleRepository jpaRoleRepository;
	@Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return jpaRoleRepository.findAll().stream()
                .map(roleMapper::toRole)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRoleRepository.findById(id)
                .map(roleMapper::toRole);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleMapper.toRoleEntity(role);
        return roleMapper.toRole(jpaRoleRepository.save(roleEntity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRoleRepository.deleteById(id);
    }
}
