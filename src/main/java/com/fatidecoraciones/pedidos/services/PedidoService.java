package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.criteria.PedidoCriteria;
import com.fatidecoraciones.pedidos.dtos.ClienteDto;
import com.fatidecoraciones.pedidos.dtos.PedidoDto;
import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.models.Pedido;
import com.fatidecoraciones.pedidos.models.Pedido_;
import com.fatidecoraciones.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;


import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService extends QueryService<Pedido> {

    @Autowired
    private  PedidoRepository pedidoRepository;

    public boolean existById (Long id) {
        return pedidoRepository.existsById(id);
    }
    public Pedido getPedido (Long id){
        return pedidoRepository.findById(id).orElse(null);
        }
    public void save (Pedido pedido){
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
    public void delete (Long id){pedidoRepository.deleteById(id);}

        public List<Pedido> findByCriteria(PedidoCriteria pedidoCriteria) {
        final Specification<Pedido> specification = createSpecification(pedidoCriteria);
        List<Pedido> pedidos = pedidoRepository.findAll(specification);
        return pedidos;
    }
    private Specification<Pedido> createSpecification(PedidoCriteria pedidoCriteria) {
        Specification<Pedido> specification = Specification.where(null);
        if (pedidoCriteria != null) {
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

//            if (pedidoCriteria.getClienteNombre() != null) {
//                specification = specification.and(buildReferringEntitySpecification(pedidoCriteria.getClienteNormbre(), Pedido_.cliente, Cliente_.apellido));
//            }
//            if (pedidoCriteria.getClienteNombre() != null) {
//                specification = specification.and(buildSpecification(pedidoCriteria.getClienteNombre(), root -> root.join(Pedido_.cliente, JoinType.LEFT).get(Cliente_.nombre)));
//            }

        }
        return specification;
    }
}