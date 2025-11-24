package rosa.ribeiro.jonas.api.bookdomain.dto;

import java.math.BigDecimal;
import java.util.List;

public record LivroRequestDTO(
        String titulo,
        String isbn,
        int numPaginas,
        int anoPublicacao,
        String resumo,
        int quantidadeEstoque,
        BigDecimal precoBase,
        String tipo,
        String editoraId,
        List<String> autoresIds,
        List<String> categoriasIds
) {}
