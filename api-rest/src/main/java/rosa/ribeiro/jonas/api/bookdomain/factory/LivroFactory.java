package rosa.ribeiro.jonas.api.bookdomain.factory;

import org.springframework.stereotype.Component;
import rosa.ribeiro.jonas.api.bookdomain.dto.LivroRequestDTO;
import rosa.ribeiro.jonas.bookdomain.model.autor.Autor;
import rosa.ribeiro.jonas.bookdomain.model.categoria.Categoria;
import rosa.ribeiro.jonas.bookdomain.model.editora.Editora;
import rosa.ribeiro.jonas.bookdomain.model.livro.*;
import rosa.ribeiro.jonas.bookdomain.repository.AutorRepository;
import rosa.ribeiro.jonas.bookdomain.repository.CategoriaRepository;
import rosa.ribeiro.jonas.bookdomain.repository.EditoraRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LivroFactory {

    private final EditoraRepository editoraRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroFactory(EditoraRepository editoraRepository,
                        AutorRepository autorRepository,
                        CategoriaRepository categoriaRepository) {
        this.editoraRepository = editoraRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Livro criarEntidade(LivroRequestDTO dto) {
        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new IllegalArgumentException("Editora não encontrada"));

        List<Autor> autores = autorRepository.findAllById(dto.autoresIds());

        List<Categoria> categoriasList = categoriaRepository.findAllById(dto.categoriasIds());
        Set<Categoria> categorias = new HashSet<>(categoriasList);

        return switch (dto.tipo().toUpperCase()) {
            case "CAPA_DURA" -> new LivroCapaDura(dto.titulo(), dto.isbn(), dto.numPaginas(), dto.anoPublicacao(),
                    dto.resumo(), dto.quantidadeEstoque(), dto.precoBase(),
                    editora, autores, categorias);
            case "BROCHURA" -> new LivroBrochura(dto.titulo(), dto.isbn(), dto.numPaginas(), dto.anoPublicacao(),
                    dto.resumo(), dto.quantidadeEstoque(), dto.precoBase(),
                    editora, autores, categorias);
            case "DIGITAL" -> new LivroDigital(dto.titulo(), dto.isbn(), dto.numPaginas(), dto.anoPublicacao(),
                    dto.resumo(), dto.quantidadeEstoque(), dto.precoBase(),
                    editora, autores, categorias);
            default -> throw new IllegalArgumentException("Tipo de livro inválido: " + dto.tipo());
        };
    }
}