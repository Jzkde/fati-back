package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.PedidoCriteria;
import com.fatidecoraciones.FatiDeco.dtos.PedidoDto;
import com.fatidecoraciones.FatiDeco.models.Pedido;
import com.fatidecoraciones.FatiDeco.models.Pedido_;
import com.fatidecoraciones.FatiDeco.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService extends QueryService<Pedido> {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public boolean existsById(Long id) {
        return pedidoRepository.existsById(id);
    }

    public Pedido getPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<PedidoDto> getPedidosDto() {
        return pedidoRepository.findAll().stream().map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
    }

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoDto getPedidoDto(Long id) {
        return new PedidoDto(this.getPedido(id));
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> findByCriteria(PedidoCriteria pedidoCriteria) {
        final Specification<Pedido> specification = createSpecification(pedidoCriteria);
        return pedidoRepository.findAll(specification);
    }
    private Specification<Pedido> createSpecification(PedidoCriteria pedidoCriteria) {
        Specification<Pedido> specification = Specification.where(null);
        if (pedidoCriteria != null) {
            if (pedidoCriteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(pedidoCriteria.getId(), Pedido_.id));
            }
            if (pedidoCriteria.getFecha_pedido() != null) {
                specification = specification.and(buildRangeSpecification(pedidoCriteria.getFecha_pedido(), Pedido_.fecha_pedido));
            }
            if (pedidoCriteria.getProvedor() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getProvedor(), Pedido_.provedor));
            }
            if (pedidoCriteria.getVia() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getVia(), Pedido_.via));
            }
            if (pedidoCriteria.getN_pedido() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getN_pedido(), Pedido_.n_pedido));
            }
            if (pedidoCriteria.getN_factura() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getN_factura(), Pedido_.n_factura));
            }
            if (pedidoCriteria.getN_remito() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getN_remito(), Pedido_.n_remito));
            }
            if (pedidoCriteria.getLlego() != null) {
                specification = specification.and(buildSpecification(pedidoCriteria.getLlego(), Pedido_.llego));
            }
            if (pedidoCriteria.getFecha_llegada() != null) {
                specification = specification.and(buildRangeSpecification(pedidoCriteria.getFecha_llegada(), Pedido_.fecha_llegada));
            }
            if (pedidoCriteria.getEstado() != null) {
                specification = specification.and(buildSpecification(pedidoCriteria.getEstado(), Pedido_.estado));
            }
            if (pedidoCriteria.getClienteNombre() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getClienteNombre(), Pedido_.clienteNombre));
            }
            if (pedidoCriteria.getResponsable() != null) {
                specification = specification.and(buildStringSpecification(pedidoCriteria.getResponsable(), Pedido_.responsable));
            }
        }
        return specification;
    }
}