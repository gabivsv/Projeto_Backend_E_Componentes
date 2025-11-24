package rosa.ribeiro.jonas.api.bookdomain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rosa.ribeiro.jonas.orderdomain.dto.DadosPedidoDTO;
import rosa.ribeiro.jonas.orderdomain.pedido.Pedido;
import rosa.ribeiro.jonas.orderdomain.service.EfetuarPedidoService;
import rosa.ribeiro.jonas.orderdomain.repository.PedidoRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final EfetuarPedidoService efetuarPedidoService;
    private final PedidoRepository pedidoRepository;

    public PedidoController(EfetuarPedidoService efetuarPedidoService, PedidoRepository pedidoRepository) {
        this.efetuarPedidoService = efetuarPedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody DadosPedidoDTO dados) {
        try {
            Pedido pedidoSalvo = efetuarPedidoService.efetuarPedido(dados);
            return ResponseEntity.created(URI.create("/api/pedidos/" + pedidoSalvo.getId())).body(pedidoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable String id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorCliente(@PathVariable String clienteId) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}